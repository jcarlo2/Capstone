package retail.view.main.tab.bot.transaction.center;

import lombok.Getter;
import retail.view.main.tab.bot.transaction.center.add.AddCard;
import retail.view.main.tab.bot.transaction.center.view.View;

import javax.swing.*;
import java.awt.*;

@Getter
public class TransactionMainCard extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final View view = new View();
    private final AddCard addCard = new AddCard();

    public TransactionMainCard() {
        setLayout(cardLayout);
        add("view", view);
        add("add", addCard);
        cardLayout.show(this,"view");
    }
}
