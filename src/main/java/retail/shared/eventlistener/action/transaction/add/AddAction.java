package retail.shared.eventlistener.action.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.transaction.add.AddTransactionFacade;
import retail.shared.constant.ConstantDialog;
import retail.shared.entity.TransactionItemDetail;
import retail.view.main.tab.bot.transaction.center.add.AddTransactionCenter;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.AddTransactionManipulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddAction implements ActionListener {
    private final AddTransactionManipulator manipulator;
    private final AddTransactionCenter center;
    private final AddTransactionFacade facade;

    public AddAction(AddTransactionManipulator manipulator,
                     AddTransactionCenter center,
                     AddTransactionFacade facade) {
        this.manipulator = manipulator;
        this.center = center;
        this.facade = facade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!stringChecker()) {
            ConstantDialog.INVALID_INPUT();
            return;
        }
        TransactionItemDetail item = facade.createReportItem(fieldDataGrabber());
        center.getTable().addReportItem(item, "0");
        facade.fixNumberColumn(center.getTable());
        facade.clear(manipulator);
    }

    private boolean stringChecker() {
        return  !manipulator.getSold().getText().equals("0") &&
                (facade.isValidNumber(manipulator.getSold().getText(),
                                      manipulator.getSoldTotal().getText(),
                                      manipulator.getDiscountPercent().getText(),
                                      manipulator.getDiscountAmount().getText()));
    }

    private String @NotNull [] fieldDataGrabber() {
        final String[] data = new String[7];
        data[0] = Objects.requireNonNull(manipulator.getProduct().getSelectedItem()).toString();
        data[1] = manipulator.getPrice().getText();
        data[2] = manipulator.getSold().getText();
        data[3] = manipulator.getSoldTotal().getText();
        data[4] = manipulator.getDiscountPercent().getText();
        data[5] = manipulator.getDiscountAmount().getText();
        data[6] = facade.calculateItemAmount(data[3],data[5]);
        return data;
    }
}
