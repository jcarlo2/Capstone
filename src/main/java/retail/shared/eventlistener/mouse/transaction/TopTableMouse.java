package retail.shared.eventlistener.mouse.transaction;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.transaction.add.ReturnTransactionFacade;
import retail.shared.custom.CustomJDialog;
import retail.shared.entity.TransactionItemDetail;
import retail.view.main.tab.bot.transaction.center.add.ReturnedTransactionCenter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static retail.shared.constant.ConstantDialog.ADD_ALL;

public class TopTableMouse extends MouseAdapter {
    private final ReturnedTransactionCenter center;
    private final ReturnTransactionFacade facade;
    private final CustomJDialog returnDialog;

    public TopTableMouse(ReturnedTransactionCenter center,
                         ReturnTransactionFacade facade,
                         CustomJDialog returnDialog) {
        this.center = center;
        this.facade = facade;
        this.returnDialog = returnDialog;
    }

    @Override
    public void mousePressed(@NotNull MouseEvent e) {
        if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
            addProcess();
        }else if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
            if(ADD_ALL() == 0) addAllProcess();
        }
    }

    private void addAllProcess() {
        ArrayList<TransactionItemDetail> itemList = facade.getRowDataByNoneReason(center.getTopTable());
        for(TransactionItemDetail item : itemList) {
            center.getBotTable().addReportItem(item, "0");
        }
        facade.removeRowWithReason(center.getTopTable());
        facade.fixNumberColumn(center.getBotTable());
        facade.fixNumberColumn(center.getTopTable());
    }

    private void addProcess() {
        int row = center.getTopTable().getSelectedRow();
        if(row == -1) return;
        String[] data = facade.getRowData(center.getTopTable(), row);
        if(data[7].equals("--")) {
            center.getBotTable().addReportItem(facade.createItem(data), "0");
            center.getTopTable().removeRow(row);
            facade.fixNumberColumn(center.getTopTable());
            facade.fixNumberColumn(center.getBotTable());
        }else {
            returnDialog.setVisible(true);
        }
    }
}
