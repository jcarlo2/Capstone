package retail.view.main.panel.bot.manipulator.reportmanipulator.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddViewDeletePanel extends JPanel {
    private final JButton add = new JButton("Add");
    private final JButton view = new JButton("View");
    private final JButton delete = new JButton("Delete");

    public AddViewDeletePanel() {
        setLayout(new GridLayout());
        view.setEnabled(false);
        add(add);
        add(delete);
        add(view);
    }
}
