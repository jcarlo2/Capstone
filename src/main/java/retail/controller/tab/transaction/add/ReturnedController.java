package retail.controller.tab.transaction.add;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retail.controller.database.ProductDatabase;
import retail.controller.database.TransactionDatabase;
import retail.model.TransactionReport;
import retail.model.TransactionReportItem;
import retail.shared.constant.ConstantDialog;
import retail.shared.customcomponent.jlist.CustomJList;
import retail.shared.customcomponent.jtable.JTableTransaction;
import retail.shared.customcomponent.jtextfield.CustomJTextField;
import retail.view.main.tab.bot.BottomMainCard;
import retail.view.main.tab.bot.BottomManipulatorCard;
import retail.view.main.tab.bot.transaction.center.add.ReturnedTransaction;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import static retail.shared.constant.ConstantDialog.EMPTY_FIELD;

public class ReturnedController {
    private final TransactionDatabase transactionDatabase = new TransactionDatabase();
    private final ProductDatabase productDatabase = new ProductDatabase();
    private final ReturnedTransaction center;
    private final retail.view.main.tab.bot.transaction.manipulator.panel.add.ReturnedTransaction manipulator;
    private final CustomJList list;
    private final JTableTransaction topTable;
    private final JTableTransaction botTable;
    private final JButton addButton;
    private String tempReportId;

    public ReturnedController(@NotNull BottomMainCard bottomMainCard, @NotNull BottomManipulatorCard bottomManipulatorCard) {
        center = bottomMainCard.getTransactionCard().getAddCard().getReturnedTransaction();
        manipulator = bottomManipulatorCard.getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransaction();
        list = manipulator.getList();
        topTable = center.getTopTable();
        botTable = center.getBotTable();
        addButton = manipulator.getAdd();
        eventListenerForList();
        search();
        eventListenerForTable();
        deleteSelectedData(manipulator.getDelete(),botTable);
        setTotalAmount(botTable,center.getTopTotal());
        list.populateTransactionList(transactionDatabase.getTransactionReportList()); // POPULATE LIST AT START UP
    }

    private void addSelectedData(JTableTransaction botTable) {
        String[] data = getSelectedData(topTable);
        if (Objects.isNull(data)) return;
        if (isDuplicateProduct(data[1])) {
            ConstantDialog.DUPLICATE_ID();
            return;
        }
        reasonAndActionChecker(data);
        fixNumberColumn(botTable);
    }

    private void reasonAndActionChecker(String @NotNull [] data) {
        switch (data[9]) {
            case "None" -> noneAction(data);
            case "Same" -> sameAction(data);
            case "Change" -> changeAction(data);
        }
    }

    @Contract(pure = true)
    private void noneAction(@NotNull String @NotNull [] data) {
        if(data[8].equals("Exp/Dam")) {
            ArrayList<TransactionReportItem> itemList = transactionDatabase.getAllTransactionReportItem(tempReportId);
            for(TransactionReportItem item : itemList) {
                if(item.getProductId().equals(data[1])) {

                }
            }
        }
    }

    @Contract(pure = true)
    private @NotNull Double calculateDiscount(Double percent, Double soldTotal) {
        percent = percent / 100;
        return soldTotal * percent;
    }

    private void sameAction(String[] data) {
    }

    private void changeAction(String[] data) {
    }

    private void setDataToTopTable() {
        String id = getReportIdFromList();
        if (transactionDatabase.isReportExist(id)) {
            ArrayList<TransactionReportItem> items = transactionDatabase.getAllTransactionReportItem(id);
            BigDecimal total = transactionDatabase.getTransactionReport(id).getTotalAmount();
            topTable.addAllReportItem(items);
            center.getTopTotal().setText(total.toString());
        }
    }

    private void getTransactionReport() {
        String str = manipulator.getReportId().getText().toLowerCase();
        ArrayList<TransactionReport> reportList = transactionDatabase.getTransactionReportList();
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
        manipulator.getReportId().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (manipulator.getReportId().isFocusOwner()) {
                    if (manipulator.getReportId().getText().equals("")) {
                        list.populateTransactionList(transactionDatabase.getTransactionReportList());
                        return;
                    }
                    getTransactionReport();
                    setDataToTopTable();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (manipulator.getReportId().isFocusOwner()) {
                    if (manipulator.getReportId().getText().equals("")) {
                        list.populateTransactionList(transactionDatabase.getTransactionReportList());
                        return;
                    }
                    getTransactionReport();
                    setDataToTopTable();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void eventListenerForTable() {
        addButton.addActionListener(e -> addSelectedData(botTable));

        topTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
                    int point = topTable.rowAtPoint(e.getPoint());
                    if(point >= 0 && point < topTable.getRowCount()) topTable.setRowSelectionInterval(point,point);
                    else topTable.clearSelection();

                    addSelectedData(botTable);
                }
            }
        });
    }

    private void eventListenerForList() {
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getClickCount() == 2) {
                    setReportId();
                    tempReportId = getReportIdFromList();
                    setDataToTopTable();
                    botTable.getModel().setRowCount(0);
                }
            }
        });
    }

    private void setReportId() {
        String transId = getReportIdFromList();
        if(transId.equals("")) return;
        manipulator.getReportId().setText(transId);
        manipulator.getNewReportId().setText(convertReportId(transId));
    }

    private @NotNull String convertReportId(String transId) {
        StringBuilder str = new StringBuilder(transId);
        int num = Character.getNumericValue(str.    charAt(str.length()-1));
        str.replace(transId.length()-2,transId.length(),"A" + ++num);
        return str.toString();
    }

    private @NotNull String getReportIdFromList() {
        if (list.getSelectedValue() == null) return "";
        return list.getSelectedValue().substring(19, 37);
    }

    private String @Nullable [] getSelectedData(@NotNull JTableTransaction topTable) {
        String[] data = new String[10];
        int row = topTable.getSelectedRow();
        if (row == -1) return null;
        for (int i = 0; i < data.length; i++) {
            if(topTable.getValueAt(row, i).toString().equals("--")) {
                EMPTY_FIELD();
                return null;
            }
            data[i] = topTable.getValueAt(row, i).toString();
        }
        return data;
    }

    private boolean isDuplicateProduct(String data) {
        int row = botTable.getRowCount();
        for (int i = 0; i < row; i++) {
            if (data.equals(botTable.getValueAt(i, 1))) return true;
        }
        return false;
    }

    private void deleteSelectedData(@NotNull JButton deleteButton, JTableTransaction botTable) {
        deleteButton.addActionListener(e -> {
            int row = this.botTable.getSelectedRow();
            if (row == -1) return;
            this.botTable.getModel().removeRow(row);
            fixNumberColumn(botTable);
        });
    }

    private void fixNumberColumn(@NotNull JTableTransaction botTable) {
        for (int i = 0; i < botTable.getRowCount(); i++) {
            botTable.setValueAt(i + 1, i, 0);
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
}
