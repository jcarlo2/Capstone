package retail.view.main.panel.leftpanel;

import lombok.Getter;

import javax.swing.*;

@Getter
public class UserPanel extends JPanel {
    private final JTextField employeeLastName = new JTextField();
    private final JTextField employeeID = new JTextField();
    private final JButton logOut = new JButton("Log Out");

    public UserPanel() {
        employeeLastName.setEnabled(false);
        employeeLastName.setHorizontalAlignment(JTextField.CENTER);

        employeeID.setEnabled(false);
        employeeID.setHorizontalAlignment(JTextField.CENTER);

        add(employeeID);
        add(employeeLastName);
        add(logOut);
    }
}
