package retail.view.main.panel.leftpanel;

import lombok.Getter;
import retail.view.main.panel.leftpanel.inventorymanipulator.InventoryManipulator;
import retail.view.main.panel.leftpanel.inventorymanipulator.panel.UpdatePanel;
import retail.view.main.panel.leftpanel.reportmanipulator.SalesReportManipulator;

import javax.swing.*;
import java.awt.*;

@Getter
public class LeftCenterPanel extends JPanel {
    private final InventoryManipulator inventoryManipulator = new InventoryManipulator();
    private final SalesReportManipulator salesReportManipulator = new SalesReportManipulator();
    private final CardLayout card = new CardLayout();

    public LeftCenterPanel() {
        setLayout(card);
        add("inventory",inventoryManipulator);
        add("salesReport", salesReportManipulator);
        card.show(this, "inventory");
    }
}
