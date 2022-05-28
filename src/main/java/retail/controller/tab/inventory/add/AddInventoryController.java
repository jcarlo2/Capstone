package retail.controller.tab.inventory.add;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductDatabase;
import retail.shared.pojo.Product;
import retail.shared.pojo.ProductReport;
import retail.shared.pojo.ProductReportItem;
import retail.shared.constant.ConstantDialog;
import retail.shared.customcomponent.jcombobox.JComboBoxProduct;
import retail.shared.customcomponent.jtable.JTableInventory;
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.bot.inventory.manipulator.panel.add.AddInventory;
import retail.view.main.tab.top.TopBorderPanel;
import retail.view.main.tab.top.UserPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AddInventoryController {
    private final ProductDatabase productDatabase = new ProductDatabase();
    private final AddInventory add;
    private final JTableInventory table;
    private final UserPanel userPanel;

    public AddInventoryController(@NotNull BottomBorderPanel bottomBorderPanel, @NotNull TopBorderPanel topBorderPanel) {
        userPanel = topBorderPanel.getUserPanel();
        add = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getAddPanel().getAddInventory();
        table = bottomBorderPanel.getBottomMainCard().getInventoryCard().getAdd().getTable();
        addReport();
        insertDataInTable();
        deleteRowInTable();
        clear();
        documentListenerQuantityByBox();
        documentListenerQuantityByPiece();
        reportIdGenerate();
        productIdItemListener();
        autoSetPrice();
        autoSetPiecesPerBox();
        updateJComboBox();
        add.getReportId().setText(generateReportId()); // Generate initial report id at startup
    }

    private void updateJComboBox() {
        Runnable runnable = () -> {
            if(!isSameData(add.getId())) {
                SwingUtilities.invokeLater(this::setProductIdList);
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,0,1, TimeUnit.SECONDS);
    }

    private boolean isSameData(@NotNull JComboBoxProduct box) {
        int count = box.getItemCount();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Product> prodList = productDatabase.showProduct();
        for(int i=0;i<count;i++) {
            list.add(box.getComboBoxModel().getElementAt(i));
        }
        for(Product product : prodList) {
            if(!list.contains(product.getId())) return false;
        }
        return list.size() == prodList.size();
    }

    private @NotNull ArrayList<String> getAllProductID() {
        ArrayList<Product> product = productDatabase.showProduct();
        ArrayList<String> list = new ArrayList<>();
        for(Product item : product) {
            list.add(item.getId());
        }
        return list;
    }

    // MUST CALL EVERY PRESSED IN ADD-JBUTTON IN INVENTORY PRODUCT PANEL
    public void setProductIdList() {
        add.getId().removeAllItems();
        ArrayList<String> list = getAllProductID();
        for(String id : list) {
            add.getId().getComboBoxModel().addElement(id);
        }
    }

    private void addReport() {
        add.getSave().addActionListener(e -> {
            if(table.getRowCount() == 0) {
                ConstantDialog.EMPTY_REPORT_TABLE();
                return;
            }
            if(productDatabase.isReportExist(add.getReportId().getText())) {
                ConstantDialog.GENERATE_NEW_REPORT_ID();
                return;
            }
            ArrayList<ProductReportItem> itemList = getAllItemsAtTable();
            addReportInDatabase(itemList,createProductReport(),updateProductQuantity(itemList));
            ConstantDialog.SAVED_REPORT();
            for(Product product: updateProductQuantity(itemList)) {
                System.out.println(product.getQuantityPerPieces());
            }
        });
    }

    public void addReportInDatabase(ArrayList<ProductReportItem> list, ProductReport report, @NotNull ArrayList<Product> productList) {
        productDatabase.saveReport(report);
        productDatabase.saveReportItem(list, report);
        for (Product product : productList) {
            productDatabase.updateProductQuantity(product.getId(), product.getQuantityPerPieces());
        }
    }

    private @NotNull ArrayList<Product> updateProductQuantity(@NotNull ArrayList<ProductReportItem> itemList) {
        ArrayList<Product> productList = new ArrayList<>();
        for (ProductReportItem item : itemList) {
            String id = item.getProductId();
            String description = productDatabase.get(id).getDescription();
            BigDecimal price = item.getPrice();
            Double quantityPerPieces = item.getQuantityByPieces();
            Double quantityPerBox = item.getQuantityByBox();
            Double piecesPerBox = item.getPiecesPerBox();
            productList.add(new Product(id, description, price, quantityPerPieces, piecesPerBox, quantityPerBox));
        }
        return productList;
    }

    private @NotNull ProductReport createProductReport() {
        String  id = add.getReportId().getText();
        String user = userPanel.getEmployeeLastName().getText();
        return new ProductReport(id, user, Date.valueOf(LocalDate.now()));
    }

    private @NotNull ArrayList<ProductReportItem> getAllItemsAtTable() {
        ArrayList<ProductReportItem> list = new ArrayList<>();
        int ROW = table.getRowCount();
        int COLUMN = table.getColumnCount();
            for(int i=0;i<ROW;i++) {
                String[] data = new String[8];
                for(int j=0;j<COLUMN;j++) {
                    data[j] = table.getValueAt(i,j).toString();
                }
                list.add(new ProductReportItem(data[1],new BigDecimal(data[2]),
                    Double.parseDouble(data[3]),Double.parseDouble(data[4]),Double.parseDouble(data[5])));
            }
        return list;
    }

    private void clear() {
        add.getClear().addActionListener(e -> {
            add.getQuantityByPiece().setText("0");
            add.getQuantityByBox().setText("0");
        });
    }

    private void deleteRowInTable() {
        add.getDelete().addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row > -1) {
                table.getModel().removeRow(row);
                fixNumberColumn();
            }
        });
    }

    private void fixNumberColumn() {
        int ROW = table.getRowCount();
        for(int i=0;i<ROW;i++) {
            table.setValueAt(i+1, i,0);
        }
    }

    private void insertDataInTable() {
        add.getAdd().addActionListener(e -> {
            String id = (String) add.getId().getSelectedItem();
            BigDecimal price = new BigDecimal(add.getPrice().getText());
            Double quantityPerPiece = Double.parseDouble(add.getQuantityByPiece().getText());
            Double piecesPerBox = Double.parseDouble(add.getPiecesPerBox().getText());
            Double quantityPerBox = Double.parseDouble(add.getQuantityByBox().getText());
            Double oldCount = productDatabase.get(id).getQuantityPerPieces();
            Product product = new Product(id,"",price, quantityPerPiece,piecesPerBox,quantityPerBox);
            table.addItem(product,oldCount);
            fixNumberColumn();
        });
    }

    private boolean isValidNumber(@NotNull String input) {
        if(input.equals("")) return false;
        if(input.charAt(0) == '.') return false;
        for(int i=0;i<input.length();i++) {
            if(!Character.isDigit(input.charAt(i)) && !(input.charAt(i) == '.')) {
                return false;
            }
        }
        return true;
    }

    private Double getPiecesPerBox() {
        final String productID = (String) add.getId().getSelectedItem();
        return productDatabase.get(productID).getPiecesPerBox();
    }

    private void documentListenerQuantityByBox() {
        add.getQuantityByBox().getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if(add.getQuantityByBox().isFocusOwner())
                    autoCalculate(add.getQuantityByPiece()
                            , add.getQuantityByBox(),getPiecesPerBox(),true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(add.getQuantityByBox().isFocusOwner())
                    autoCalculate(add.getQuantityByPiece()
                            , add.getQuantityByBox(),getPiecesPerBox(),true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void documentListenerQuantityByPiece() {
        add.getQuantityByPiece().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(add.getQuantityByPiece().isFocusOwner())
                    autoCalculate(add.getQuantityByBox(),
                            add.getQuantityByPiece(),getPiecesPerBox(),false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(add.getQuantityByPiece().isFocusOwner())
                    autoCalculate(add.getQuantityByBox(),
                            add.getQuantityByPiece(),getPiecesPerBox(),false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void autoCalculate(@NotNull JTextField textChange, JTextField textGet,Double num,boolean isMultiply) {
        if(isMultiply){
            textChange.setText(String.valueOf(multiplyToGetTotal(num,textGet.getText(),textGet)));
        }else {
            textChange.setText(String.valueOf(divideToGetTotal(BigDecimal.valueOf(num),textGet.getText(),textGet)));
        }
    }

    private @NotNull Double multiplyToGetTotal(Double piecesPerBox, String input, @NotNull JTextField textField)  {
        if (isValidNumber(input)) return Integer.parseInt(textField.getText()) * piecesPerBox;
        return 0.0;
    }

    private @NotNull BigDecimal divideToGetTotal(BigDecimal piecesPerBox, String input, @NotNull JTextField textField)  {
        int SCALE = 4;
        if (isValidNumber(input)) return new BigDecimal(textField.getText())
                .divide(piecesPerBox,SCALE, RoundingMode.CEILING);
        return new BigDecimal("0");
    }

    private void productIdItemListener() {
        add.getId().addItemListener(e -> {
            autoSetPrice();
            autoSetPiecesPerBox();
            add.getQuantityByBox().setText("0");
            add.getQuantityByPiece().setText("0");
        });
    }

    private void autoSetPiecesPerBox() {
        String productID = (String) add.getId().getSelectedItem();
        if(Objects.isNull(productID)) return; // PREVENT NULL POINTER EXCEPTION WHEN REMOVING ALL ELEMENTS
        String piecesPerBox = String.valueOf(productDatabase.get(productID).getPiecesPerBox());
        add.getPiecesPerBox().setText(piecesPerBox);
    }

    private void autoSetPrice() {
        String productID = (String) add.getId().getSelectedItem();
        if(Objects.isNull(productID)) return; // PREVENT NULL POINTER EXCEPTION WHEN REMOVING ALL ELEMENTS
        String price = String.valueOf(productDatabase.get(productID).getPrice());
        add.getPrice().setText(price);
    }

    private void reportIdGenerate() {
        add.getGenerateId().addActionListener(e -> add.getReportId().setText(generateReportId()));
    }

    private @NotNull String generateReportId() {
        long id;
        String formatId = "";
        boolean flag = true;
        while(flag) {
            id = (long) (Math.random() * 1000000000000L);
            formatId = String.format("%013d",id);
            if(!productDatabase.isReportExist(formatId)) flag = false;
        }
        return "IR" + formatId + "-A0";
    }
}
