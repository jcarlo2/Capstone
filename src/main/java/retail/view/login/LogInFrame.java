package retail.view.login;

import lombok.Getter;

import javax.swing.*;

@Getter
public class LogInFrame extends JFrame {
    private final LogIn logIn = new LogIn();

    public LogInFrame(String TITLE) {
        setTitle(TITLE);
        ImageIcon img = new ImageIcon("src/main/resources/images/rm_logo.png");
        setIconImage(img.getImage());
        add(logIn);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
