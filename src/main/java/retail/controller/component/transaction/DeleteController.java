package retail.controller.component.transaction;

import org.jetbrains.annotations.NotNull;
import retail.util.constant.ConstantDialog;
import retail.controller.database.TransactionController;
import retail.customcomponent.jtable.CustomJTableTransaction;
import retail.model.TransactionReport;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.manipulator.transaction.panel.Delete;
import retail.view.main.panel.bot.manipulator.transaction.panel.View;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Objects;

public class DeleteController {
    private final TransactionController transactionController = new TransactionController();
    private final Delete delete;
    private final View view;
    private final CustomJTableTransaction table;

    public DeleteController(@NotNull BottomBorderPanel bottomBorderPanel) {
        delete = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getDelete();
        table = bottomBorderPanel.getBottomMainCard().getTransactionCard().getDelete().getTable();
        view = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getView();

        searchList();
        checkIfReportDeletable();
        toggleReportDeletable();
        deleteReport();
        // populate list at start up
        delete.getList().populateSalesList(transactionController.getSalesReportList());
    }

    private void populateTableWithSalesReportItem(String id) {
        table.addItem(transactionController.getAllSalesReportItem(id));
    }

    private void deleteReport() {
        delete.getDelete().addActionListener(e -> {
            if(e.getSource() == delete.getDelete()) {
                String id = getIdString();
                    if(transactionController.isReportDeletable(id)) {
                        transactionController.deleteReport(id);
                        delete.getList().populateSalesList(transactionController.getSalesReportList());
                    }else {
                        ConstantDialog.REPORT_NOT_DELETABLE();
                    }
            }
        });
    }

    private void toggleReportDeletable() {
        delete.getYesNo().addActionListener(e -> {
            if(e.getSource() == delete.getYesNo()) {
                String id = getIdString();
                if(transactionController.isReportDeletable(id)) delete.getYesNo().setText("NO");
                else delete.getYesNo().setText("YES");

                transactionController.changeIsDeletableStatus(id);
            }
        });
    }

    private @NotNull String getIdString() {
        String id = delete.getList().getSelectedValue();
        if(Objects.isNull(id)) return "";
        return id.substring(0,15);
    }

    private void checkIfReportDeletable() {
        delete.getList().addListSelectionListener(e -> {
            String id = getIdString();
            if(id.equals("")) return;
            if(!transactionController.ifReportExist(id)) delete.getYesNo().setText("");
            if(transactionController.isReportDeletable(id)) {
                delete.getYesNo().setText("YES");
            }else{
                delete.getYesNo().setText("NO");
            }
            populateTableWithSalesReportItem(id);
        });
    }

    private @NotNull ArrayList<String> checker(String check) {
        ArrayList<TransactionReport> reportList = transactionController.getSalesReportList();
        ArrayList<String> checkedList = new ArrayList<>();
            for(TransactionReport report : reportList) {
                String str = report.getId() + " - " + report.getDate();
                    if(str.contains(check)) {
                        checkedList.add(str);
                    }
            }
        return checkedList;
    }

    private void autoSearch() {
        String check = delete.getSearch().getText();
        if(check.equals("")) {
            delete.getList().populateSalesList(transactionController.getSalesReportList());
            return;
        }
        delete.getList().getModel().removeAllElements();
        for (String str : checker(check)) {
            delete.getList().getModel().addElement(str);
        }
    }

    private void searchList() {
        delete.getSearch().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                autoSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                autoSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }
}
