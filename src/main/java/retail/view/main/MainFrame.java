package retail.view.main;

import lombok.Getter;
import retail.shared.constant.ImageDirectory;

import javax.swing.*;
import java.awt.*;



@Getter
public class MainFrame extends JFrame implements ImageDirectory {
    private final Main main = new Main();

    public MainFrame(String TITLE) {
        setTitle(TITLE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // set to maximize at first
        setMinimumSize(new Dimension(1100,730));
        setIconImage(SYSTEM_LOGO());
        add(main);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
    }
}
