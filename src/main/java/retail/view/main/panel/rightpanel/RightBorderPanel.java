package retail.view.main.panel.rightpanel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class RightBorderPanel extends JPanel {

    private final RightCenterPanel rightCenterPanel = new RightCenterPanel();
    private final NorthButton northButton = new NorthButton();

    public RightBorderPanel() {
        setLayout(new BorderLayout());

        add(northButton,BorderLayout.NORTH);
        add(rightCenterPanel,BorderLayout.CENTER);
    }
}
