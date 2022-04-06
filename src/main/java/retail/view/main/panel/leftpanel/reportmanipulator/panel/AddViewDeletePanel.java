package retail.view.main.panel.leftpanel.reportmanipulator.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddViewDeletePanel extends JPanel {
    private final JButton add = new JButton("Add");
    private final JButton view = new JButton("View");
    private final JButton delete = new JButton("Delete");

    public AddViewDeletePanel() {
        setLayout(new BorderLayout());
        setButtonPanel();
    }

    private void setButtonPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(add,constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(delete,constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(view,constraints);
    }
}
