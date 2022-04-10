package retail.controller.component.sales;

import org.jetbrains.annotations.NotNull;
import retail.constant.ConstantDialog;
import retail.controller.database.SalesReportController;
import retail.model.SalesReportObject;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.manipulator.reportmanipulator.panel.DeletePanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Objects;

public class SalesDeleteController {
    private final SalesReportController reportController = new SalesReportController();
    private final DeletePanel deletePanel;

    public SalesDeleteController(@NotNull BottomBorderPanel bottomBorderPanel) {
        deletePanel = bottomBorderPanel.getManipulatorCard().getSalesReportManipulator().getReportCardPanel().getDeletePanel();

        searchList();
        checkIfReportDeletable();
        toggleReportDeletable();
        deleteReport();
    }

    private void deleteReport() {
        deletePanel.getDelete().addActionListener(e -> {
            if(e.getSource() == deletePanel.getDelete()) {
                String id = deletePanel.getList().getSelectedValue().substring(0,13);
                    if(reportController.isReportDeletable(id)) {
                        reportController.deleteReport(id);
                        deletePanel.getList().populateSalesReportList();
                    }else {
                        ConstantDialog.REPORT_NOT_DELETABLE();
                    }
            }
        });
    }

    private void toggleReportDeletable() {
        deletePanel.getYesNo().addActionListener(e -> {
            if(e.getSource() == deletePanel.getYesNo()) {
                String id = deletePanel.getList().getSelectedValue().substring(0,13);
                if(reportController.isReportDeletable(id)) deletePanel.getYesNo().setText("NO");
                else deletePanel.getYesNo().setText("YES");

                reportController.changeIsDeletableStatus(id);
            }
        });
    }

    private @NotNull String getIdString() {
        String id = deletePanel.getList().getSelectedValue();
        if(Objects.isNull(id)) return "";
        return id.substring(0,13);
    }

    private void checkIfReportDeletable() {
        deletePanel.getList().addListSelectionListener(e -> {
            String id = getIdString();
            if(id.equals("")) return;
            if(!reportController.ifReportExist(id)) deletePanel.getYesNo().setText("");
            if(reportController.isReportDeletable(id)) {
                deletePanel.getYesNo().setText("YES");
            }else{
                deletePanel.getYesNo().setText("NO");
            }
        });
    }

    private @NotNull ArrayList<String> checker(String check) {
        ArrayList<SalesReportObject> reportList = reportController.getSalesReportList();
        ArrayList<String> checkedList = new ArrayList<>();
            for(SalesReportObject report : reportList) {
                String str = report.getId() + " - " + report.getDate();
                    if(str.contains(check)) {
                        checkedList.add(str);
                    }
            }
        return checkedList;
    }

    private void autoSearch() {
        String check = deletePanel.getSearch().getText();
        if(check.equals("")) {
            deletePanel.getList().populateSalesReportList();
            return;
        }
        deletePanel.getList().getModel().removeAllElements();
        for (String str : checker(check)) {
            deletePanel.getList().getModel().addElement(str);
        }
    }

    private void searchList() {
        deletePanel.getSearch().getDocument().addDocumentListener(new DocumentListener() {
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
