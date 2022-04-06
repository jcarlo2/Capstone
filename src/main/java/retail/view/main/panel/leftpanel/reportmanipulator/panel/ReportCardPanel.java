package retail.view.main.panel.leftpanel.reportmanipulator.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ReportCardPanel extends JPanel {
    private final AddPanel addPanel = new AddPanel();
    private final DeletePanel deletePanel = new DeletePanel();
    private final UpdatePanel updatePanel = new UpdatePanel();
    private final ViewPanel viewPanel = new ViewPanel();
    private final CardLayout cardLayout = new CardLayout();

    public ReportCardPanel() {
        setLayout(cardLayout);
        add("add",addPanel);
        add("delete",deletePanel);
        add("update",updatePanel);
        add("view",viewPanel);
        cardLayout.show(this,"view");
    }
}
