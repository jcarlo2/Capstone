package retail.controller.tab.inventory;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductDatabase;
import retail.shared.customcomponent.jtable.JTableInventory;
import retail.model.ProductReport;
import retail.model.ProductReportItem;
import retail.shared.constant.ConstantDialog;
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.bot.inventory.manipulator.panel.View;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Objects;

public class ViewController {
    private final ProductDatabase controller = new ProductDatabase();
    private final View view;
    private final JTableInventory table;

    public ViewController(@NotNull BottomBorderPanel bottomBorderPanel) {
        view = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getView();
        table = bottomBorderPanel.getBottomMainCard().getInventoryCard().getView().getTableInventory();

        deleteReport();
        searchList();
        populateTable();
        // POPULATE LIST AT START UP
//        view.getList().populateInventoryList(controller.getProductReportList());
    }

    private void populateTable() {
        view.getList().addListSelectionListener(e -> {
            ArrayList<ProductReportItem> itemList = controller.getAllProductReportItem(getIdString());
            table.addItemList(itemList);
        });
    }

    private void deleteReport() {
        view.getDelete().addActionListener(e -> {
            if(e.getSource() == view.getDelete()) {
                String id = getIdString();
                if(controller.isDeletable(id)) {
                    controller.deleteReport(id);
                    view.getList().populateInventoryList(controller.getProductReportList());
                }else {
                    ConstantDialog.REPORT_NOT_DELETABLE();
                }
            }
        });
    }

    private @NotNull String getIdString() {
        String id = view.getList().getSelectedValue();
        if(Objects.isNull(id)) return "";
        return id.substring(13,28);
    }

    private @NotNull ArrayList<String> checker(String check) {
        ArrayList<ProductReport> reportList = controller.getProductReportList();
        ArrayList<String> checkedList = new ArrayList<>();
        for(ProductReport report : reportList) {
            String str =  report.getDate() + " - " + report.getId();
            if(str.toLowerCase().contains(check.toLowerCase())) {
                checkedList.add(str);
            }
        }
        return checkedList;
    }

    private void autoSearch() {
        String check = view.getSearch().getText();
        if(check.equals("")) {
            view.getList().populateInventoryList(controller.getProductReportList());
            return;
        }
        view.getList().getModel().removeAllElements();
        for (String str : checker(check)) {
            view.getList().getModel().addElement(str);
        }
    }

    private void searchList() {
        view.getSearch().getDocument().addDocumentListener(new DocumentListener() {
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
