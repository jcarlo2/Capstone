package retail.view.main;

import lombok.Getter;
import retail.view.main.tab.bot.BottomPanel;
import retail.view.main.tab.top.TopBorderPanel;

import javax.swing.*;
import java.awt.*;

@Getter
public class Main extends JPanel {
    private final TopBorderPanel topBorderPanel = new TopBorderPanel();
    private final BottomPanel bottomPanel = new BottomPanel();

    public Main() {
        setLayout(new BorderLayout());
        add(topBorderPanel,BorderLayout.NORTH);
        add(bottomPanel,BorderLayout.CENTER);
    }
}


















