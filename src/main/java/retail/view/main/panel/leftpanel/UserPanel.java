package retail.view.main.panel.leftpanel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class UserPanel extends JPanel {
    private final JTextField employeeLastName = new JTextField(10);
    private final JTextField employeeID = new JTextField(10);
    private final JButton logOut = new JButton("Log Out");

    public UserPanel() {
        employeeLastName.setEnabled(false);
        employeeLastName.setHorizontalAlignment(JTextField.CENTER);

        employeeID.setEnabled(false);
        employeeID.setHorizontalAlignment(JTextField.CENTER);
        setLayout(new GridLayout(1,3));
        add(employeeID);
        add(employeeLastName);
        add(logOut);
    }
}
