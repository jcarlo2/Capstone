package retail.view.main;

import lombok.Getter;
import retail.view.main.tab.bot.BottomPanel;
import retail.view.main.tab.top.TopPanel;

import javax.swing.*;
import java.awt.*;

@Getter
public class Main extends JPanel {
    private final TopPanel topPanel = new TopPanel();
    private final BottomPanel bottomPanel = new BottomPanel();

    public Main() {
        setLayout(new BorderLayout());
        add(topPanel,BorderLayout.NORTH);
        add(bottomPanel,BorderLayout.CENTER);
    }
}


















