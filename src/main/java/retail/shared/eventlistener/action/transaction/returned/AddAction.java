package retail.shared.eventlistener.action.transaction.returned;

import retail.model.facade.transaction.add.ReturnTransactionFacade;
import retail.shared.custom.CustomJDialog;
import retail.view.main.tab.bot.transaction.center.add.ReturnedTransactionCenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAction implements ActionListener {
    private final ReturnedTransactionCenter center;
    private final ReturnTransactionFacade facade;
    private final CustomJDialog returnDialog;

    public AddAction(ReturnedTransactionCenter center,
                         ReturnTransactionFacade facade,
                         CustomJDialog returnDialog) {
        this.center = center;
        this.facade = facade;
        this.returnDialog = returnDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = center.getTopTable().getSelectedRow();
        if(row == -1) return;
        String[] data =  facade.getRowData(center.getTopTable(), row);
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
