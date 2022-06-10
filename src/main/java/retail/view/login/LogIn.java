package retail.view.login;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


@Getter
public class LogIn extends JPanel{
    private final JTextField id = new JTextField("7777",15);
    private final JPasswordField password = new JPasswordField("admin",15);
    private final JButton logIn = new JButton("Log In");

    public LogIn() {
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
        setPreferredSize(new Dimension(350,150));
        setFocusable(true);
        setVisible(true);
    }

    public String getId() {
        return id.getText();
    }

    public String getPassword() {
        return String.valueOf(password.getPassword());
    }

    public void logInActionListener(ActionListener actionListener) {
        logIn.addActionListener(actionListener);
    }
}
