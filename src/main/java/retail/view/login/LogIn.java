package retail.view.login;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static retail.constant.ConstantString.*;

@Getter
@Setter
public class LogIn extends JPanel{
    private final JTextField id = new JTextField(EMPLOYEE_ID,15);
    private final JPasswordField password = new JPasswordField(PASSWORD,15);
    private final JButton logIn = new JButton(LOGIN);

    public LogIn() {
        logInSetUp();
    }

    public void logInSetUp() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(id,constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(password,constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(logIn,constraints);
        setPreferredSize(new Dimension(300,150));
        setFocusable(true);
        setVisible(true);
    }
}
