package retail.view.main.tab.bot.inventory.center;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryMainCard extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final AddInventory add = new AddInventory();
    private final View view = new View();
    private final Product product = new Product();

    public InventoryMainCard() {
        setLayout(cardLayout);
        add("add", add);
        add("view",view);
        add("product",product);
        cardLayout.show(this,"product");
    }
}