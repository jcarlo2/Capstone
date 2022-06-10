package retail.view.main.tab.bot.transaction.center.add;

import lombok.Getter;
import retail.shared.custom.CustomJDialog;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddCard extends JPanel {
    private final AddTransactionCenter addTransactionCenter = new AddTransactionCenter();
    private final ReturnedTransactionCenter returnedTransactionCenter = new ReturnedTransactionCenter();
    private final CustomJDialog returnDialog = new CustomJDialog();
    private final CardLayout card = new CardLayout();

    public AddCard() {
        setLayout(card);
        add("add", addTransactionCenter);
        add("return", returnedTransactionCenter);
        card.show(this, "add");
    }
}
