package retail.view.main.tab.bot;

import lombok.Getter;
import retail.view.main.tab.bot.inventory.manipulator.InventoryManipulator;
import retail.view.main.tab.bot.transaction.manipulator.TransactionManipulator;

import javax.swing.*;
import java.awt.*;

@Getter
public class BottomManipulatorCard extends JPanel {
    private final InventoryManipulator inventoryManipulator = new InventoryManipulator();
    private final TransactionManipulator transactionManipulator = new TransactionManipulator();
    private final CardLayout card = new CardLayout();

    public BottomManipulatorCard() {
        setLayout(card);
        add("inventory",inventoryManipulator);
        add("salesReport", transactionManipulator);
        card.show(this, "salesReport");
    }
}
