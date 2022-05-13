package retail.controller.tab.transaction.view;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.TransactionDatabase;
import retail.customcomponent.jlist.CustomJList;
import retail.customcomponent.jtable.transaction.CustomJTableTransaction;
import retail.model.TransactionReport;
import retail.model.TransactionReportItem;
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.bot.center.transaction.ViewMain;
import retail.view.main.tab.bot.manipulator.transaction.panel.view.View;

import java.util.ArrayList;
import java.util.Objects;

public class ViewController {
    private final TransactionDatabase controller = new TransactionDatabase();
    private final View viewManipulator;
    private final ViewMain viewMain;
    private final CustomJTableTransaction table;
    private final CustomJList list;

    public ViewController(@NotNull BottomBorderPanel bottomBorderPanel) {
        viewManipulator = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getView();
        viewMain = bottomBorderPanel.getBottomMainCard().getTransactionCard().getViewMain();
        table = bottomBorderPanel.getBottomMainCard().getTransactionCard().getViewMain().getTable();
        list = viewManipulator.getList();

        listSelectionListener();
        populateListWithReport(); // POPULATE LIST ON START UP
    }

    public void populateListWithReport() {
        list.populateTransactionList(controller.getTransactionReportList());
    }

    private void listSelectionListener() {
        list.addListSelectionListener(e -> {
            getTransactionReportItems();
            getTotalAmountOfItems();
        });
    }

    private void getTransactionReportItems() {
        ArrayList<TransactionReportItem> itemList = controller.getAllTransactionReportItem(getIdString());
        table.addAllItems(itemList);
        viewMain.getId().setText(getIdString());
    }

    private void getTotalAmountOfItems() {
        TransactionReport report = controller.getTransactionReport(getIdString());
            if(Objects.isNull(report)) return;
        viewMain.getTotal().setText(report.getTotalAmount().toString());
    }
    private @NotNull String getIdString() {
        String id = viewManipulator.getList().getSelectedValue();
        if(Objects.isNull(id)) return "";
        return id.substring(13,28);
    }
}