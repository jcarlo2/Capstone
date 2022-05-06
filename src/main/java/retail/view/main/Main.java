package retail.view.main;

import lombok.Getter;
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.top.TopBorderPanel;

import javax.swing.*;
import java.awt.*;

@Getter
public class Main extends JPanel {
    private final TopBorderPanel topBorderPanel = new TopBorderPanel();
    private final BottomBorderPanel bottomBorderPanel = new BottomBorderPanel();

    public Main() {
        setLayout(new BorderLayout());
        add(topBorderPanel,BorderLayout.NORTH);
        add(bottomBorderPanel,BorderLayout.CENTER);
    }
}


















