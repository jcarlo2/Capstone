package retail.controller.tab.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.AddTransactionDatabase;
import retail.controller.database.ProductDatabase;
import retail.controller.getter.transaction.add.AddTransaction;
import retail.model.User;
import retail.shared.constant.ConstantDialog;
import retail.shared.customcomponent.jcombobox.JComboBoxProduct;
import retail.shared.customcomponent.jtable.JTableProduct;
import retail.shared.customcomponent.jtable.JTableTransaction;
import retail.shared.customcomponent.jtextfield.CustomJTextField;
import retail.shared.pojo.Product;
import retail.shared.pojo.TransactionReport;
import retail.shared.pojo.TransactionReportItem;
import retail.shared.util.calculate.AutoSetPrice;
import retail.shared.util.calculate.CalculateDiscount;
import retail.shared.util.calculate.CalculateSold;
import retail.shared.util.update.JComboBoxUpdate;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AddController implements JComboBoxUpdate, CalculateSold, CalculateDiscount, AutoSetPrice {
    private final AddTransactionDatabase addTransactionDatabase = new AddTransactionDatabase();
    private final ProductDatabase productDatabase = new ProductDatabase();
    private final User user;

    // MANIPULATOR
    private final JComboBoxProduct id;
    private final CustomJTextField price;
    private final CustomJTextField sold;
    private final CustomJTextField soldTotal;
    private final CustomJTextField reportId;
    private final CustomJTextField discountPercentage;
    private final CustomJTextField discountTotal;
    private final JButton add;
    private final JButton clear;
    private final JButton delete;
    private final JButton save;
    private final JButton generateId;

    // CENTER
    private final JTableProduct productTable;
    private final JTableTransaction transactionTable;
    private final CustomJTextField totalAmount;

    public AddController(@NotNull User user, @NotNull AddTransaction addTransaction) {
        this.user = user;

        id = addTransaction.getId();
        price = addTransaction.getPrice();
        sold = addTransaction.getSold();
        soldTotal = addTransaction.getSoldTotal();
        reportId = addTransaction.getReportId();
        discountPercentage = addTransaction.getDiscountPercentage();
        discountTotal = addTransaction.getDiscountTotal();
        add = addTransaction.getAdd();
        clear = addTransaction.getClear();
        delete = addTransaction.getDelete();
        save = addTransaction.getSave();
        generateId = addTransaction.getGenerateId();

        transactionTable = addTransaction.getTable();
        productTable = addTransaction.getProductTable();
        totalAmount = addTransaction.getTotalAmount();

        clear();
        addItemToTable();
        deleteRowFromTable();
        productIdItemListener();
        autoSetPrice(productDatabase,price,id);
        documentListenerOfSold();
        documentListenerOfDiscountAmount();
        documentListenerOfDiscountPercentage();
        saveReportInDatabase();
        generateReportIdActionListener();
        updateJComboBox();
        reportId.setText(generateReportId()); // Generate initial report id at startup
    }

    private void updateJComboBox() {
        Runnable runnable = () -> {
            if(isNotSameData(id, productDatabase)) {
                SwingUtilities.invokeLater(() -> setProductIdList(id,productDatabase));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,0,1, TimeUnit.SECONDS);
    }

    private void saveReportInDatabase() {
        save.addActionListener(e -> {
            if(e.getSource() == save) {
                if(transactionTable.getRowCount() == 0) {
                    ConstantDialog.EMPTY_REPORT_TABLE();
                    return;
                }
                if(addTransactionDatabase.isReportExist( reportId.getText())) {
                    ConstantDialog.GENERATE_NEW_REPORT_ID();
                    return;
                }
                addTransactionDatabase.addReport(createTransactionReport(),getAllItemReportAtSalesTable());
                subtractTransactItemsToInventory();
                productTable.populateProductTable(productDatabase.showProduct());
                ConstantDialog.SAVED_REPORT();
            }
        });
    }

    private void subtractTransactItemsToInventory() {
        for(TransactionReportItem item : getAllItemReportAtSalesTable()) {
            Product product = productDatabase.get(item.getProductId());
            productDatabase.updateProductQuantity(product.getId(),item.getSold() * -1);
        }
    }

    private @NotNull ArrayList<TransactionReportItem> getAllItemReportAtSalesTable() {
        ArrayList<TransactionReportItem> itemReport = new ArrayList<>();
        int ROW = transactionTable.getRowCount();
        int COLUMN = transactionTable.getColumnCount();
        for(int i=0;i<ROW;i++) {
            String[] data = new String[8];
            for(int j=0;j<COLUMN;j++) {
                data[j] = transactionTable.getModel().getValueAt(i,j).toString();
            }
            itemReport.add(new TransactionReportItem(data[1],Double.parseDouble(data[2]),Double.parseDouble(data[3])
                    ,Double.parseDouble(data[4]),Double.parseDouble(data[5]),
                    Double.parseDouble(data[6]),new BigDecimal(data[7])));
        }
        return itemReport;
    }

    private @NotNull TransactionReport createTransactionReport() {
        String reportId = this.reportId.getText();
        String lastName = user.getLastName();
        Date date = Date.valueOf(LocalDate.now());
        BigDecimal total = new BigDecimal(totalAmount.getText());

        return new TransactionReport(reportId, date,null,lastName,total);
    }

    private void autoCalculateTotalAmount() {
        if(transactionTable.getRowCount() < 1) return;
        BigDecimal num = new BigDecimal("0");
            for(int i = 0; i< transactionTable.getRowCount(); i++) {
               String value = (String) transactionTable.getValueAt(i,7);
               num = num.add(new BigDecimal(value));
            }
        totalAmount.setText(num.toString());
    }

    private void deleteRowFromTable() {
        delete.addActionListener(e -> {
            int row = transactionTable.getSelectedRow();
            if(row > -1) {
                transactionTable.getModel().removeRow(row);
                fixNumberColumn();
                autoCalculateTotalAmount();
            }
        });
    }

    private void fixNumberColumn() {
        for(int i = 0; i< transactionTable.getRowCount(); i++) {
            transactionTable.setValueAt(i+1, i,0);
        }
    }

    private void addItemToTable() {
        add.addActionListener(e -> {
            if(sold.getText().equals("0")) {
                ConstantDialog.EMPTY_FIELD();
                return;
            }
            if(isAllFieldValid()) {
                String[] data = getFieldData();
                transactionTable.addReportItem(new TransactionReportItem(data[0],Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]),Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]),Double.parseDouble(data[5]),
                        new BigDecimal(data[6])),"");
                        fixNumberColumn();
                        autoCalculateTotalAmount();
            } else {
                ConstantDialog.INVALID_INPUT();
           }
        });
    }

    private String @NotNull [] getFieldData() {
        String[] data = new String[7];
        data[0] = (String) id.getSelectedItem();
        data[1] = price.getText();
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
        generateId.addActionListener(e -> reportId.setText(generateReportId()));
    }

    private @NotNull String generateReportId() {
        long id;
        String formatId = "";
        boolean flag = true;
            while(flag) {
                id = (long) (Math.random() * 1000000000000L);
                formatId = String.format("%013d",id);
                if(!addTransactionDatabase.isReportExist(formatId)) flag = false;
            }
        return "TR" + formatId + "-A0";
    }

    private void clear() {
        clear.addActionListener(e -> {
            sold.setText("0");
            soldTotal.setText("0");
            discountPercentage.setText("0");
            discountTotal.setText("0");
            totalAmount.setText("0");
        });
    }

    private void productIdItemListener() {
        id.addItemListener(e -> {
            autoSetPrice(productDatabase,price,id);
            sold.setText("0");
            soldTotal.setText("0");
            discountPercentage.setText("0");
            discountTotal.setText("0");
        });
    }

    private void documentListenerOfDiscountPercentage() {
        discountPercentage.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(discountPercentage.isFocusOwner()) {
                    setDiscountAmount(discountTotal,discountPercentage,soldTotal);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(discountPercentage.isFocusOwner()) {
                    setDiscountAmount(discountTotal,discountPercentage,soldTotal);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

    }

    private void documentListenerOfDiscountAmount() {
        discountTotal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(discountTotal.isFocusOwner()) {
                    final BigDecimal discount = isValidNumber(discountTotal.getText()) ? calculateDiscountAmount(discountTotal,soldTotal) : new BigDecimal("0");
                    discountPercentage.setText(discount.toString());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(discountTotal.isFocusOwner()) {
                    final BigDecimal discount = isValidNumber(discountTotal.getText()) ? calculateDiscountAmount(discountTotal,soldTotal) : new BigDecimal("0");
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
                    autoCalculate(soldTotal, sold,true,productDatabase,id);
                    setDiscountAmount(discountTotal,discountPercentage,soldTotal);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(sold.isFocusOwner()) {
                    autoCalculate(soldTotal, sold,true,productDatabase,id);
                    setDiscountAmount(discountTotal,discountPercentage,soldTotal);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }
}
