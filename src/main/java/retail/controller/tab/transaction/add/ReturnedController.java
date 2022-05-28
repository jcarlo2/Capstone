package retail.controller.tab.transaction.add;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retail.controller.database.AddTransactionDatabase;
import retail.controller.database.ProductDatabase;
import retail.controller.database.ReturnedTransactionDatabase;
import retail.controller.getter.transaction.add.ReturnTransaction;
import retail.model.User;
import retail.shared.constant.ConstantDialog;
import retail.shared.customcomponent.CustomJDialog;
import retail.shared.customcomponent.jlist.CustomJList;
import retail.shared.customcomponent.jtable.JTableTransaction;
import retail.shared.customcomponent.jtextfield.CustomJTextField;
import retail.shared.pojo.ReturnTransactionReport;
import retail.shared.pojo.TransactionReport;
import retail.shared.pojo.TransactionReportItem;
import retail.shared.util.calculate.AutoSetPrice;
import retail.shared.util.calculate.CalculateDiscount;
import retail.shared.util.calculate.CalculateSold;
import retail.shared.util.calculate.ValidNumberChecker;
import retail.shared.util.update.JComboBoxUpdate;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static retail.shared.constant.ConstantDialog.*;

public class ReturnedController implements JComboBoxUpdate, CalculateSold, CalculateDiscount, AutoSetPrice, ValidNumberChecker {
    private final AddTransactionDatabase addTransactionDB = new AddTransactionDatabase();
    private final ReturnedTransactionDatabase returnedTransactionDB = new ReturnedTransactionDatabase();
    private final ProductDatabase productDB = new ProductDatabase();
    private final User user;
    private final CustomJDialog returnDialog = new CustomJDialog();

    // MANIPULATOR
    private final CustomJList list;
    private final CustomJTextField reportId;
    private final JButton addAll;
    private final JButton add;
    private final JButton delete;
    private final JButton deleteAll;
    private final JButton save;
    private final CustomJTextField newReportId;

    // CENTER
    private final JTableTransaction topTable;
    private final CustomJTextField topTotal;
    private final JTableTransaction botTable;
    private final CustomJTextField credit;
    private final CustomJTextField newTotal;

    private int initialCount;
    private double initialCredit;
    private String selectedId;

    public ReturnedController(ReturnTransaction returnTransaction, User user) {
        this.user = user;
        list = returnTransaction.getList();
        reportId = returnTransaction.getReportId();
        addAll = returnTransaction.getAddAll();
        add = returnTransaction.getAdd();
        delete = returnTransaction.getDelete();
        deleteAll = returnTransaction.getDeleteAll();
        save = returnTransaction.getSave();
        newReportId = returnTransaction.getNewReportId();

        topTable = returnTransaction.getTopTable();
        botTable = returnTransaction.getBotTable();
        topTotal = returnTransaction.getTopTotal();
        credit = returnTransaction.getCredit();
        newTotal = returnTransaction.getNewTotal();

        autoSetPrice(productDB,returnDialog.getPrice(),returnDialog.getProduct());
        setTotalAmount(botTable,topTotal);
        setReturnDialog();
        documentListenerOfDiscountAmount();
        documentListenerOfDiscountPercentage();
        documentListenerOfSold();
        eventListenerForList();
        eventListenerForTable();
        search();
        saveReport();
        addAllButtonListener();
        deleteAllButtonListener();
        deleteAllButtonListener(botTable);
        list.populateTransactionList(addTransactionDB.getTransactionReportList()); // POPULATE LIST AT START UP
    }

    private void setReturnDialog() {
        update();
        saveItem();
        returnedDialogCancelListener();
        clearReturnDialogField();
        productIdItemListener();
        itemCountListener();
        botTableChangeListener();
    }

    private void deleteAllButtonListener() {
        deleteAll.addActionListener(e -> deleteAllItem());
    }

