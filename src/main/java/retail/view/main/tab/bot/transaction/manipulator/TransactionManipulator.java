package retail.view.main.tab.bot.transaction.manipulator;

import lombok.Getter;
import retail.shared.custom.jpanel.ViewManipulator;
import retail.view.main.tab.bot.transaction.manipulator.add.AddManipulator;

import javax.swing.*;
import java.awt.*;

@Getter
public class TransactionManipulator extends JPanel {
    private final JPanel wrapper1 = new JPanel(new GridLayout(1,2));
    private final JButton add = new JButton("Add");
    private final JButton view = new JButton("View");

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel wrapper2 = new JPanel(cardLayout);
    private final AddManipulator addManipulator = new AddManipulator();
    private final ViewManipulator viewManipulator = new ViewManipulator("All","Valid Only","Valid Type");

    public TransactionManipulator() {
        setLayout(new BorderLayout());
        setWrapper1();
        setWrapper2();

        add(wrapper1,BorderLayout.NORTH);
        add(wrapper2,BorderLayout.CENTER);
    }

    private void setWrapper2() {
        wrapper2.add("add", addManipulator);
        wrapper2.add("view", viewManipulator);
        cardLayout.show(wrapper2,"view");
    }

    private void setWrapper1() {
        view.setEnabled(false);
        wrapper1.add(add);
        wrapper1.add(view);
    }
}
