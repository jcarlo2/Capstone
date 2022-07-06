package retail.view.main.tab.top;

import lombok.Getter;
import lombok.Setter;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

import static retail.shared.constant.Constant.COLOR_GREEN;

@Getter
@Setter
public class User extends JPanel {
    private final JPanel wrapper = new JPanel(new GridLayout(1,2));
    private final CustomJTextField lastName = new CustomJTextField("Last Name",COLOR_GREEN,false);
    private final CustomJTextField id = new CustomJTextField("Employee ID",COLOR_GREEN,13);
    private final JButton option = new JButton("Option");
    private final JButton logOut = new JButton("Log Out");

    public User() {
        setLayout(new GridLayout(2,1));
        wrapper.add(id);
        wrapper.add(lastName);

        add(option);
        add(wrapper);
    }
}
