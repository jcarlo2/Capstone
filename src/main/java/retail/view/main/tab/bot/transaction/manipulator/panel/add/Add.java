package retail.view.main.tab.bot.transaction.manipulator.panel.add;

import lombok.Getter;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

@Getter
public class Add extends JPanel {
    private final JPanel wrapper1 = new JPanel();
    private final AddCard addCard = new AddCard();
    private final JButton addReport = new JButton("Add Transaction");
    private final JButton returnReport = new JButton("Returned Transaction");

    public Add() {
        setLayout(new BorderLayout());
        addReport.setEnabled(false);
        wrapper1.setLayout(new GridLayout(1,2));
        wrapper1.add(addReport);
        wrapper1.add(returnReport);

        add(addCard,BorderLayout.CENTER);
        add(wrapper1,BorderLayout.SOUTH);
    }

    public void addActionListener(ActionListener actionListener) {
        addReport.addActionListener(actionListener);
    }

    public void returnActionListener(ActionListener actionListener) {
        returnReport.addActionListener(actionListener);
    }
}
