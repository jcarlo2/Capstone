package retail.view.main.tab.bot.transaction.manipulator.panel;

import lombok.Getter;
import retail.shared.custom.jpanel.ViewManipulator;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.Add;

import javax.swing.*;
import java.awt.*;

@Getter
public class TransactionManipulatorCard extends JPanel {
    private final Add add = new Add();
    private final ViewManipulator viewManipulator = new ViewManipulator();
    private final CardLayout cardLayout = new CardLayout();

    public TransactionManipulatorCard() {
        setLayout(cardLayout);
        add("add", add);
        add("view", viewManipulator);
        cardLayout.show(this,"view");
    }
}
