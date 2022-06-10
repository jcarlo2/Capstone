package retail.shared.eventlistener.action.transaction.add;

import retail.model.facade.transaction.add.AddTransactionFacade;
import retail.view.main.tab.bot.transaction.center.add.AddTransactionCenter;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.AddTransactionManipulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAction implements ActionListener {
    private final AddTransactionManipulator manipulator;
    private final AddTransactionCenter center;
    private final AddTransactionFacade facade;

    public DeleteAction(AddTransactionManipulator manipulator,
                        AddTransactionCenter center,
                        AddTransactionFacade facade) {
        this.manipulator = manipulator;
        this.center = center;
        this.facade = facade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = center.getTable().getSelectedRow();
        if(row == - 1) return;
        center.getTable().removeRow(row);
        facade.fixNumberColumn(center.getTable());
        facade.clear(manipulator);
    }
}
