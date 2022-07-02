package retail.view.main.tab.bot.transaction.center;

import lombok.Getter;
import retail.view.main.tab.bot.transaction.center.add.AddCard;
import retail.view.main.tab.bot.transaction.center.view.ViewTransactionCenter;

import javax.swing.*;
import java.awt.*;

@Getter
public class TransactionMainCard extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final ViewTransactionCenter viewTransactionCenter = new ViewTransactionCenter();
    private final AddCard addCard = new AddCard();

    public TransactionMainCard() {
        setLayout(cardLayout);
        add("view", viewTransactionCenter);
        add("add", addCard);
        cardLayout.show(this,"view");
    }
}
