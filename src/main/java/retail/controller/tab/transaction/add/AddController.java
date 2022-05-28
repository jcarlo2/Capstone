package retail.controller.tab.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.AddTransactionDatabase;
import retail.controller.database.ProductDatabase;
import retail.model.User;
import retail.shared.constant.ConstantDialog;
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
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.AddCard;

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
    private final JTableTransaction table;
    private final User user;
    private final AddCard addCard;
    private final JTableProduct productTable;
    private final CustomJTextField sold;
    private final CustomJTextField soldTotal;
    private final CustomJTextField discountPercentage;
    private final CustomJTextField discountTotal;
    private final CustomJTextField totalAmount;

    public AddController(@NotNull User user, @NotNull BottomBorderPanel bottomBorderPanel) {
        this.user = user;
        table = bottomBorderPanel.getBottomMainCard().getTransactionCard().getAddCard().getAddTransaction().getCenterTable();
        addCard = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard();
        productTable = bottomBorderPanel.getBottomMainCard().getInventoryCard().getProduct().getTable();
        sold = addCard.getAddTransaction().getSold();
        soldTotal = addCard.getAddTransaction().getSoldTotal();
        discountPercentage = addCard.getAddTransaction().getDiscountPercentage();
        discountTotal = addCard.getAddTransaction().getDiscountTotal();
        totalAmount = bottomBorderPanel.getBottomMainCard().getTransactionCard().getAddCard().getAddTransaction().getTotalAmount();

        clear();
        addItemToTable();
        deleteRowFromTable();
        productIdItemListener();
        autoSetPrice(productDatabase,addCard.getAdd().getPrice(),addCard.getAdd().getId());
        documentListenerOfSold();
        documentListenerOfDiscountAmount();
        documentListenerOfDiscountPercentage();
        saveReportInDatabase();
        generateReportIdActionListener();
        updateJComboBox();
        addCard.getAdd().getReportId().setText(generateReportId()); // Generate initial report id at startup
    }

    private void updateJComboBox() {
        Runnable runnable = () -> {
            if(isNotSameData(addCard.getAdd().getId(), productDatabase)) {
                SwingUtilities.invokeLater(() -> setProductIdList(addCard.getAdd().getId(),productDatabase));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,0,1, TimeUnit.SECONDS);
    }

    private void saveReportInDatabase() {
        addCard.getAdd().getSave().addActionListener(e -> {
            if(e.getSource() == addCard.getAdd().getSave()) {
                if(table.getRowCount() == 0) {
                    ConstantDialog.EMPTY_REPORT_TABLE();
                    return;
                }
                if(addTransactionDatabase.isReportExist( addCard.getAdd().getReportId().getText())) {
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
        int ROW = table.getRowCount();
        int COLUMN = table.getColumnCount();
        for(int i=0;i<ROW;i++) {
            String[] data = new String[8];
            for(int j=0;j<COLUMN;j++) {
                data[j] = table.getModel().getValueAt(i,j).toString();
            }
            itemReport.add(new TransactionReportItem(data[1],Double.parseDouble(data[2]),Double.parseDouble(data[3])
                    ,Double.parseDouble(data[4]),Double.parseDouble(data[5]),
                    Double.parseDouble(data[6]),new BigDecimal(data[7])));
        }
        return itemReport;
    }

    private @NotNull TransactionReport createTransactionReport() {
        String reportId = addCard.getAdd().getReportId().getText();
        String lastName = user.getLastName();
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
                System.out.println(data[2]);
                table.addReportItem(new TransactionReportItem(data[0],Double.parseDouble(data[1]),
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
                if(!addTransactionDatabase.isReportExist(formatId)) flag = false;
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

    private void productIdItemListener() {
        addCard.getAdd().getId().addItemListener(e -> {
            autoSetPrice(productDatabase,addCard.getAdd().getPrice(),addCard.getAdd().getId());
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
                    autoCalculate(soldTotal, sold,true,productDatabase,addCard.getAdd().getId());
                    setDiscountAmount(discountTotal,discountPercentage,soldTotal);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(sold.isFocusOwner()) {
                    autoCalculate(soldTotal, sold,true,productDatabase,addCard.getAdd().getId());
                    setDiscountAmount(discountTotal,discountPercentage,soldTotal);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }
}
