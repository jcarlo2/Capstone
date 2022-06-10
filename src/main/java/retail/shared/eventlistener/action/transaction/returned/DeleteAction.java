package retail.shared.eventlistener.action.transaction.returned;

import retail.model.facade.transaction.add.ReturnTransactionFacade;
import retail.view.main.tab.bot.transaction.center.add.ReturnedTransactionCenter;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.ReturnedTransactionManipulator;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAction implements ActionListener {
    private final ReturnedTransactionCenter center;
    private final ReturnTransactionFacade facade;
    private final ReturnedTransactionManipulator manipulator;

    public DeleteAction(ReturnedTransactionManipulator manipulator,
                        ReturnedTransactionCenter center,
                        ReturnTransactionFacade facade) {
        this.manipulator = manipulator;
        this.center = center;
        this.facade = facade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = center.getBotTable().getSelectedRow();
        if(row == -1) return;
        String[] data = facade.getRowData(center.getBotTable(), row);
        String reportId = facade.reverseConvertId(manipulator.getNewReportId().getText());

        center.getTopTable().addReportItem(facade.recoverItem(data,reportId), "0");
        ((DefaultTableModel)center.getBotTable().getModel()).removeRow(row);
        facade.fixNumberColumn(center.getBotTable());
    }
}
