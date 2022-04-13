package retail.controller.component.transaction;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductController;
import retail.controller.database.TransactionController;
import retail.customcomponent.jlist.CustomJList;
import retail.customcomponent.jtable.CustomJTableTransaction;
import retail.customcomponent.jtextfield.CustomJTextField;
import retail.model.TransactionReport;
import retail.model.TransactionReportItem;
import retail.util.constant.Constant;
import retail.util.constant.ConstantDialog;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.manipulator.transaction.panel.add.AddCard;
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

public class AddController {
    private final TransactionController transactionController = new TransactionController();
    private final ProductController productController = new ProductController();
    private final CustomJTableTransaction table;
    private final AddCard addCard;
    private final UserPanel userPanel;
    private final CustomJList reportListView;
    private final CustomJList reportListDelete;
    private CustomJTextField sold;
    private CustomJTextField soldTotal;
    private CustomJTextField damaged;
    private CustomJTextField damagedTotal;
    private CustomJTextField total;

    public AddController(@NotNull TopBorderPanel topBorderPanel, @NotNull BottomBorderPanel bottomBorderPanel) {
        table = bottomBorderPanel.getBottomMainCard().getTransactionCard().getAdd().getCenterTable();
        addCard = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getAddCard();
        userPanel = topBorderPanel.getUserPanel();
        reportListView = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getViewList();
        reportListDelete = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getDeleteList();
        
        jComponentInitialize();
        clear();
        addItemToTable();
        deleteRowFromTable();
        productIdItemListener();
        autoSetPrice();
        documentListenerOfAddPanelComponent();
        saveReportInDatabase();
        reportIdActionListener();
        addCard.getAdd().getReportId().setText(generateReportId()); // Generate initial report id at startup
    }
    
    private void jComponentInitialize() {
        sold = addCard.getAddTransaction().getSold();
        soldTotal = addCard.getAddTransaction().getSoldTotal();
        damaged = addCard.getAddTransaction().getDamage();
        damagedTotal = addCard.getAddTransaction().getDamageTotal();
        total = addCard.getAddTransaction().getTotal();
    }

    private void saveReportInDatabase() {
        addCard.getAdd().getSave().addActionListener(e -> {
            if(e.getSource() == addCard.getAdd().getSave()) {
                if(table.getRowCount() == 0) {
                    ConstantDialog.EMPTY_REPORT_TABLE();
                    return;
                }
                if(transactionController.ifReportExist( addCard.getAdd().getReportId().getText())) {
                    ConstantDialog.GENERATE_NEW_REPORT_ID();
                    return;
                }
                transactionController.addReport(createSalesReport(),getAllItemReportAtSalesTable());
                reportListDelete.populateSalesList(transactionController.getSalesReportList());
                reportListView.populateSalesList(transactionController.getSalesReportList());
                ConstantDialog.SAVED_REPORT();
            }
        });
    }

    private @NotNull ArrayList<TransactionReportItem> getAllItemReportAtSalesTable() {
        ArrayList<TransactionReportItem> itemReport = new ArrayList<>();
        int ROW = table.getRowCount();
        int COLUMN = table.getColumnCount();
        for(int i=0;i<ROW;i++) {
            String[] data = new String[8];
            for(int j=0;j<COLUMN;j++) {
                data[j] = table.getModel().getValueAt(i,j).toString();
            }
            itemReport.add(new TransactionReportItem(data[1],new BigDecimal(data[2]),new BigDecimal(data[3])
                    ,new BigDecimal(data[4]),new BigDecimal(data[5])
                    , new BigDecimal(data[6]),new BigDecimal(data[7])));
        }
        return itemReport;
    }

    private @NotNull TransactionReport createSalesReport() {
        String reportId = addCard.getAdd().getReportId().getText();
        String lastName = userPanel.getEmployeeLastName().getText();
        Date date = Date.valueOf(LocalDate.now());

        return new TransactionReport(reportId, date,lastName);
    }

