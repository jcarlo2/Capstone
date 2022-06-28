package retail.shared.eventlistener.tablemodel;

import retail.model.facade.transaction.add.ReturnTransactionFacade;
import retail.view.main.tab.bot.transaction.center.add.ReturnedTransactionCenter;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.math.BigDecimal;

public class BotTableModel implements TableModelListener {
    private final ReturnedTransactionCenter center;
    private final ReturnTransactionFacade facade;

    public BotTableModel(ReturnedTransactionCenter center,
                        ReturnTransactionFacade facade) {
        this.center = center;
        this.facade = facade;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        calculateNewTotalAmount();
        calculateCredit();
    }

    private void calculateNewTotalAmount() {
        String newTotal = facade.calculateNewTotal(center.getBotTable());
        center.getNewTotal().setText(newTotal);
    }

    private void calculateCredit() {
        BigDecimal credit = new BigDecimal(center.getTopTotal().getText());
        credit = credit.multiply(BigDecimal.valueOf(-1));
        BigDecimal newTotal = new BigDecimal(center.getNewTotal().getText());
        center.getCredit().setText(credit.add(newTotal).toString());
    }
}
