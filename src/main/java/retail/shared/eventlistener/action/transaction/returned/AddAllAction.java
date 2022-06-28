package retail.shared.eventlistener.action.transaction.returned;

import retail.model.facade.transaction.add.ReturnTransactionFacade;
import retail.shared.entity.TransactionItemDetail;
import retail.view.main.tab.bot.transaction.center.add.ReturnedTransactionCenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static retail.shared.constant.ConstantDialog.ADD_ALL;

public class AddAllAction implements ActionListener {
    private final ReturnedTransactionCenter center;
    private final ReturnTransactionFacade facade;

    public AddAllAction(ReturnedTransactionCenter center,
                     ReturnTransactionFacade facade) {
        this.center = center;
        this.facade = facade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(ADD_ALL() != 0) return;
        ArrayList<TransactionItemDetail> itemList = facade.getRowDataByNoneReason(center.getTopTable());
        for(TransactionItemDetail item : itemList) {
            center.getBotTable().addReportItem(item, "0");
        }
        facade.removeRowWithReason(center.getTopTable());
        facade.fixNumberColumn(center.getBotTable());
        facade.fixNumberColumn(center.getTopTable());
    }
}
