package retail.view.main.tab.bot.transaction.manipulator.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddViewPanel extends JPanel {
    private final JButton add = new JButton("Add");
    private final JButton view = new JButton("View");

    public AddViewPanel() {
        setLayout(new GridLayout(1,2));
        view.setEnabled(false);
        add(add);
        add(view);
    }
}
