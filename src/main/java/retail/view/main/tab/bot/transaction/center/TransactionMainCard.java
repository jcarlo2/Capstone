package retail.view.main.tab.bot.transaction.center;

import lombok.Getter;
import retail.shared.custom.jpanel.ViewCenter;
import retail.view.main.tab.bot.transaction.center.add.AddCard;

import javax.swing.*;
import java.awt.*;

@Getter
public class TransactionMainCard extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final ViewCenter viewCenter = new ViewCenter();
    private final AddCard addCard = new AddCard();

    public TransactionMainCard() {
        setLayout(cardLayout);
        add("view", viewCenter);
        add("add", addCard);
        cardLayout.show(this,"view");
    }
}
