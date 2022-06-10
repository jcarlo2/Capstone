package retail.shared.eventlistener.document.transaction;

import retail.model.facade.transaction.add.AddTransactionFacade;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigDecimal;

public class DiscountAmountDocument implements DocumentListener {
    private final AddTransactionFacade addFacade;
    private final JTextField soldTotal;
    private final JTextField discountAmount;
    private final JTextField discountPercentage;

    public DiscountAmountDocument(AddTransactionFacade addFacade,
                                  JTextField soldTotal,
                                  JTextField discountAmount,
                                  JTextField discountPercentage) {
        this.addFacade = addFacade;
        this.soldTotal = soldTotal;
        this.discountAmount = discountAmount;
        this.discountPercentage = discountPercentage;
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
        if(!discountAmount.isFocusOwner()) return;
        if(!stringChecker()) return;
        BigDecimal soldTotal = new BigDecimal(this.soldTotal.getText());
        BigDecimal amount = new BigDecimal(discountAmount.getText());
        String percent = addFacade.calculateDiscountPercentage(soldTotal,amount);
        SwingUtilities.invokeLater(() -> discountPercentage.setText(percent));
    }

    private boolean stringChecker() {
        return addFacade.isValidNumber(soldTotal.getText()) && addFacade.isValidNumber(discountAmount.getText());
    }
}
