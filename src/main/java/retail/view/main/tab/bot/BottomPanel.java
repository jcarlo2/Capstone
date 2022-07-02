package retail.view.main.tab.bot;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class BottomPanel extends JPanel {
    private final BottomMainCard bottomMainCard = new BottomMainCard();
    private final BottomManipulatorCard manipulatorCard = new BottomManipulatorCard();
    public BottomPanel() {
        setLayout(new BorderLayout());
        add(bottomMainCard,BorderLayout.CENTER);
        add(manipulatorCard,BorderLayout.WEST);
    }
}
