package retail.view.main.tab.bot.transaction.manipulator.panel;

import lombok.Getter;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.Add;
import retail.view.main.tab.bot.transaction.manipulator.panel.view.ViewTransactionManipulator;

import javax.swing.*;
import java.awt.*;

@Getter
public class TransactionManipulatorCard extends JPanel {
    private final Add add = new Add();
    private final ViewTransactionManipulator viewTransactionManipulator = new ViewTransactionManipulator();
    private final CardLayout cardLayout = new CardLayout();

    public TransactionManipulatorCard() {
        setLayout(cardLayout);
        add("add", add);
        add("view", viewTransactionManipulator);
        cardLayout.show(this,"view");
    }
}
