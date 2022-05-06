package retail.controller.tab.inventory;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductDatabase;
import retail.customcomponent.jtable.CustomJTableInventory;
import retail.model.Product;
import retail.model.ProductReport;
import retail.model.ProductReportItem;
import retail.util.constant.ConstantDialog;
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.bot.manipulator.inventory.panel.add.AddInventory;
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

public class AddController {
    private final ProductDatabase controller = new ProductDatabase();
    private final AddInventory add;
    private final CustomJTableInventory table;
    private final UserPanel userPanel;
    public AddController(@NotNull BottomBorderPanel bottomBorderPanel, @NotNull TopBorderPanel topBorderPanel) {
        userPanel = topBorderPanel.getUserPanel();
        add = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getAddPanel().getAddCard().getAdd();
        table = bottomBorderPanel.getBottomMainCard().getInventoryCard().getAdd().getTableInventory();

        addReportInDatabase();
        insertDataInTable();
        deleteRowInTable();
        clear();
        documentListenerQuantityByBox();
        documentListenerQuantityByPiece();
        reportIdGenerate();
        productIdItemListener();
        autoSetPrice();
        autoSetPiecesPerBox();
        add.getReportId().setText(generateReportId()); // Generate initial report id at startup
    }

    private void addReportInDatabase() {
        add.getSave().addActionListener(e -> {
            if(table.getRowCount() == 0) {
                ConstantDialog.EMPTY_REPORT_TABLE();
                return;
            }
            if(controller.isReportExist(add.getReportId().getText())) {
                ConstantDialog.GENERATE_NEW_REPORT_ID();
                return;
            }
            ArrayList<ProductReportItem> itemList = getAllItemsAtTable();

            controller.addReport(itemList,createProductReport(),updateProductQuantity(itemList));
            ConstantDialog.SAVED_REPORT();
        });
    }

    private @NotNull ArrayList<Product> updateProductQuantity(@NotNull ArrayList<ProductReportItem> itemList) {
        ArrayList<Product> productList = new ArrayList<>();
        for (ProductReportItem productReportItem : itemList) {
            String id = productReportItem.getProductId();
            String description = controller.get(id).getDescription();
            BigDecimal price = productReportItem.getPrice();
            BigDecimal quantityPerPieces = addProductQuantity(productReportItem.getQuantityByPieces(),id);
            BigDecimal quantityPerBox = productReportItem.getQuantityByBox();
            BigDecimal piecesPerBox = productReportItem.getPiecesPerBox();
            productList.add(new Product(id, description, price, quantityPerPieces, piecesPerBox, quantityPerBox));
        }
        return productList;
    }

    private @NotNull BigDecimal addProductQuantity(@NotNull BigDecimal quantityPerPieces, String id) {
        return quantityPerPieces.add(controller.get(id).getQuantityPerPieces());
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
                    new BigDecimal(data[3]),new BigDecimal(data[4]),new BigDecimal(data[5])));
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
            String price = add.getPrice().getText();
            String quantityByPiece = add.getQuantityByPiece().getText();
            String quantityByBox = add.getQuantityByBox().getText();
            String piecesPerBox = add.getPiecesPerBox().getText();
            BigDecimal oldCount = controller.get(id).getQuantityPerPieces();
            Product product = new Product(id,"",new BigDecimal(price),
                    new BigDecimal(quantityByPiece),new BigDecimal(piecesPerBox),
                    new BigDecimal(quantityByBox));
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

    private BigDecimal getPiecesPerBox() {
        final String productID = (String) add.getId().getSelectedItem();
        return controller.get(productID).getPiecesPerBox();
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

    private void autoCalculate(@NotNull JTextField textChange, JTextField textGet,BigDecimal num,boolean isMultiply) {
        if(isMultiply){
            textChange.setText(String.valueOf(multiplyToGetTotal(num,textGet.getText(),textGet)));
        }else {
            textChange.setText(String.valueOf(divideToGetTotal(num,textGet.getText(),textGet)));
        }
    }

    private @NotNull BigDecimal multiplyToGetTotal(BigDecimal piecesPerBox, String input, @NotNull JTextField textField)  {
        if (isValidNumber(input)) return new BigDecimal(textField.getText()).multiply(piecesPerBox);
        return new BigDecimal("0");
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
        String piecesPerBox = String.valueOf(controller.get(productID).getPiecesPerBox());
        add.getPiecesPerBox().setText(piecesPerBox);
    }

    private void autoSetPrice() {
        String productID = (String) add.getId().getSelectedItem();
        if(Objects.isNull(productID)) return; // PREVENT NULL POINTER EXCEPTION WHEN REMOVING ALL ELEMENTS
        String price = String.valueOf(controller.get(productID).getPrice());
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
            if(!controller.isReportExist(formatId)) flag = false;
        }
        return "IR" + formatId;
    }
}
