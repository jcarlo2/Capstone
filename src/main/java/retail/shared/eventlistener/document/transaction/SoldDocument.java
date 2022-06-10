package retail.shared.eventlistener.document.transaction;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.transaction.add.AddTransactionFacade;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.AddTransactionManipulator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigDecimal;

public final class SoldDocument implements DocumentListener {
    private final AddTransactionFacade facade;
    private final JTextField soldTotal;
    private final JTextField discountAmount;
    private final JTextField price;
    private final JTextField sold;
    private final JTextField discountPercent;

    public SoldDocument(AddTransactionFacade facade, @NotNull AddTransactionManipulator manipulator) {
        this.facade = facade;
        sold = manipulator.getSold();
        soldTotal = manipulator.getSoldTotal();
        discountPercent = manipulator.getDiscountPercent();
        discountAmount = manipulator.getDiscountAmount();
        price = manipulator.getPrice();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        textUpdate();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        textUpdate();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {}

    private void textUpdate() {
        if(!sold.isFocusOwner()) return;
        if(!stringChecker()) return;
        String total = facade.calculateSoldTotal(new BigDecimal(price.getText()), new BigDecimal(sold.getText()));
        soldTotal.setText(total);
        String discount = facade.calculateDiscountAmount(new BigDecimal(soldTotal.getText()),new BigDecimal(discountPercent.getText()));
        discountAmount.setText(discount);
    }

    private boolean stringChecker() {
        return facade.isValidNumber(sold.getText()) && facade.isValidNumber(price.getText()) &&
                facade.isValidNumber(soldTotal.getText()) && facade.isValidNumber(discountPercent.getText());
    }
}
