package retail.view.main.tab.bot.inventory.center;

import lombok.Getter;
import retail.view.main.tab.bot.inventory.center.add.AddInventoryCenter;
import retail.view.main.tab.bot.inventory.center.product.ProductCenter;
import retail.view.main.tab.bot.inventory.center.view.InventoryViewCenter;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryMain extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final AddInventoryCenter add = new AddInventoryCenter();
    private final InventoryViewCenter view = new InventoryViewCenter();
    private final ProductCenter product = new ProductCenter();

    public InventoryMain() {
        setLayout(cardLayout);
        add("add", add);
        add("view",view);
        add("product", product);
        cardLayout.show(this,"product");
    }
}
