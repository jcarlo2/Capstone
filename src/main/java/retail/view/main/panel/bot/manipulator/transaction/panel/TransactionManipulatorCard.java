package retail.view.main.panel.bot.manipulator.transaction.panel;

import lombok.Getter;
import retail.view.main.panel.bot.manipulator.transaction.panel.add.Add;

import javax.swing.*;
import java.awt.*;

@Getter
public class TransactionManipulatorCard extends JPanel {
    private final Add add = new Add();
    private final Delete delete = new Delete();
    private final View view = new View();
    private final CardLayout cardLayout = new CardLayout();

    public TransactionManipulatorCard() {
        setLayout(cardLayout);
        add("add", add);
        add("delete", delete);
        add("view", view);
        cardLayout.show(this,"view");
    }
}
