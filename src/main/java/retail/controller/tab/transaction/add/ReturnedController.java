package retail.controller.tab.transaction.add;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retail.controller.database.TransactionDatabase;
import retail.customcomponent.jlist.CustomJList;
import retail.customcomponent.jtable.CustomJTableTransaction;
import retail.model.TransactionReport;
import retail.model.TransactionReportItem;
import retail.view.main.tab.bot.BottomMainCard;
import retail.view.main.tab.bot.BottomManipulatorCard;
import retail.view.main.tab.bot.center.transaction.add.ReturnedTransaction;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class ReturnedController {
    private final TransactionDatabase transactionDatabase = new TransactionDatabase();
    private final ReturnedTransaction center;
    private final retail.view.main.tab.bot.manipulator.transaction.panel.add.ReturnedTransaction manipulator;
    private CustomJList list;
    private CustomJTableTransaction topTable;
    private CustomJTableTransaction botTable;

    public ReturnedController(@NotNull BottomMainCard bottomMainCard, @NotNull BottomManipulatorCard bottomManipulatorCard) {
        center = bottomMainCard.getTransactionCard().getAddCard().getReturnedTransaction();
        manipulator = bottomManipulatorCard.getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransaction();

        initializeComponent();
        listSelectionListener();
        search();
        clear();
        topTableSelectionListener();

        list.populateTransactionList(transactionDatabase.getTransactionReportList()); // POPULATE LIST AT START UP
    }

    private void initializeComponent() {
        list = manipulator.getList();
        topTable = center.getTopTable();
        botTable = center.getBotTable();
    }

    private void topTableSelectionListener() {
        topTable.getSelectionModel().addListSelectionListener(e -> setTopTableDataToManipulator());
    }

    private void setTopTableDataToManipulator() {
        if(Objects.isNull(getTopTableData())) {
            clearManipulator();
            return;
        }
        manipulator.getId().setText(getTopTableData()[1]);
        manipulator.getPrice().setText(getTopTableData()[2]);
        manipulator.getSold().setText(getTopTableData()[3]);
        manipulator.getSoldTotal().setText(getTopTableData()[4]);
        manipulator.getDiscountPercentage().setText(getTopTableData()[5]);
        manipulator.getDiscountTotal().setText(getTopTableData()[6]);
    }

    private void clear() {
        manipulator.getClear().addActionListener(e -> clearManipulator());
    }

    private void clearManipulator() {
        manipulator.getId().setText("");
        manipulator.getPrice().setText("");
        manipulator.getSold().setText("");
        manipulator.getSoldTotal().setText("");
        manipulator.getDiscountPercentage().setText("");
        manipulator.getDiscountTotal().setText("");
    }

    private String @Nullable [] getTopTableData() {
        String[] data = new String[topTable.getColumnCount()];
        int row = topTable.getSelectedRow();
        for(int i=0;i<topTable.getColumnCount();i++) {
            if(row == -1) return null;
            data[i] = topTable.getValueAt(row,i).toString();
        }
        return data;
    }

    private void setDataToTopTable() {
        String id = manipulator.getReportId().getText();
            if(transactionDatabase.isReportExist(id)) {
               ArrayList<TransactionReportItem> items = transactionDatabase.getAllTransactionReportItem(id);
                topTable.addAllItems(items);
            }
    }

    private void getTransactionReport() {
        String str = manipulator.getReportId().getText().toLowerCase();
        ArrayList<TransactionReport> reportList = transactionDatabase.getTransactionReportList();
        ArrayList<TransactionReport> reports = new ArrayList<>();
        for (TransactionReport transactionReport : reportList) {
            String id = transactionReport.getId().toLowerCase();
            String date = transactionReport.getDate().toString().toLowerCase();
            if (id.contains(str) || date.contains(str)) {
                reports.add(transactionReport);
            }
        }
        list.populateTransactionList(reports);
    }

    private void search() {
        manipulator.getReportId().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(manipulator.getReportId().isFocusOwner())  {
                    if(manipulator.getReportId().getText().equals("")) {
                        list.populateTransactionList(transactionDatabase.getTransactionReportList());
                        return;
                    }
                    getTransactionReport();
                    setDataToTopTable();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(manipulator.getReportId().isFocusOwner()) {
                    if(manipulator.getReportId().getText().equals("")) {
                        list.populateTransactionList(transactionDatabase.getTransactionReportList());
                        return;
                    }
                    getTransactionReport();
                    setDataToTopTable();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void listSelectionListener() {
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if(e.getClickCount() == 1) {
                setReportId();
                setDataToTopTable();
            }
            }
        });
    }

    private void setReportId() {
        manipulator.getReportId().setText(getReportIdFromList());
    }

    private @NotNull String getReportIdFromList() {
        if(list.getSelectedValue() == null) return "";
        return list.getSelectedValue().substring(13,28);
    }
}
