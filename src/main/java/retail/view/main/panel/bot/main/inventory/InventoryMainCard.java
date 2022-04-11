package retail.view.main.panel.bot.main.inventory;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryMainCard extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final InventoryMainAdd add = new InventoryMainAdd();
    private final InventoryMainView delete = new InventoryMainView();
    private final InventoryMainProduct product = new InventoryMainProduct();

    public InventoryMainCard() {
        setLayout(cardLayout);
        add("add",add);
        add("delete",delete);
        add("product",product);
        cardLayout.show(this,"product");
    }
}
