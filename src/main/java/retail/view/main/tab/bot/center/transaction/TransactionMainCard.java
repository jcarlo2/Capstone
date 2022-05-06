package retail.view.main.tab.bot.center.transaction;

import lombok.Getter;
import retail.view.main.tab.bot.center.transaction.add.AddCard;
import retail.view.main.tab.bot.center.transaction.add.AddTransaction;

import javax.swing.*;
import java.awt.*;

@Getter
public class TransactionMainCard extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final ViewMain viewMain = new ViewMain();
    private final AddCard addCard = new AddCard();

    public TransactionMainCard() {
        setLayout(cardLayout);
        add("view", viewMain);
        add("add", addCard);
        cardLayout.show(this,"view");
    }
}
