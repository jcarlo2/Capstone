package retail.view.main;

import lombok.Getter;
import retail.view.main.panel.leftpanel.LeftBorderPanel;
import retail.view.main.panel.rightpanel.RightBorderPanel;

import javax.swing.*;
import java.awt.*;

@Getter
public class Main extends JPanel {
    private final RightBorderPanel rightBorderPanel = new RightBorderPanel();
    private final LeftBorderPanel leftBorderPanel = new LeftBorderPanel();

    public Main() {
        setLayout(new BorderLayout());
        add(leftBorderPanel,BorderLayout.WEST);
        add(rightBorderPanel,BorderLayout.CENTER);
    }
}


















