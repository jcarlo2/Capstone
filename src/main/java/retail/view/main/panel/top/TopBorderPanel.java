package retail.view.main.panel.top;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class TopBorderPanel extends JPanel {
    private final UserPanel userPanel = new UserPanel();
    private final NorthButton northButton = new NorthButton();
    public TopBorderPanel() {
        setLayout(new BorderLayout());
        add(userPanel,BorderLayout.WEST);
        add(northButton,BorderLayout.CENTER);
    }
}
