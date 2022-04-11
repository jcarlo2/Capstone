package retail.controller.component.sales;

import org.jetbrains.annotations.NotNull;
import retail.util.constant.ConstantDialog;
import retail.controller.database.SalesReportController;
import retail.customcomponent.jtable.CustomJTableSalesReport;
import retail.model.SalesReport;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.manipulator.reportmanipulator.panel.Delete;
import retail.view.main.panel.bot.manipulator.reportmanipulator.panel.View;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Objects;

public class DeleteSalesController {
    private final SalesReportController salesReportController = new SalesReportController();
    private final Delete delete;
    private final View view;
    private final CustomJTableSalesReport table;

    public DeleteSalesController(@NotNull BottomBorderPanel bottomBorderPanel) {
        delete = bottomBorderPanel.getManipulatorCard().getSalesReportManipulator().getSalesManipulatorCard().getDelete();
        table = bottomBorderPanel.getBottomMainCard().getSalesReport().getSalesMainDelete().getTable();
        view = bottomBorderPanel.getManipulatorCard().getSalesReportManipulator().getSalesManipulatorCard().getView();

        searchList();
        checkIfReportDeletable();
        toggleReportDeletable();
        deleteReport();
        // populate list at start up
        delete.getList().populateSalesList(salesReportController.getSalesReportList());
    }

    private void populateTableWithSalesReportItem(String id) {
        table.addItem(salesReportController.getAllSalesReportItem(id));
    }

    private void deleteReport() {
        delete.getDelete().addActionListener(e -> {
            if(e.getSource() == delete.getDelete()) {
                String id = getIdString();
                    if(salesReportController.isReportDeletable(id)) {
                        salesReportController.deleteReport(id);
                        delete.getList().populateSalesList(salesReportController.getSalesReportList());
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
                if(salesReportController.isReportDeletable(id)) delete.getYesNo().setText("NO");
                else delete.getYesNo().setText("YES");

                salesReportController.changeIsDeletableStatus(id);
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
            if(!salesReportController.ifReportExist(id)) delete.getYesNo().setText("");
            if(salesReportController.isReportDeletable(id)) {
                delete.getYesNo().setText("YES");
            }else{
                delete.getYesNo().setText("NO");
            }
            populateTableWithSalesReportItem(id);
        });
    }

    private @NotNull ArrayList<String> checker(String check) {
        ArrayList<SalesReport> reportList = salesReportController.getSalesReportList();
        ArrayList<String> checkedList = new ArrayList<>();
            for(SalesReport report : reportList) {
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
            delete.getList().populateSalesList(salesReportController.getSalesReportList());
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
