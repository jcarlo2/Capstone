package retail.view.main.panel.bot.manipulator.transaction.panel.add;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class Add extends JPanel {
    private final JPanel wrapper1 = new JPanel();
    private final AddCard addCard = new AddCard();

    private final JButton generateId = new JButton("Generate ID");
    private final JButton addReport = new JButton("Add Transaction");
    private final JButton returnReport = new JButton("Returned Transaction");

    public Add() {
        setLayout(new BorderLayout());
        wrapper1.setLayout(new GridLayout(1,2));
        wrapper1.add(addReport);
        wrapper1.add(returnReport);

        add(addCard,BorderLayout.CENTER);
        add(wrapper1,BorderLayout.SOUTH);
    }
}
