package retail.view.main;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

@Getter
public class MainFrame extends JFrame {
    private final Main main = new Main();

    public MainFrame(String TITLE) {
        setTitle(TITLE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // set to maximize at first
        setMinimumSize(new Dimension(1100,730));
        ImageIcon img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/rmlogo.png")));
        setIconImage(img.getImage());

        add(main);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
    }
}
