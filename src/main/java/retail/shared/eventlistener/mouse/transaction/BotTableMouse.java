package retail.shared.eventlistener.mouse.transaction;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.transaction.add.ReturnTransactionFacade;
import retail.shared.entity.TransactionItemDetail;
import retail.view.main.tab.bot.transaction.center.add.ReturnedTransactionCenter;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.ReturnedTransactionManipulator;

import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static retail.shared.constant.ConstantDialog.DELETE_ALL;

public class BotTableMouse extends MouseAdapter {
    private final ReturnedTransactionCenter center;
    private final ReturnTransactionFacade facade;
    private final ReturnedTransactionManipulator manipulator;

    public BotTableMouse(ReturnedTransactionManipulator manipulator,
                        ReturnedTransactionCenter center,
                        ReturnTransactionFacade facade) {
        this.manipulator = manipulator;
        this.center = center;
        this.facade = facade;
    }

    @Override
    public void mousePressed(@NotNull MouseEvent e) {
        if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
            deleteProcess();
        }else if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
            if(DELETE_ALL() == 0) deleteAllProcess();
        }
    }

    private void deleteAllProcess() {
        String id = facade.reverseConvertId(manipulator.getNewReportId().getText());
        ((DefaultTableModel)center.getBotTable().getModel()).setRowCount(0);
        ((DefaultTableModel)center.getTopTable().getModel()).setRowCount(0);
        for(TransactionItemDetail item : facade.getAllReportItem(id)) {
            center.getTopTable().addReportItem(item, "");
        }
        facade.fixNumberColumn(center.getTopTable());
    }

    private void deleteProcess() {
        int row = center.getBotTable().getSelectedRow();
        if(row == -1) return;
        String[] data = facade.getRowData(center.getBotTable(), row);
        String id = facade.reverseConvertId(manipulator.getNewReportId().getText());

        center.getTopTable().addReportItem(facade.recoverItem(data,id), "0");
        ((DefaultTableModel)center.getBotTable().getModel()).removeRow(row);
        facade.fixNumberColumn(center.getBotTable());
    }
}
