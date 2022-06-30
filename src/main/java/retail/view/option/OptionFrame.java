package retail.view.option;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

@Getter
public class OptionFrame extends JFrame {
    private final Option option = new Option();

    public OptionFrame(String TITLE) {
        setTitle(TITLE);
        setPreferredSize(new Dimension(400,400));
        ImageIcon img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/rm_logo.png")));
        setIconImage(img.getImage());
        add(option);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(false);
    }
}
