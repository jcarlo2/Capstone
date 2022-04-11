package retail.customcomponent.jtextfield;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CustomJTextField extends JTextField {
    public CustomJTextField(String title,Color color) {
        setTitledBorder(title,color);
        setHorizontalAlignment(SwingConstants.CENTER);
        setText("0");
    }

    private void setTitledBorder(String title, Color color) {
        setBorder(BorderFactory.createTitledBorder
                (new LineBorder(color),title,
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
    }
}
