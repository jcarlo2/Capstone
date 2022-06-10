package retail.shared.eventlistener.mouse.transaction;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.transaction.add.ReturnTransactionFacade;
import retail.shared.entity.TransactionItemDetail;
import retail.view.main.tab.bot.transaction.center.add.ReturnedTransactionCenter;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.ReturnedTransactionManipulator;

import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ListMouse extends MouseAdapter {
    private final ReturnedTransactionManipulator manipulator;
    private final ReturnedTransactionCenter center;
    private final ReturnTransactionFacade facade;

    public ListMouse(ReturnedTransactionManipulator manipulator,
                     ReturnedTransactionCenter center,
                     ReturnTransactionFacade facade) {
        this.manipulator = manipulator;
        this.center = center;
        this.facade = facade;
    }

    @Override
    public void mousePressed(@NotNull MouseEvent e) {
        if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
            String id = manipulator.getList().getSelectedValue().substring(19,37);
            if(facade.isReportExist(id)) {
                setReportItem(id);
                setTopTotalAmount();
                setCredit();
            }
        }
    }

    private void setReportItem(String id) {
        ArrayList<TransactionItemDetail> itemList = facade.getAllReportItem(id);
        center.getTopTable().addAllReportItem(itemList);
        manipulator.getNewReportId().setText(facade.convertId(id));
        ((DefaultTableModel)center.getBotTable().getModel()).setRowCount(0);
    }

    private void setTopTotalAmount() {
        String newId = manipulator.getNewReportId().getText();
        if(newId.equals("")) return;
        center.getTopTotal().setText(facade.getReportTotalAmount(newId));
    }

    private void setCredit() {
        String total = center.getTopTotal().getText();
        BigDecimal credit = new BigDecimal(total);
        center.getCredit().setText(credit.multiply(BigDecimal.valueOf(-1)).toString());
    }
}
