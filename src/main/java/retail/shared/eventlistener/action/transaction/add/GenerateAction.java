package retail.shared.eventlistener.action.transaction.add;

import retail.model.facade.transaction.add.AddTransactionFacade;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.AddTransactionManipulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateAction implements ActionListener {
    private final AddTransactionManipulator manipulator;
    private final AddTransactionFacade facade;

    public GenerateAction(AddTransactionManipulator manipulator,
                        AddTransactionFacade facade) {
        this.manipulator = manipulator;
        this.facade = facade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String reportId = facade.generateId();
        manipulator.getReportId().setText(reportId);
    }
}
