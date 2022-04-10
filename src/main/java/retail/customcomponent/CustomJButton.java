package retail.customcomponent;

import javax.swing.*;
import java.awt.*;

public class CustomJButton extends JButton {
    public CustomJButton(String title) {
        setText(title);
        setFont(new Font("Monospaced", Font.BOLD,20));
    }
}
