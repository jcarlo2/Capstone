package retail.view.main.tab.top;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class TopBorderPanel extends JPanel {
    private final UserPanel userPanel = new UserPanel();
    private final NorthPanel northPanel = new NorthPanel();
    public TopBorderPanel() {
        setLayout(new BorderLayout());
        add(userPanel,BorderLayout.WEST);
        add(northPanel,BorderLayout.CENTER);
    }
}
