package retail.controller.tab.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductDatabase;
import retail.controller.database.TransactionDatabase;
import retail.shared.customcomponent.jtable.JTableProduct;
import retail.shared.customcomponent.jtable.JTableTransaction;
import retail.shared.customcomponent.jtextfield.CustomJTextField;
import retail.model.Product;
import retail.model.TransactionReport;
import retail.model.TransactionReportItem;
import retail.shared.constant.ConstantDialog;
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.bot.transaction.center.add.AddTransaction;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.AddCard;
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
    private final TransactionDatabase transactionDatabase = new TransactionDatabase();
    private final ProductDatabase productDatabase = new ProductDatabase();
    private final JTableTransaction table;
    private final AddCard addCard;
    private final UserPanel userPanel;
    private final JTableProduct productTable;
    private CustomJTextField sold;
    private CustomJTextField soldTotal;
    private CustomJTextField discountPercentage;
    private CustomJTextField discountTotal;
    private CustomJTextField totalAmount;

    public AddController(@NotNull TopBorderPanel topBorderPanel, @NotNull BottomBorderPanel bottomBorderPanel) {
        table = bottomBorderPanel.getBottomMainCard().getTransactionCard().getAddCard().getAddTransaction().getCenterTable();
        addCard = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard();
        userPanel = topBorderPanel.getUserPanel();
        productTable = bottomBorderPanel.getBottomMainCard().getInventoryCard().getProduct().getTable();

        jComponentInitialize(bottomBorderPanel.getBottomMainCard().getTransactionCard().getAddCard().getAddTransaction());
        clear();
        addItemToTable();
        deleteRowFromTable();
        productIdItemListener();
        autoSetPrice();
        documentListenerOfSold();
        documentListenerOfSoldTotal();
        documentListenerOfDiscountAmount();
        documentListenerOfDiscountPercentage();
        saveReportInDatabase();
        generateReportIdActionListener();
        addCard.getAdd().getReportId().setText(generateReportId()); // Generate initial report id at startup
    }
    
    private void jComponentInitialize(@NotNull AddTransaction addTransaction) {
        sold = addCard.getAddTransaction().getSold();
        soldTotal = addCard.getAddTransaction().getSoldTotal();
        discountPercentage = addCard.getAddTransaction().getDiscountPercentage();
        discountTotal = addCard.getAddTransaction().getDiscountTotal();
        totalAmount = addTransaction.getTotalAmount();
    }

    private void saveReportInDatabase() {
        addCard.getAdd().getSave().addActionListener(e -> {
            if(e.getSource() == addCard.getAdd().getSave()) {
                if(table.getRowCount() == 0) {
                    ConstantDialog.EMPTY_REPORT_TABLE();
                    return;
                }
                if(transactionDatabase.isReportExist( addCard.getAdd().getReportId().getText())) {
                    ConstantDialog.GENERATE_NEW_REPORT_ID();
                    return;
                }
                transactionDatabase.addReport(createTransactionReport(),getAllItemReportAtSalesTable());
                subtractTransactItemsToInventory();
                productTable.populateProductTable(productDatabase.showProduct());
                ConstantDialog.SAVED_REPORT();
            }
        });
    }

    private void subtractTransactItemsToInventory() {
        for(TransactionReportItem item : getAllItemReportAtSalesTable()) {
            Product product = productDatabase.get(item.getProductId());
            product.setQuantityPerPieces(product.getQuantityPerPieces() - item.getSold());
            productDatabase.updateProductQuantity(product.getId(),product.getQuantityPerPieces());
        }
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
            itemReport.add(new TransactionReportItem(data[1],Double.parseDouble(data[2]),Integer.parseInt(data[3])
                    ,Double.parseDouble(data[4]),Double.parseDouble(data[5]),
                    Double.parseDouble(data[6]),new BigDecimal(data[7])));
        }
        return itemReport;
    }

    private @NotNull TransactionReport createTransactionReport() {
        String reportId = addCard.getAdd().getReportId().getText();
        String lastName = userPanel.getEmployeeLastName().getText();
        Date date = Date.valueOf(LocalDate.now());
        BigDecimal total = new BigDecimal(totalAmount.getText());

        return new TransactionReport(reportId, date,null,lastName,total);
    }

    private void autoCalculateTotalAmount() {
        if(table.getRowCount() < 1) return;
        BigDecimal num = new BigDecimal("0");
            for(int i=0;i<table.getRowCount();i++) {
               String value = (String) table.getValueAt(i,7);
               num = num.add(new BigDecimal(value));
            }
        totalAmount.setText(num.toString());
    }

    private void deleteRowFromTable() {
        addCard.getAdd().getDelete().addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row > -1) {
                table.getModel().removeRow(row);
                fixNumberColumn();
                autoCalculateTotalAmount();
            }
        });
    }

    private void fixNumberColumn() {
        for(int i=0;i<table.getRowCount();i++) {
            table.setValueAt(i+1, i,0);
        }
    }

    private void addItemToTable() {
        addCard.getAdd().getAdd().addActionListener(e -> {
            if(sold.getText().equals("0")) {
                ConstantDialog.EMPTY_FIELD();
                return;
            }
            if(isAllFieldValid()) {
                String[] data = getFieldData();
                table.addReportItem(new TransactionReportItem(data[0],Double.parseDouble(data[1]),
                        Integer.parseInt(data[2]),Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]),Double.parseDouble(data[5]),
                        new BigDecimal(data[6])),"","");
                        fixNumberColumn();
                        autoCalculateTotalAmount();
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
        data[4] = discountPercentage.getText();
        data[5] = discountTotal.getText();
        data[6] = String.valueOf(new BigDecimal(soldTotal.getText()).subtract(new BigDecimal(discountTotal.getText())));
        return data;
    }

    private boolean isAllFieldValid() {
        return isValidNumber(discountPercentage.getText()) && isValidNumber(discountTotal.getText())
                    && isValidNumber(sold.getText()) && isValidNumber(soldTotal.getText());
    }

    private void generateReportIdActionListener() {
        addCard.getAdd().getGenerateId().addActionListener(e -> addCard.getAdd().getReportId().setText(generateReportId()));
    }

    private @NotNull String generateReportId() {
        long id;
        String formatId = "";
        boolean flag = true;
            while(flag) {
                id = (long) (Math.random() * 1000000000000L);
                formatId = String.format("%013d",id);
                if(!transactionDatabase.isReportExist(formatId)) flag = false;
            }
        return "TR" + formatId + "-A0";
    }

    private void clear() {
        addCard.getAdd().getClear().addActionListener(e -> {
            if(e.getSource() == addCard.getAdd().getClear()) {
                sold.setText("0");
                soldTotal.setText("0");
                discountPercentage.setText("0");
                discountTotal.setText("0");
                totalAmount.setText("0");
            }
        });
    }

    private void autoSetPrice() {
        String productID = (String) addCard.getAdd().getId().getSelectedItem();
            if(Objects.isNull(productID)) return; // PREVENT NULL POINTER EXCEPTION WHEN REMOVING ALL ELEMENTS
        String price = String.valueOf(productDatabase.get(productID).getPrice());
        addCard.getAdd().getPrice().setText(price);
    }

    private void productIdItemListener() {
        addCard.getAdd().getId().addItemListener(e -> {
            autoSetPrice();
            sold.setText("0");
            soldTotal.setText("0");
            discountPercentage.setText("0");
            discountTotal.setText("0");
        });
    }

    private @NotNull BigDecimal calculateDiscountPercentage() {
        if(soldTotal.getText().equals("")) return BigDecimal.ZERO;
        final BigDecimal percentage = new BigDecimal(discountPercentage.getText());
        BigDecimal ONE_HUNDRED = new BigDecimal("100");
        BigDecimal total;
            try {
                total = percentage.divide(ONE_HUNDRED,4,RoundingMode.HALF_EVEN);
            }
            catch (ArithmeticException e) {
                return BigDecimal.ZERO;
            }
        return total.multiply(new BigDecimal(soldTotal.getText())).setScale(2,RoundingMode.HALF_EVEN);
    }

    private @NotNull BigDecimal calculateDiscountAmount() {
        final BigDecimal amount = new BigDecimal(discountTotal.getText());
        BigDecimal ONE_HUNDRED = new BigDecimal("100");
        BigDecimal total;
            try {
                total = amount.divide(new BigDecimal(soldTotal.getText()),4,RoundingMode.HALF_EVEN );
            }catch (ArithmeticException e) {
                return BigDecimal.ZERO;
            }
        return total.multiply(ONE_HUNDRED).setScale(2,RoundingMode.HALF_EVEN);
    }

    private void documentListenerOfDiscountPercentage() {
        discountPercentage.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(discountPercentage.isFocusOwner()) {
                    setDiscountAmount();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(discountPercentage.isFocusOwner()) {
                    setDiscountAmount();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

    }

    private void setDiscountAmount() {
        final BigDecimal discount = isValidNumber(discountPercentage.getText()) ? calculateDiscountPercentage() : new BigDecimal("0");
        discountTotal.setText(discount.toString());
    }

    private void documentListenerOfDiscountAmount() {
        discountTotal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(discountTotal.isFocusOwner()) {
                    final BigDecimal discount = isValidNumber(discountTotal.getText()) ? calculateDiscountAmount() : new BigDecimal("0");
                    discountPercentage.setText(discount.toString());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(discountTotal.isFocusOwner()) {
                    final BigDecimal discount = isValidNumber(discountTotal.getText()) ? calculateDiscountAmount() : new BigDecimal("0");
                    discountPercentage.setText(discount.toString());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void documentListenerOfSold() {
        sold.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(sold.isFocusOwner()) {
                    autoCalculate(soldTotal, sold,true);
                    setDiscountAmount();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(sold.isFocusOwner()) {
                    autoCalculate(soldTotal, sold,true);
                    setDiscountAmount();
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
                    if(!isValidNumber(soldTotal.getText())) return;
                    autoCalculate(sold, soldTotal,false);
                    setDiscountAmount();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(soldTotal.isFocusOwner()) {
                    if(!isValidNumber(soldTotal.getText())) return;
                    autoCalculate(sold, soldTotal,false);
                    setDiscountAmount();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private @NotNull BigDecimal multiplyToGetTotal(BigDecimal price, String input)  {
        if (isValidNumber(input)) return new BigDecimal(input).multiply(price);
        return new BigDecimal("0");
    }

    private @NotNull BigDecimal divideToGetTotal(BigDecimal price, String input)  {
        int SCALE = 4;
        if (isValidNumber(input)) return new BigDecimal(input).divide(price,SCALE, RoundingMode.CEILING);
        return new BigDecimal("0");
    }

    private void autoCalculate(@NotNull JTextField textChange, JTextField textGet,boolean isMultiply) {
        String productID = (String) addCard.getAdd().getId().getSelectedItem();
        BigDecimal price = productDatabase.get(productID).getPrice();
        String str = isMultiply ? String.valueOf(multiplyToGetTotal(price,textGet.getText()))
                                : String.valueOf(divideToGetTotal(price,textGet.getText()));
        textChange.setText(str);
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
}
