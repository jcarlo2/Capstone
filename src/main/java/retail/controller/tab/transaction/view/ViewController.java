package retail.controller.tab.transaction.view;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.TransactionDatabase;
import retail.shared.customcomponent.jlist.CustomJList;
import retail.shared.customcomponent.jtable.JTableTransaction;
import retail.model.TransactionReport;
import retail.model.TransactionReportItem;
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.bot.transaction.center.view.View;

import java.util.ArrayList;
import java.util.Objects;

public class ViewController {
    private final TransactionDatabase controller = new TransactionDatabase();
    private final retail.view.main.tab.bot.transaction.manipulator.panel.view.View viewManipulator;
    private final View view;
    private final JTableTransaction table;
    private final CustomJList list;

    public ViewController(@NotNull BottomBorderPanel bottomBorderPanel) {
        viewManipulator = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getView();
        view = bottomBorderPanel.getBottomMainCard().getTransactionCard().getView();
        table = bottomBorderPanel.getBottomMainCard().getTransactionCard().getView().getTable();
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
        table.addAllReportItem(itemList);
        view.getId().setText(getIdString());
    }

    private void getTotalAmountOfItems() {
        TransactionReport report = controller.getTransactionReport(getIdString());
            if(Objects.isNull(report)) return;
        view.getTotal().setText(report.getTotalAmount().toString());
    }
    private @NotNull String getIdString() {
        String id = viewManipulator.getList().getSelectedValue();
        if(Objects.isNull(id)) return "";
        return id.substring(19,37);
    }
}

