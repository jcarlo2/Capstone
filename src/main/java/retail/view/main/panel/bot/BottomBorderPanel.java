package retail.view.main.panel.bot;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class BottomBorderPanel extends JPanel {
    BottomMainCard bottomMainCard = new BottomMainCard();
    BottomManipulatorCard manipulatorCard = new BottomManipulatorCard();
    public BottomBorderPanel() {
        setLayout(new BorderLayout());
        add(bottomMainCard,BorderLayout.CENTER);
        add(manipulatorCard,BorderLayout.WEST);
    }
}
