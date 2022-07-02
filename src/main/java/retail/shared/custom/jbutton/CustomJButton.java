package retail.shared.custom.jbutton;

import javax.swing.*;
import java.awt.*;

public class CustomJButton extends JButton {
    public CustomJButton(String title) {
        setText(title);
        setFont(new Font("Monospaced", Font.BOLD,20));
        setFocusPainted(false);
    }
}
