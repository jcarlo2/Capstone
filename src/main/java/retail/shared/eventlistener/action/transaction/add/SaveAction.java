package retail.shared.eventlistener.action.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.transaction.add.AddTransactionFacade;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;
import retail.view.main.tab.bot.transaction.center.add.AddTransactionCenter;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.AddTransactionManipulator;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static retail.shared.constant.ConstantDialog.EMPTY_FIELD;
import static retail.shared.constant.ConstantDialog.SAVED_REPORT;

public class SaveAction implements ActionListener {
    private final AddTransactionManipulator manipulator;
    private final AddTransactionCenter center;
    private final AddTransactionFacade facade;
    private final String lastName;

    public SaveAction(AddTransactionManipulator manipulator,
                        AddTransactionCenter center,
                        AddTransactionFacade facade,
                        String lastName) {
        this.manipulator = manipulator;
        this.center = center;
        this.facade = facade;
        this.lastName = lastName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(center.getTable().getRowCount() == 0) {
            EMPTY_FIELD();
            return;
        }
        ArrayList<TransactionItemDetail> itemList = facade.convertDataToItems(center.getTable());
        TransactionDetail report = facade.createTransactionDetail(gatherReportData());
        facade.saveReport(report,itemList);
        ((DefaultTableModel)center.getTable().getModel()).setRowCount(0);
        SAVED_REPORT();
    }

    private String @NotNull [] gatherReportData() {
        String[] data = new String[8];
        data[0] = manipulator.getReportId().getText();
        data[1] = "";
        data[2] = "";
        data[3] = lastName;
        data[4] = center.getTotalAmountText();
        data[5] = "";
        data[6] = "";
        data[7] = "";
        return data;
    }
}
