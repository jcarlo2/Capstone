package retail.controller.tab.inventory.view;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductDatabase;
import retail.shared.pojo.ProductReport;
import retail.shared.pojo.ProductReportItem;
import retail.shared.constant.ConstantDialog;
import retail.shared.customcomponent.jlist.CustomJList;
import retail.shared.customcomponent.jtable.JTableInventory;
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.bot.inventory.manipulator.panel.View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        update();
        view.getList().populateInventoryList(controller.getProductReportList());
    }

    private void update() {
        Runnable runnable = () -> {
            if(!isSameData(view.getList())) {
                SwingUtilities.invokeLater(() -> view.getList().populateInventoryList(controller.getProductReportList()));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,0,1, TimeUnit.SECONDS);
    }

    private boolean isSameData(@NotNull CustomJList list) {
        ArrayList<String> reportList = list.getAllElement();
        ArrayList<ProductReport> report = controller.getProductReportList();
        for(ProductReport productReport : report) {
            if(!reportList.contains(productReport.getId())) return false;
        }
        System.out.println(report.size() + " " + reportList.size());
        return reportList.size() == report.size();
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
        view.getList().getListModel().removeAllElements();
        for (String str : checker(check)) {
            view.getList().getListModel().addElement(str);
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
