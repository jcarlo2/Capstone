package retail.controller.component.inventory;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductController;
import retail.customcomponent.jtable.CustomJTableInventory;
import retail.model.ProductReport;
import retail.util.constant.ConstantDialog;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.manipulator.inventory.panel.Delete;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Objects;

public class ViewController {
    private final ProductController controller = new ProductController();
    private final Delete delete;
    private final CustomJTableInventory table;

    public ViewController(@NotNull BottomBorderPanel bottomBorderPanel) {
        delete = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getDeletePanel();
        table = bottomBorderPanel.getBottomMainCard().getInventoryCard().getDelete().getTableInventory();

        deleteReport();
        toggleReportDeletable();
        searchList();
        checkIfReportDeletable();

        // POPULATE LIST AT START UP
        delete.getList().populateInventoryList(controller.getProductReportList());
    }

    private void populateTableWithSalesReportItem(String id) {
        table.addItemList(controller.getAllProductReportItem(id));
    }

    private void deleteReport() {
        delete.getDelete().addActionListener(e -> {
            if(e.getSource() == delete.getDelete()) {
                String id = getIdString();
                if(controller.isDeletable(id)) {
                    controller.deleteReport(id);
                    delete.getList().populateInventoryList(controller.getProductReportList());
                }else {
                    ConstantDialog.REPORT_NOT_DELETABLE();
                }
            }
        });
    }

    private void toggleReportDeletable() {
        delete.getDeletableStatus().addActionListener(e -> {
            if(e.getSource() == delete.getDeletableStatus()) {
                String id = getIdString();
                if(controller.isDeletable(id)) delete.getDeletableStatus().setText("NO");
                else delete.getDeletableStatus().setText("YES");

                controller.changeIsDeletableStatus(id);
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
            if(!controller.isReportExist(id)) delete.getDeletableStatus().setText("");
            if(controller.isDeletable(id)) {
                delete.getDeletableStatus().setText("YES");
            }else{
                delete.getDeletableStatus().setText("NO");
            }
            populateTableWithSalesReportItem(id);
        });
    }

    private @NotNull ArrayList<String> checker(String check) {
        ArrayList<ProductReport> reportList = controller.getProductReportList();
        ArrayList<String> checkedList = new ArrayList<>();
        for(ProductReport report : reportList) {
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
            delete.getList().populateInventoryList(controller.getProductReportList());
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
