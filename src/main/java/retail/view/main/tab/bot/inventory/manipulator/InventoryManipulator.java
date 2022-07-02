package retail.view.main.tab.bot.inventory.manipulator;

import lombok.Getter;
import retail.view.main.tab.bot.inventory.manipulator.panel.AddViewDelete;
import retail.view.main.tab.bot.inventory.manipulator.panel.InventoryManipulatorCard;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryManipulator extends JPanel {
    private final AddViewDelete addViewDelete = new AddViewDelete();
    private final InventoryManipulatorCard inventoryManipulatorCard = new InventoryManipulatorCard();
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);

    public InventoryManipulator() {
        setLayout(new BorderLayout());

        addViewDelete.getProduct().setFont(sansSerif);
        addViewDelete.getProduct().setEnabled(false);

        add(addViewDelete,BorderLayout.NORTH);
        add(inventoryManipulatorCard,BorderLayout.CENTER);
    }
}
