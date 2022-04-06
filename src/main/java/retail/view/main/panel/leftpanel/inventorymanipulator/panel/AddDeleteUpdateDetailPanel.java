package retail.view.main.panel.leftpanel.inventorymanipulator.panel;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddDeleteUpdateDetailPanel extends JPanel {
    private final JButton add = new JButton("Add");
    private final JButton delete = new JButton("Delete");
    private final JButton update = new JButton("Update");
    private final JButton detail = new JButton("Details");
    private Boolean isDetail = false;
    private Boolean isUpdate = false;

    public AddDeleteUpdateDetailPanel() {
        setButtonPanel();
    }

    public void setButtonPanel() {
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
        add(detail,constraints);
    }
}



















































