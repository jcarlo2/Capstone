package retail.view.main.panel.bot;

import lombok.Getter;
import retail.view.main.panel.bot.manipulator.inventorymanipulator.InventoryManipulator;
import retail.view.main.panel.bot.manipulator.reportmanipulator.SalesReportManipulator;

import javax.swing.*;
import java.awt.*;

@Getter
public class BottomManipulatorCard extends JPanel {
    private final InventoryManipulator inventoryManipulator = new InventoryManipulator();
    private final SalesReportManipulator salesReportManipulator = new SalesReportManipulator();
    private final CardLayout card = new CardLayout();

    public BottomManipulatorCard() {
        setLayout(card);
        add("inventory",inventoryManipulator);
        add("salesReport", salesReportManipulator);
        card.show(this, "salesReport");
    }
}
