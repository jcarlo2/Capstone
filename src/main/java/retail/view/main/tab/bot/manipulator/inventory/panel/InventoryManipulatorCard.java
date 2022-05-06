package retail.view.main.tab.bot.manipulator.inventory.panel;

import lombok.Getter;
import retail.view.main.tab.bot.manipulator.inventory.panel.add.Add;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryManipulatorCard extends JPanel {
    private final Add add = new Add();
    private final View view = new View();
    private final Product product = new Product();
    private final CardLayout cardLayout = new CardLayout();

    public InventoryManipulatorCard() {
        setLayout(cardLayout);

        add(add,"add");
        add(view,"view");
        add(product,"product");
        cardLayout.show(this,"product");
    }
}





























