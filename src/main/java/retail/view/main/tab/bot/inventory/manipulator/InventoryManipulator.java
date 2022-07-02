package retail.view.main.tab.bot.inventory.manipulator;

import lombok.Getter;
import retail.view.main.tab.bot.inventory.manipulator.panel.AddViewProduct;
import retail.view.main.tab.bot.inventory.manipulator.panel.InventoryManipulatorCard;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryManipulator extends JPanel {
    private final AddViewProduct addViewProduct = new AddViewProduct();
    private final InventoryManipulatorCard inventory = new InventoryManipulatorCard();
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);

    public InventoryManipulator() {
        setLayout(new BorderLayout());

        addViewProduct.getProduct().setFont(sansSerif);
        addViewProduct.getProduct().setEnabled(false);

        add(addViewProduct,BorderLayout.NORTH);
        add(inventory,BorderLayout.CENTER);
    }
}
