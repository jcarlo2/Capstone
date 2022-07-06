package retail.view.main.tab.bot.transaction.center;

import lombok.Getter;
import retail.view.main.tab.bot.transaction.center.view.TransactionViewCenter;
import retail.view.main.tab.bot.transaction.center.add.AddCenter;

import javax.swing.*;
import java.awt.*;

@Getter
public class TransactionMain extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final TransactionViewCenter transactionViewCenter = new TransactionViewCenter();
    private final AddCenter addCenter = new AddCenter();

    public TransactionMain() {
        setLayout(cardLayout);
        add("view", transactionViewCenter);
        add("add", addCenter);
        cardLayout.show(this,"view");
    }
}