    private void update() {
        Runnable runnable = () -> SwingUtilities.invokeLater(() -> {
            if(isNotSameData(returnDialog.getProduct(), productDB)) {
                setProductIdList(returnDialog.getProduct(), productDB);
            }
        });
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,0,1, TimeUnit.SECONDS);
    }

    // GET PRODUCT COUNT AND REPORT AT NULL INVENTORY
    private void saveReport() {
        save.addActionListener( e -> {
            if(topTable.getRowCount() != 0) {
                ConstantDialog.SAVE_FAILED();
                return;
            }
            returnedTransactionDB.addReport(createReport(), getAllItem());
            reflectReturnToInventory();
        });
    }

    private @NotNull ReturnTransactionReport createReport() {
        String id = newReportId.getText();
        String oldId = selectedId;
        String user = this.user.getLastName();
        BigDecimal credit = new BigDecimal(this.credit.getText());
        BigDecimal totalAmount = new BigDecimal(newTotal.getText());

        return new ReturnTransactionReport(id,oldId,null,null,user,totalAmount,credit);
    }

    private @NotNull ArrayList<TransactionReportItem> getAllItem() {
        ArrayList<TransactionReportItem> itemList = new ArrayList<>();
        int row = botTable.getRowCount();
        int column = botTable.getColumnCount();
        for(int i=0;i<row;i++) {
            String[] data = new String[column];
            for(int j=0;j<column;j++) {
                data[j] = botTable.getValueAt(i,j).toString();
            }
            itemList.add(new TransactionReportItem(data[1],Double.parseDouble(data[2]),Double.parseDouble(data[3]),
                    Double.parseDouble(data[4]),Double.parseDouble(data[5]),Double.parseDouble(data[6]),new BigDecimal(data[7])));
        }
        return itemList;
    }


    private void saveItem() {
        returnDialog.getSave().addActionListener(e-> {
            String[] data = getSelectedData(topTable);
            String[] returnData = getReturnDialogData();
            int count = (int) Double.parseDouble(returnDialog.getCount().getText());
            if(count == initialCount) {
                clear();
                returnDialog.setVisible(false);
                INVALID_INPUT();
                return;
            }
            for(int i=1;i<returnData.length;i++) {
                if(!isValidNumber(returnData[i])) {
                    clear();
                    returnDialog.setVisible(false);
                    INVALID_INPUT();
                    return;
                }
            }
            saveProcess(data, returnData, false);
            removeRowAfterItemSave();
        });
    }

    private void saveProcess(String[] data, String[] returnData,boolean isSame) {
        if(data == null) return;
        String count = returnDialog.getProductCount().getText();
        if(!isSame) {
            System.arraycopy(returnData, 0, data, 1, returnData.length-1);
            clear();
            returnDialog.setVisible(false);
        }
        botTable.addReportItem(createTransactionReportItem(data),count);
        fixNumberColumn(botTable);
    }

    private void removeRowAfterItemSave() {
        int row = topTable.getSelectedRow();
        if(row == -1) return;
        topTable.getModel().removeRow(row);
    }

    private void deleteAllButtonListener(JTableTransaction table) {
        delete.addActionListener(e -> deleteSelectedItem(table));
    }

    private void deleteSelectedItem(@NotNull JTableTransaction table) {
        int row = table.getSelectedRow();
        if (row == -1) return;
        recoverItem();
        table.getModel().removeRow(row);
        fixNumberColumn(table);
    }

    private void recoverItem() {
        ArrayList<TransactionReportItem> itemList = addTransactionDB.getAllTransactionReportItem(selectedId);
        String[] data = getSelectedData(botTable);
        if(data == null) return;
        for(TransactionReportItem item : itemList) {
            if(item.getProductId().equals(data[1])) {
                topTable.addReportItem(item,"");
                fixNumberColumn(topTable);
                return;
            }
        }
    }

    private void addAllButtonListener() {
        addAll.addActionListener( e -> addAllItem());
    }

    private void addAllItem() {
        if(ADD_ALL() == 0) {
            for(int i=0;i<topTable.getRowCount();i++) {
                String[] data = new String[botTable.getColumnCount()];
                for(int j=0;j<data.length;j++) {
                    data[j] = topTable.getValueAt(i,j).toString();
                }
                if(topTable.getValueAt(i, 8).equals("--")) saveProcess(data, null, true);
            }
            removeRowWithReason();
        }
    }

    private void removeRowWithReason() {
        int row = topTable.getRowCount();
        for(int i=0;i<row;i++) {
            if(topTable.getValueAt(i, 8).equals("--")) {
                topTable.getModel().removeRow(i);
                i = -1;
                row = topTable.getRowCount();
            }
        }
    }

    private void reflectReturnToInventory() {
        ArrayList<TransactionReportItem> itemList = addTransactionDB.getAllTransactionReportItem(selectedId);
            for(TransactionReportItem item : itemList) {
                compareProductToUpdate(item);
            }
    }

    private void compareProductToUpdate(TransactionReportItem item) {
        int row = botTable.getRowCount();
        String id;
        String sold;
        for(int i=0;i<row;i++) {
            id = botTable.getValueAt(i,1).toString();
            sold = botTable.getValueAt(i,3).toString();
            if(item.getProductId().equals(id)) {
                updateProduct(item,id,sold,i);
                return;
            }
        }
    }

    private void updateProduct(@NotNull TransactionReportItem item, String id, String sold, int i) {
        double num;
        String returnValue;
        returnValue = botTable.getValueAt(i,8).toString();
        num = item.getSold() - Double.parseDouble(returnValue);
        num = Double.parseDouble(sold) - num;
        productDB.updateProductQuantity(id,num * -1);
    }

    @Contract("_ -> new")
    private @NotNull TransactionReportItem createTransactionReportItem(String @NotNull [] data) {
        return new TransactionReportItem(data[1],Double.parseDouble(data[2]), Double.parseDouble(data[3]),
                Double.parseDouble(data[4]),Double.parseDouble(data[5]),Double.parseDouble(data[6]),new BigDecimal(data[7]));
    }

    private String @NotNull [] getReturnDialogData() {
        String[] data = new String[8];
        data[0] = Objects.requireNonNull(returnDialog.getProduct().getSelectedItem()).toString();
        data[1] = returnDialog.getPrice().getText();
        data[2] = returnDialog.getSold().getText();
        data[3] = returnDialog.getSoldTotal().getText();
        data[4] = returnDialog.getDiscountPercentage().getText();
        data[5] = returnDialog.getDiscountTotal().getText();
        data[6] = returnDialog.getTotalAmount().getText();
        data[7] = returnDialog.getProductCount().getText();
        return data;
    }

    private void productIdItemListener() {
        returnDialog.getProduct().addItemListener(e -> {
            autoSetPrice(productDB,returnDialog.getPrice(),returnDialog.getProduct());
            returnDialog.getSold().setText("0");
            returnDialog.getSoldTotal().setText("0");
            returnDialog.getDiscountPercentage().setText("0");
            returnDialog.getDiscountTotal().setText("0");
        });
    }

    private void returnedDialogCancelListener() {
        returnDialog.getCancel().addActionListener(e -> {
            returnDialog.setVisible(false);
            clear();
        });
    }

    private void itemCountListener() {
        returnDialog.getProductCount().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(returnDialog.getProductCount().isFocusOwner()) {
                    String count = returnDialog.getProductCount().getText();
                    if(count.equals("")) returnDialog.getProductCount().setText("0");
                    calculateReturnQuantity(returnDialog.getProductCount().getText());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(returnDialog.getProductCount().isFocusOwner()) {
                    String count = returnDialog.getProductCount().getText();
                    if(count.equals("")) {
                        returnDialog.getCount().setText(String.valueOf(initialCount));
                        returnDialog.getSold().setText(String.valueOf(initialCount));
                    }
                    calculateReturnQuantity(returnDialog.getProductCount().getText());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void calculateReturnQuantity(@NotNull String count) {
        if(count.equals("")) return;
        if(isValidNumber(count)) {
            BigDecimal returned = new BigDecimal(count);
            count = String.valueOf(BigDecimal.valueOf(initialCount).subtract(returned));
            returnDialog.getSold().setText(count);
            returnDialog.getCount().setText(count);
        }
    }

    private void autoSetProduct(String[] data) {
        if(data == null) return;
        initialCount = (int) Math.floor(Double.parseDouble(data[3]));
        returnDialog.getProductId().setText(data[1]);
        returnDialog.getCount().setText(data[3]);
        returnDialog.getProductCount().setText("0");
        returnDialog.getProduct().getModel().setSelectedItem(data[1]);
        returnDialog.getSold().setText(data[3]);
        returnDialog.getDiscountPercentage().setText(data[5]);
        returnDialog.getDiscountTotal().setText(data[6]);
        returnDialog.getTotalAmount().setText(data[7]);
    }

    private void clearReturnDialogField() {
        returnDialog.getClear().addActionListener(e -> clear());
    }

    private void clear() {
        returnDialog.getSold().setText("0");
        returnDialog.getTotalAmount().setText("0");
        returnDialog.getProductCount().setText("0");
        returnDialog.getCount().setText(String.valueOf(initialCount));
    }

    private void setDataToCenter() {
        String id = getReportIdFromList();
        if (addTransactionDB.isReportExist(id)) {
            selectedId = id;
            ArrayList<TransactionReportItem> items = addTransactionDB.getAllTransactionReportItem(id);
            BigDecimal total = addTransactionDB.getTransactionReport(id).getTotalAmount();
            topTable.addAllReportItem(items);
            topTotal.setText(total.toString());
            credit.setText(total.multiply(BigDecimal.valueOf(-1)).toString());
            initialCredit = Double.parseDouble(credit.getText());
        }
    }

    private void getSearchedTransactionReport() {
        String str = reportId.getText().toLowerCase();
        ArrayList<TransactionReport> reportList = addTransactionDB.getTransactionReportList();
        ArrayList<TransactionReport> reports = new ArrayList<>();
        for (TransactionReport transactionReport : reportList) {
            String id = transactionReport.getId().toLowerCase();
            String date = transactionReport.getDate().toString().toLowerCase();
            String combine = date + " - " + id;
            if (combine.contains(str)) {
                reports.add(transactionReport);
            }
        }
        list.populateTransactionList(reports);
    }

    private void search() {
        reportId.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (reportId.isFocusOwner()) {
                    if (reportId.getText().equals("")) {
                        list.populateTransactionList(addTransactionDB.getTransactionReportList());
                        return;
                    }
                    getSearchedTransactionReport();
                    setDataToCenter();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (reportId.isFocusOwner()) {
                    if (reportId.getText().equals("")) {
                        list.populateTransactionList(addTransactionDB.getTransactionReportList());
                        return;
                    }
                    getSearchedTransactionReport();
                    setDataToCenter();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void eventListenerForTable() {
        add.addActionListener(e -> addIfNotDuplicateProduct());

        topTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    addIfNotDuplicateProduct();
                }
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
                    addAllItem();
                }
            }
        });

        botTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    deleteSelectedItem(botTable);
                }
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
                    deleteAllItem();
                }
            }
        });
    }

    private void deleteAllItem() {
        if(DELETE_ALL() == 0) {
            botTable.getModel().setRowCount(0);
            setDataToCenter();
        }
    }

    private void addIfNotDuplicateProduct() {
        String[] data = getSelectedData(topTable);
        if (data == null) return;
        if(isDuplicateProduct(data[1])) return;
        if(data[8].equals("Exp/Dam")){
            autoSetProduct(data);
            returnDialog.setVisible(true);
            return;
        }
        saveProcess(data,null,true);
        removeRowAfterItemSave();
    }

    private void eventListenerForList() {
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getClickCount() == 2) {
                    setReportId();
                    setDataToCenter();
                    botTable.getModel().setRowCount(0);
                }
            }
        });
    }

    private void botTableChangeListener() {
        botTable.getModel().addTableModelListener(e -> {
            int row = botTable.getRowCount();
            calculateCreditAndTotalAmount(row);
        });
    }

    private void calculateCreditAndTotalAmount(int row) {
        double count = 0;
        for(int i=0;i<row;i++) {
            count += Double.parseDouble(botTable.getValueAt(i, 7).toString());
        }
        newTotal.setText(String.valueOf(count));
        credit.setText(String.valueOf(initialCredit + count));
    }

    private void calculateItemTotalAmount() {
        BigDecimal amount = new BigDecimal(returnDialog.getSoldTotal().getText()).
                subtract(new BigDecimal(returnDialog.getDiscountTotal().getText()));
        returnDialog.getTotalAmount().setText(amount.toString());
    }

    private void setReportId() {
        String transId = getReportIdFromList();
        if(transId.equals("")) return;
        reportId.setText(transId);
        newReportId.setText(convertReportId(transId));
    }

    private @NotNull String convertReportId(String transId) {
        StringBuilder str = new StringBuilder(transId);
        int num = Character.getNumericValue(str.    charAt(str.length()-1));
        str.replace(transId.length()-2,transId.length(),"B" + ++num);
        return str.toString();
    }

    private @NotNull String getReportIdFromList() {
        if (list.getSelectedValue() == null) return "";
        return list.getSelectedValue().substring(19, 37);
    }

    private String @Nullable [] getSelectedData(@NotNull JTableTransaction table) {
        String[] data = new String[table.getColumnCount()];
        int row = table.getSelectedRow();
        if (row == -1) return null;
        for (int i = 0; i < data.length; i++) {
            data[i] = table.getValueAt(row, i).toString();
        }
        return data;
    }

    private boolean isDuplicateProduct(String data) {
        int row = botTable.getRowCount();
        for (int i = 0; i < row; i++) {
            if (data.equals(botTable.getValueAt(i, 1))) {
                ConstantDialog.DUPLICATE_ID();
                return true;
            }
        }
        return false;
    }

    private void fixNumberColumn(@NotNull JTableTransaction table) {
        for (int i = 0; i < table.getRowCount(); i++) {
            table.setValueAt(i + 1, i, 0);
        }
    }

    private void setTotalAmount(@NotNull JTableTransaction table, CustomJTextField totalField) {
        table.getModel().addTableModelListener(e -> {
            BigDecimal total = new BigDecimal("0");
            final int ROW = table.getRowCount();
            for(int i=0;i<ROW;i++) {
                total = total.add(new BigDecimal(table.getValueAt(i,7).toString()));
            }
            totalField.setText(total.toString());
        });
    }

    private void documentListenerOfDiscountPercentage() {
        returnDialog.getDiscountPercentage().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(returnDialog.getDiscountPercentage().isFocusOwner()) {
                    setDiscountAmount(returnDialog.getDiscountTotal(),returnDialog.getDiscountPercentage(),returnDialog.getSoldTotal());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(returnDialog.getDiscountPercentage().isFocusOwner()) {
                    setDiscountAmount(returnDialog.getDiscountTotal(),returnDialog.getDiscountPercentage(),returnDialog.getSoldTotal());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

    }

    private void documentListenerOfDiscountAmount() {
        returnDialog.getDiscountTotal().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(returnDialog.getDiscountTotal().isFocusOwner()) {
                    final BigDecimal discount = isValidNumber(returnDialog.getDiscountTotal().getText()) ?
                                                calculateDiscountAmount(returnDialog.getDiscountTotal(),returnDialog.getSoldTotal()) : new BigDecimal("0");
                    returnDialog.getDiscountPercentage().setText(discount.toString());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(returnDialog.getDiscountTotal().isFocusOwner()) {
                    final BigDecimal discount = isValidNumber(returnDialog.getDiscountTotal().getText()) ?
                                                calculateDiscountAmount(returnDialog.getDiscountTotal(),returnDialog.getSoldTotal()) : new BigDecimal("0");
                    returnDialog.getDiscountPercentage().setText(discount.toString());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void documentListenerOfSold() {
        returnDialog.getSold().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                autoCalculate(returnDialog.getSoldTotal(), returnDialog.getSold(),true, productDB,returnDialog.getProduct());
                setDiscountAmount(returnDialog.getDiscountTotal(),returnDialog.getDiscountPercentage(),returnDialog.getSoldTotal());
                calculateItemTotalAmount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                autoCalculate(returnDialog.getSoldTotal(), returnDialog.getSold(),true, productDB,returnDialog.getProduct());
                setDiscountAmount(returnDialog.getDiscountTotal(),returnDialog.getDiscountPercentage(),returnDialog.getSoldTotal());
                calculateItemTotalAmount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }
}