    private void deleteRowFromTable() {
        addCard.getAdd().getDelete().addActionListener(e -> {
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
        addCard.getAdd().getAdd().addActionListener(e -> {
            if(isAllFieldValid()) {
                String[] data = getFieldData();
                table.addRow(new TransactionReportItem(data[0],new BigDecimal(data[1]),
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
        data[0] = (String) addCard.getAdd().getId().getSelectedItem();
        data[1] = addCard.getAdd().getPrice().getText();
        data[2] = sold.getText();
        data[3] = soldTotal.getText();
        data[4] = damaged.getText();
        data[5] = damagedTotal.getText();
        data[6] = total.getText();
        return data;
    }

    private boolean isAllFieldValid() {
        if(sold.getText().equals("")) return false;
        if(soldTotal.getText().equals("")) return false;
        if(damagedTotal.getText().equals("")) return false;
        if(damaged.getText().equals("")) return false;
        if(!isValidChar(sold.getText().toCharArray())) return false;
        if(!isValidChar(soldTotal.getText().toCharArray())) return false;
        if(!isValidChar(damaged.getText().toCharArray())) return false;
        return isValidChar(damagedTotal.getText().toCharArray());
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
        addCard.getAdd().getGenerateId().addActionListener(e -> addCard.getAdd().getReportId().setText(generateReportId()));
    }

    private @NotNull String generateReportId() {
        long id;
        String formatId = "";
        boolean flag = true;
        while(flag) {
            id = (long) (Math.random() * 1000000000000L);
            formatId = String.format("%013d",id);
            if(!transactionController.ifReportExist(formatId)) flag = false;
        }
        return "TR" + formatId;
    }

    private void clear() {
        addCard.getAdd().getClear().addActionListener(e -> {
            if(e.getSource() == addCard.getAdd().getClear()) {
                sold.setText("0");
                soldTotal.setText("0");
                damagedTotal.setText("0");
                damaged.setText("0");
                total.setText("0");
            }
        });
    }

    private void calculateTotalSales() {
        try {
            String sold = soldTotal.getText();
            String damExpTotal = damagedTotal.getText();
            if(sold.equals("")) sold = "0";
            if(damExpTotal.equals("")) damExpTotal = "0";
            BigDecimal totalAmount = new BigDecimal(sold).subtract(new BigDecimal(damExpTotal));
            total.setText(totalAmount.toString());
        }catch (NumberFormatException e) {
            ConstantDialog.INVALID_INPUT();
        }
    }

    private void autoSetPrice() {
        String productID = (String) addCard.getAdd().getId().getSelectedItem();
            if(Objects.isNull(productID)) return; // PREVENT NULL POINTER EXCEPTION WHEN REMOVING ALL ELEMENTS
        String price = String.valueOf(productController.get(productID).getPrice());
        addCard.getAdd().getPrice().setText(price);
    }

    private void productIdItemListener() {
        addCard.getAdd().getId().addItemListener(e -> {
            autoSetPrice();
            sold.setText("0");
            soldTotal.setText("0");
            damaged.setText("0");
            damagedTotal.setText("0");
            total.setText("0");
        });
    }

    private void documentListenerOfSold() {
        sold.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(sold.isFocusOwner()) {
                    autoCalculate(soldTotal, sold,true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(sold.isFocusOwner()) {
                    autoCalculate(soldTotal, sold,true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void documentListenerOfSoldTotal() {
        soldTotal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(soldTotal.isFocusOwner()) {
                    autoCalculate(sold, soldTotal,false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(soldTotal.isFocusOwner()) {
                    autoCalculate(sold, soldTotal,false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void documentListenerOfDamagedExpired(){
        damaged.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(damaged.isFocusOwner()) {
                    autoCalculate(damagedTotal, damaged,true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(damaged.isFocusOwner()) {
                    autoCalculate(damagedTotal, damaged,true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void documentListenerOfDamagedExpiredTotal(){
        damagedTotal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(damagedTotal.isFocusOwner()) {
                    autoCalculate(damaged, damagedTotal,false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(damagedTotal.isFocusOwner()) {
                    autoCalculate(damaged, damagedTotal,false);
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

            String productID = (String) addCard.getAdd().getId().getSelectedItem();
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
