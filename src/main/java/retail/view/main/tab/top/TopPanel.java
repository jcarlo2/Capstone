package retail.view.main.tab.top;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class TopPanel extends JPanel {
    private final User user = new User();
    private final North north = new North();
    public TopPanel() {
        setLayout(new BorderLayout());
        add(user,BorderLayout.WEST);
        add(north,BorderLayout.CENTER);
    }
}
