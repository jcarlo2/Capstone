package retail.view.main.panel.leftpanel.reportmanipulator.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddViewDeleteUpdatePanel extends JPanel {
    private final JButton add = new JButton("Add");
    private final JButton view = new JButton("View");
    private final JButton delete = new JButton("Delete");
    private final JButton update = new JButton("Update");

    public AddViewDeleteUpdatePanel() {
        setLayout(new BorderLayout());
        setButtonPanel();
    }

    private void setButtonPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(add,constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(delete,constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        add(update,constraints);

        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(view,constraints);
    }
}
