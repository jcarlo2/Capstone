package retail.view.main.tab.bot.transaction.manipulator.panel.add;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddCard extends JPanel {
    private final AddTransaction addTransaction = new AddTransaction();
    private final ReturnedTransaction returnedTransaction = new ReturnedTransaction();
    private final CardLayout card = new CardLayout();

    public AddCard() {
        setLayout(card);
        add("add",addTransaction);
        add("return",returnedTransaction);
        card.show(this,"add");
    }

    public AddTransaction getAdd() {
        return addTransaction;
    }

    public ReturnedTransaction getReturn() {
        return returnedTransaction;
    }
}