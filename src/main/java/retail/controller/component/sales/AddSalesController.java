package retail.controller.component.sales;

import org.jetbrains.annotations.NotNull;
import retail.customcomponent.jlist.CustomJList;
import retail.customcomponent.jtable.CustomJTableSalesReport;
import retail.util.constant.Constant;
import retail.util.constant.ConstantDialog;
import retail.controller.database.ProductController;
import retail.controller.database.SalesReportController;
import retail.model.SalesReportItem;
import retail.model.SalesReport;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.manipulator.reportmanipulator.panel.Add;
import retail.view.main.panel.top.TopBorderPanel;
import retail.view.main.panel.top.UserPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class AddSalesController {
    private final SalesReportController salesReportController = new SalesReportController();
    private final ProductController productController = new ProductController();
    private final CustomJTableSalesReport table;
    private final Add inventoryManipulatorAdd;
    private final UserPanel userPanel;
    private final CustomJList reportListView;
    private final CustomJList reportListDelete;

    public AddSalesController(@NotNull TopBorderPanel topBorderPanel, @NotNull BottomBorderPanel bottomBorderPanel) {
        table = bottomBorderPanel.getBottomMainCard().getSalesReport().getSalesMainAdd().getCenterTable();
        inventoryManipulatorAdd = bottomBorderPanel.getManipulatorCard().getSalesReportManipulator().getSalesManipulatorCard().getInventoryManipulatorAdd();
        userPanel = topBorderPanel.getUserPanel();
        reportListView = bottomBorderPanel.getManipulatorCard().getSalesReportManipulator().getSalesManipulatorCard().getView().getList();
        reportListDelete = bottomBorderPanel.getManipulatorCard().getSalesReportManipulator().getSalesManipulatorCard().getDelete().getList();
        clear();
        addItemToTable();
        deleteRowFromTable();
        productIdItemListener();
        autoSetPrice();
        documentListenerOfAddPanelComponent();
        saveReportInDatabase();
        reportIdActionListener();
        inventoryManipulatorAdd.getReportId().setText(generateReportId()); // Generate initial report id at startup
    }

    private void saveReportInDatabase() {
        inventoryManipulatorAdd.getSave().addActionListener(e -> {
            if(e.getSource() == inventoryManipulatorAdd.getSave()) {
                if(table.getRowCount() == 0) {
                    ConstantDialog.EMPTY_REPORT_TABLE();
                    return;
                }
                if(salesReportController.ifReportExist(inventoryManipulatorAdd.getReportId().getText())) {
                    ConstantDialog.GENERATE_NEW_REPORT_ID();
                    return;
                }
                salesReportController.addReport(createSalesReport(),getAllItemReportAtSalesTable());
                reportListDelete.populateSalesList(salesReportController.getSalesReportList());
                reportListView.populateSalesList(salesReportController.getSalesReportList());
                ConstantDialog.SAVED_REPORT();
            }
        });
    }

    private @NotNull ArrayList<SalesReportItem> getAllItemReportAtSalesTable() {
        ArrayList<SalesReportItem> itemReport = new ArrayList<>();
        int ROW = table.getRowCount();
        int COLUMN = table.getColumnCount();
        for(int i=0;i<ROW;i++) {
            String[] data = new String[8];
            for(int j=0;j<COLUMN;j++) {
                data[j] = table.getModel().getValueAt(i,j).toString();
            }
            itemReport.add(new SalesReportItem(data[1],new BigDecimal(data[2]),new BigDecimal(data[3])
                    ,new BigDecimal(data[4]),new BigDecimal(data[5])
                    , new BigDecimal(data[6]),new BigDecimal(data[7])));
        }
        return itemReport;
    }

    private @NotNull SalesReport createSalesReport() {
        String reportId = inventoryManipulatorAdd.getReportId().getText();
        String lastName = userPanel.getEmployeeLastName().getText();
        Date date = Date.valueOf(LocalDate.now());

        return new SalesReport(reportId, date,lastName);
    }

    private void deleteRowFromTable() {
        inventoryManipulatorAdd.getDelete().addActionListener(e -> {
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

    private void addItemToTable() {
        inventoryManipulatorAdd.getAdd().addActionListener(e -> {
            if(isAllFieldValid()) {
                String[] data = getFieldData();
                table.addRow(new SalesReportItem(data[0],new BigDecimal(data[1]),
                        new BigDecimal(data[2]),new BigDecimal(data[3]),new BigDecimal(data[4])
                        ,new BigDecimal(data[5]),new BigDecimal(data[6])));
                        fixNumberColumn();
            } else {
                ConstantDialog.INVALID_INPUT();
            }
        });
    }

    private String @NotNull [] getFieldData() {
        String[] data = new String[7];
        data[0] = (String) inventoryManipulatorAdd.getId().getSelectedItem();
        data[1] = inventoryManipulatorAdd.getPrice().getText();
        data[2] = inventoryManipulatorAdd.getSold().getText();
        data[3] = inventoryManipulatorAdd.getSoldTotal().getText();
        data[4] = inventoryManipulatorAdd.getDamage().getText();
        data[5] = inventoryManipulatorAdd.getDamageTotal().getText();
        data[6] = inventoryManipulatorAdd.getTotal().getText();
        return data;
    }

    private boolean isAllFieldValid() {
        if(inventoryManipulatorAdd.getSold().getText().equals("")) return false;
        if(inventoryManipulatorAdd.getSoldTotal().getText().equals("")) return false;
        if(inventoryManipulatorAdd.getDamageTotal().getText().equals("")) return false;
        if(inventoryManipulatorAdd.getDamage().getText().equals("")) return false;
        if(!isValidChar(inventoryManipulatorAdd.getSold().getText().toCharArray())) return false;
        if(!isValidChar(inventoryManipulatorAdd.getSoldTotal().getText().toCharArray())) return false;
        if(!isValidChar(inventoryManipulatorAdd.getDamage().getText().toCharArray())) return false;
        return isValidChar(inventoryManipulatorAdd.getDamageTotal().getText().toCharArray());
    }

    private boolean isValidChar(char @NotNull [] a) {
        for(char b : a) {
            if(!((b >= Constant.ZERO && b <= Constant.NINE) || b == '.')) {
                return false;
            }
        }
        return true;
    }

    private void documentListenerOfAddPanelComponent() {
        documentListenerOfSold();
        documentListenerOfSoldTotal();
        documentListenerOfDamagedExpired();
        documentListenerOfDamagedExpiredTotal();
    }

    private void reportIdActionListener() {
        inventoryManipulatorAdd.getGenerateId().addActionListener(e -> inventoryManipulatorAdd.getReportId().setText(generateReportId()));
    }

    private @NotNull String generateReportId() {
        long id;
        String formatId = "";
        boolean flag = true;
        while(flag) {
            id = (long) (Math.random() * 1000000000000L);
            formatId = String.format("%013d",id);
            if(!salesReportController.ifReportExist(formatId)) flag = false;
        }
        return "SR" + formatId;
    }

    private void clear() {
        inventoryManipulatorAdd.getClear().addActionListener(e -> {
            if(e.getSource() == inventoryManipulatorAdd.getClear()) {
                inventoryManipulatorAdd.getSold().setText("0");
                inventoryManipulatorAdd.getSoldTotal().setText("0");
                inventoryManipulatorAdd.getDamageTotal().setText("0");
                inventoryManipulatorAdd.getDamage().setText("0");
                inventoryManipulatorAdd.getTotal().setText("0");
            }
        });
    }

    private void calculateTotalSales() {
        try {
            String soldTotal = inventoryManipulatorAdd.getSoldTotal().getText();
            String damExpTotal = inventoryManipulatorAdd.getDamageTotal().getText();
            if(soldTotal.equals("")) soldTotal = "0";
            if(damExpTotal.equals("")) damExpTotal = "0";
            BigDecimal total = new BigDecimal(soldTotal).subtract(new BigDecimal(damExpTotal));
            inventoryManipulatorAdd.getTotal().setText(total.toString());
        }catch (NumberFormatException e) {
            ConstantDialog.INVALID_INPUT();
        }
    }

    private void autoSetPrice() {
        String productID = (String) inventoryManipulatorAdd.getId().getSelectedItem();
            if(Objects.isNull(productID)) return; // PREVENT NULL POINTER EXCEPTION WHEN REMOVING ALL ELEMENTS
        String price = String.valueOf(productController.get(productID).getPrice());
        inventoryManipulatorAdd.getPrice().setText(price);
    }

    private void productIdItemListener() {
        inventoryManipulatorAdd.getId().addItemListener(e -> {
            autoSetPrice();
            inventoryManipulatorAdd.getSold().setText("0");
            inventoryManipulatorAdd.getSoldTotal().setText("0");
            inventoryManipulatorAdd.getDamage().setText("0");
            inventoryManipulatorAdd.getDamageTotal().setText("0");
            inventoryManipulatorAdd.getTotal().setText("0");
        });
    }

    private void documentListenerOfSold() {
        inventoryManipulatorAdd.getSold().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(inventoryManipulatorAdd.getSold().isFocusOwner()) {
                    autoCalculate(inventoryManipulatorAdd.getSoldTotal(), inventoryManipulatorAdd.getSold(),true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(inventoryManipulatorAdd.getSold().isFocusOwner()) {
                    autoCalculate(inventoryManipulatorAdd.getSoldTotal(), inventoryManipulatorAdd.getSold(),true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void documentListenerOfSoldTotal() {
        inventoryManipulatorAdd.getSoldTotal().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(inventoryManipulatorAdd.getSoldTotal().isFocusOwner()) {
                    autoCalculate(inventoryManipulatorAdd.getSold(), inventoryManipulatorAdd.getSoldTotal(),false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(inventoryManipulatorAdd.getSoldTotal().isFocusOwner()) {
                    autoCalculate(inventoryManipulatorAdd.getSold(), inventoryManipulatorAdd.getSoldTotal(),false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void documentListenerOfDamagedExpired(){
        inventoryManipulatorAdd.getDamage().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(inventoryManipulatorAdd.getDamage().isFocusOwner()) {
                    autoCalculate(inventoryManipulatorAdd.getDamageTotal(), inventoryManipulatorAdd.getDamage(),true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(inventoryManipulatorAdd.getDamage().isFocusOwner()) {
                    autoCalculate(inventoryManipulatorAdd.getDamageTotal(), inventoryManipulatorAdd.getDamage(),true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void documentListenerOfDamagedExpiredTotal(){
        inventoryManipulatorAdd.getDamageTotal().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(inventoryManipulatorAdd.getDamageTotal().isFocusOwner()) {
                    autoCalculate(inventoryManipulatorAdd.getDamage(), inventoryManipulatorAdd.getDamageTotal(),false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(inventoryManipulatorAdd.getDamageTotal().isFocusOwner()) {
                    autoCalculate(inventoryManipulatorAdd.getDamage(), inventoryManipulatorAdd.getDamageTotal(),false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private @NotNull BigDecimal multiplyToGetTotal(BigDecimal price, String input, @NotNull JTextField textField)  {
        if (isValidNumber(input)) return new BigDecimal(textField.getText()).multiply(price);
        return new BigDecimal("0");
    }

    private @NotNull BigDecimal divideToGetTotal(BigDecimal price, String input, @NotNull JTextField textField)  {
        int SCALE = 4;
        System.out.println(isValidNumber(input));
        if (isValidNumber(input)) return new BigDecimal(textField.getText())
                .divide(price,SCALE, RoundingMode.CEILING);
        return new BigDecimal("0");
    }

    private void autoCalculate(@NotNull JTextField textChange, JTextField textGet,boolean isMultiply) {

            String productID = (String) inventoryManipulatorAdd.getId().getSelectedItem();
            BigDecimal price = productController.get(productID).getPrice();
            if(isMultiply){
                textChange.setText(String.valueOf(multiplyToGetTotal(price,textGet.getText(),textGet)));
            }else {
                textChange.setText(String.valueOf(divideToGetTotal(price,textGet.getText(),textGet)));
            }
            calculateTotalSales();

    }

    private boolean isValidNumber(@NotNull String input) {
        if(input.equals("")) return false;
        int SOLD_LENGTH_TEXT = input.length();
        for(int i=0;i<SOLD_LENGTH_TEXT;i++) {
            if(Character.isAlphabetic(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
