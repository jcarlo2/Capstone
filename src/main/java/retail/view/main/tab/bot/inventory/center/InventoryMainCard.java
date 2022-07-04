package retail.view.main.tab.bot.inventory.center;

import lombok.Getter;
import retail.shared.custom.jpanel.ViewCenter;
import retail.view.main.tab.bot.inventory.center.add.AddInventoryCard;
import retail.view.main.tab.bot.inventory.center.product.ProductCenter;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryMainCard extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final AddInventoryCard add = new AddInventoryCard();
    private final ViewCenter view = new ViewCenter();
    private final ProductCenter product = new ProductCenter();

    public InventoryMainCard() {
        setLayout(cardLayout);
        add("add", add);
        add("view",view);
        add("product", product);
        cardLayout.show(this,"product");
    }
}
