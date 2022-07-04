package retail.view.main.tab.bot.inventory.manipulator.panel;

import lombok.Getter;
import retail.shared.custom.jpanel.ViewManipulator;
import retail.view.main.tab.bot.inventory.manipulator.panel.add.Add;
import retail.view.main.tab.bot.inventory.manipulator.panel.product.ProductManipulator;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryManipulatorCard extends JPanel {
    private final Add add = new Add();
    private final ViewManipulator view = new ViewManipulator();
    private final ProductManipulator productManipulator = new ProductManipulator();
    private final CardLayout cardLayout = new CardLayout();

    public InventoryManipulatorCard() {
        setLayout(cardLayout);

        add(add,"add");
        add(view,"view");
        add(productManipulator,"product");
        cardLayout.show(this,"product");
    }
}





























