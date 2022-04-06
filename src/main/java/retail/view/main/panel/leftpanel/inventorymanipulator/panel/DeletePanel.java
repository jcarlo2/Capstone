package retail.view.main.panel.leftpanel.inventorymanipulator.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class DeletePanel extends JPanel {
    private final JTextField id = new JTextField("ID");
    private final JTextField getId = new JTextField(15);
    private final JButton delete = new JButton("Delete");

    public DeletePanel() {
        setLayout(new GridLayout(13,1));
        id.setHorizontalAlignment(JLabel.CENTER);
        id.setEnabled(false);
        add(id);

        getId.setHorizontalAlignment(JLabel.CENTER);
        add(getId);
        add(delete);
    }
}
