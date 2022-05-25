package retail.view.main.tab.top;

import lombok.Getter;
import retail.shared.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

import static retail.shared.constant.Constant.COLOR_GREEN;

@Getter
public class UserPanel extends JPanel {
    private final JPanel wrapper = new JPanel(new GridLayout(1,2));
    private final CustomJTextField employeeLastName = new CustomJTextField("Last Name",COLOR_GREEN,false);
    private final CustomJTextField employeeID = new CustomJTextField("Employee ID",COLOR_GREEN,13);
    private final JButton option = new JButton("Option");
    private final JButton logOut = new JButton("Log Out");

    public UserPanel() {
        setLayout(new GridLayout(2,1));
        wrapper.add(employeeID);
        wrapper.add(employeeLastName);

        add(option);
        add(wrapper);
    }
}
