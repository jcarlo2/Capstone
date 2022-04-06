package retail.view.main.panel.leftpanel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class LeftBorderPanel extends JPanel {
    private final UserPanel userPanel = new UserPanel();
    private final LeftCenterPanel leftCenterPanel = new LeftCenterPanel();

    public LeftBorderPanel() {
        setLayout(new BorderLayout());
        add(userPanel,BorderLayout.NORTH);
        add(leftCenterPanel,BorderLayout.CENTER);
    }
}

