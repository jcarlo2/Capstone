package retail.view.main.panel.top;

import lombok.Getter;
import retail.component.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
public class UserPanel extends JPanel {
    private final JPanel wrapper = new JPanel();
    private final CustomJTextField employeeLastName = new CustomJTextField("Last Name");
    private final CustomJTextField employeeID = new CustomJTextField("Employee ID");
    private final JButton option = new JButton("Option");
    private final JButton logOut = new JButton("Log Out");

    public UserPanel() {
        employeeLastName.setEnabled(false);
        employeeLastName.setColumns(13);
        employeeLastName.setHorizontalAlignment(JTextField.CENTER);

        employeeID.setEnabled(false);
        employeeID.setHorizontalAlignment(JTextField.CENTER);

        setLayout(new GridLayout(2,1));
        wrapper.setLayout(new GridLayout(1,2));
        wrapper.add(employeeID);
        wrapper.add(employeeLastName);

        add(option);
        add(wrapper);
    }
}
