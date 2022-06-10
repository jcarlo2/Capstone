package retail.shared.eventlistener.document.transaction;

import retail.model.facade.transaction.add.AddTransactionFacade;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigDecimal;

public class DiscountPercentageDocument implements DocumentListener {
    private final AddTransactionFacade addFacade;
    private final JTextField soldTotal;
    private final JTextField discountAmount;
    private final JTextField discountPercent;

    public DiscountPercentageDocument(AddTransactionFacade addFacade,
                                      JTextField soldTotal,
                                      JTextField discountAmount,
                                      JTextField discountPercent) {
        this.addFacade = addFacade;
        this.soldTotal = soldTotal;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
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
        if(!discountPercent.isFocusOwner()) return;
        if(!stringChecker()) return;
        BigDecimal sold = new BigDecimal(soldTotal.getText());
        BigDecimal percent = new BigDecimal(discountPercent.getText());
        String discount = addFacade.calculateDiscountAmount(sold,percent);
        SwingUtilities.invokeLater(() -> discountAmount.setText(discount));
    }

    private boolean stringChecker() {
        return addFacade.isValidNumber(soldTotal.getText()) && addFacade.isValidNumber(discountPercent.getText());
    }
}
