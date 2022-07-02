package retail.shared.custom.textarea;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CustomTextArea extends JTextArea {

    public CustomTextArea(int row, int column, boolean isEditable) {
        setRows(row);
        setColumns(column);
        setFullDescription(isEditable);
    }

    private void setFullDescription(boolean isEditable) {
        setLineWrap(true);
        setWrapStyleWord(true);
        setEditable(isEditable);
        setFont(new Font("Monospaced", Font.PLAIN,12));
        setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Full Description",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,12)));
    }

}
