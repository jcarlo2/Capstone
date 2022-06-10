package retail.view.main.tab.bot.transaction.manipulator.panel.add;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddCard extends JPanel {
    private final AddTransactionManipulator addTransactionManipulator = new AddTransactionManipulator();
    private final ReturnedTransactionManipulator returnedTransactionManipulator = new ReturnedTransactionManipulator();
    private final CardLayout card = new CardLayout();

    public AddCard() {
        setLayout(card);
        add("add", addTransactionManipulator);
        add("return", returnedTransactionManipulator);
        card.show(this,"add");
    }


}
