package retail.shared.eventlistener.action.transaction.returned;

import retail.model.facade.transaction.add.ReturnTransactionFacade;
import retail.shared.entity.TransactionItemDetail;
import retail.view.main.tab.bot.transaction.center.add.ReturnedTransactionCenter;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.ReturnedTransactionManipulator;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static retail.shared.constant.ConstantDialog.DELETE_ALL;

public class DeleteAllAction implements ActionListener {
    private final ReturnedTransactionCenter center;
    private final ReturnTransactionFacade facade;
    private final ReturnedTransactionManipulator manipulator;

    public DeleteAllAction(ReturnedTransactionManipulator manipulator,
                           ReturnedTransactionCenter center,
                           ReturnTransactionFacade facade) {
        this.manipulator = manipulator;
        this.center = center;
        this.facade = facade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(DELETE_ALL() != 0) return;
        String id = facade.reverseConvertId(manipulator.getNewReportId().getText());
        ((DefaultTableModel)center.getBotTable().getModel()).setRowCount(0);
        ((DefaultTableModel)center.getTopTable().getModel()).setRowCount(0);
        for(TransactionItemDetail item : facade.getAllReportItem(id)) {
            center.getTopTable().addReportItem(item, "");
        }
        facade.fixNumberColumn(center.getTopTable());
    }
}
