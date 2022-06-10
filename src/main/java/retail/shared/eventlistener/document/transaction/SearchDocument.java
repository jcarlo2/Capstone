package retail.shared.eventlistener.document.transaction;

import retail.model.facade.transaction.add.ReturnTransactionFacade;
import retail.shared.entity.TransactionDetail;
import retail.view.main.tab.bot.transaction.center.add.ReturnedTransactionCenter;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.ReturnedTransactionManipulator;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;

public class SearchDocument implements DocumentListener {
    private final ReturnedTransactionManipulator manipulator;
    private final ReturnTransactionFacade facade;

    public SearchDocument(ReturnedTransactionManipulator manipulator,
                          ReturnTransactionFacade facade) {
        this.manipulator = manipulator;
        this.facade = facade;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        search();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        search();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
    
    private void search() {
        if (manipulator.getSearch().getText().equals("")) {
            manipulator.getList().populateTransactionList(facade.getTransactionReportList());
            return;
        }
        ArrayList<TransactionDetail> reportList = facade.findAllReportByString(manipulator.getSearch().getText());
        manipulator.getList().populateTransactionList(reportList);
    }
}
