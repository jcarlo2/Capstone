package retail.controller.viewcontroller;

import org.jetbrains.annotations.NotNull;
import retail.controller.logic.ITransactionAdd;
import retail.dao.AddTransactionDAO;
import retail.dao.ProductDAO;
import retail.getter.transaction.add.AddTransaction;
import retail.model.User;
import retail.shared.constant.ConstantDialog;
import retail.shared.customcomponent.jcombobox.JComboBoxProduct;
import retail.shared.customcomponent.jtable.JTableProduct;
import retail.shared.customcomponent.jtable.JTableTransaction;
import retail.shared.customcomponent.jtextfield.CustomJTextField;
import retail.shared.util.calculate.AutoSetPrice;
import retail.shared.util.calculate.CalculateDiscount;
import retail.shared.util.calculate.CalculateSold;
import retail.shared.util.update.JComboBoxUpdate;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TransactionAdd implements ITransactionAdd, JComboBoxUpdate, CalculateSold, CalculateDiscount, AutoSetPrice {
    private final AddTransactionDAO addTransactionDAO = new AddTransactionDAO();
    private final ProductDAO productDAO = new ProductDAO();
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

    public TransactionAdd(@NotNull User user, @NotNull AddTransaction addTransaction) {
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

        updateJComboBox();
        discountAmountDocumentListener();
        discountPercentageDocumentListener();
        soldDocumentListener();
        productIdActionPerformed();
        clearActionPerformed();
        generateIdActionPerformed();
        addActionPerformed();
        deleteActionPerformed();
        saveActionPerformed();
        reportId.setText(generateReportId(addTransactionDAO)); // Generate initial report id at startup
    }

    private void updateJComboBox() {
        Runnable runnable = () -> {
            if(isNotSameData(id, productDAO)) {
                SwingUtilities.invokeLater(() -> setProductIdList(id, productDAO));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,0,1, TimeUnit.SECONDS);
    }

    private void discountPercentageDocumentListener() {
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

    private void discountAmountDocumentListener() {
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

    private void soldDocumentListener() {
        sold.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(sold.isFocusOwner()) {
                    autoCalculate(soldTotal, sold,true, productDAO,id);
                    setDiscountAmount(discountTotal,discountPercentage,soldTotal);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(sold.isFocusOwner()) {
                    autoCalculate(soldTotal, sold,true, productDAO,id);
                    setDiscountAmount(discountTotal,discountPercentage,soldTotal);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void productIdActionPerformed() {
        id.addItemListener(e -> {
            autoSetPrice(productDAO,price,id);
            sold.setText("0");
            soldTotal.setText("0");
            discountPercentage.setText("0");
            discountTotal.setText("0");
        });
    }

    private void clearActionPerformed() {
        clear.addActionListener(e -> {
            sold.setText("0");
            soldTotal.setText("0");
            discountPercentage.setText("0");
            discountTotal.setText("0");
            totalAmount.setText("0");
        });
    }

    private void generateIdActionPerformed() {
        generateId.addActionListener(e -> reportId.setText(generateReportId(addTransactionDAO)));
    }

    private void addActionPerformed() {
        add.addActionListener(e -> {
            if(sold.getText().equals("0")) {
                ConstantDialog.EMPTY_FIELD();
                return;
            }
            if(isAllFieldValid(discountPercentage,discountTotal,sold,soldTotal)) {
                String[] data = getFieldData(id,price,sold,soldTotal,discountPercentage,discountTotal);
                transactionTable.addReportItem(createTransactionReportItem(data),"");
                fixNumberColumn(transactionTable);
                autoCalculateTotalAmount(transactionTable,totalAmount);
            } else {
                ConstantDialog.INVALID_INPUT();
            }
        });
    }

    private void deleteActionPerformed() {
        delete.addActionListener(e -> {
            int row = transactionTable.getSelectedRow();
            if(row > -1) {
                transactionTable.getModel().removeRow(row);
                fixNumberColumn(transactionTable);
                autoCalculateTotalAmount(transactionTable,totalAmount);
            }
        });
    }

    private void saveActionPerformed() {
        save.addActionListener(e -> {
            if(e.getSource() == save) {
                if(transactionTable.getRowCount() == 0) {
                    ConstantDialog.EMPTY_REPORT_TABLE();
                    return;
                }
                if(addTransactionDAO.isReportExist( reportId.getText())) {
                    ConstantDialog.GENERATE_NEW_REPORT_ID();
                    return;
                }
                addTransactionDAO.addReport(createTransactionReport(reportId,user,totalAmount), getAllItemReport(transactionTable));
                subtractItemsToInventory(transactionTable, productDAO);
                productTable.populateProductTable(productDAO.showProduct());
                ConstantDialog.SAVED_REPORT();
            }
        });
    }

}
