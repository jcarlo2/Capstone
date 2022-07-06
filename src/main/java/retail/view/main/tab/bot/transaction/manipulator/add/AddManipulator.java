package retail.view.main.tab.bot.transaction.manipulator.add;

import lombok.Getter;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;

@Getter
public class AddManipulator extends JPanel {
    private final JPanel wrapper1 = new JPanel(new GridLayout(1,2));
    private final JButton addReport = new JButton("Add Transaction");
    private final JButton returnReport = new JButton("Returned Transaction");

    private final CardLayout card = new CardLayout();
    private final JPanel wrapper2 = new JPanel(card);
    private final AddTransactionManipulator addTransactionManipulator = new AddTransactionManipulator();
    private final ReturnedTransactionManipulator returnedTransactionManipulator = new ReturnedTransactionManipulator();

    public AddManipulator() {
        setLayout(new BorderLayout());
        setWrapper1();
        setWrapper2();

        add(wrapper2,BorderLayout.CENTER);
        add(wrapper1,BorderLayout.SOUTH);
    }

    private void setWrapper1() {
        addReport.setEnabled(false);
        wrapper1.add(addReport);
        wrapper1.add(returnReport);
    }

    private void setWrapper2() {
        wrapper2.add("add", addTransactionManipulator);
        wrapper2.add("return", returnedTransactionManipulator);
        card.show(wrapper2,"add");
    }
}
