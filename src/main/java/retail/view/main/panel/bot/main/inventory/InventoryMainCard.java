package retail.view.main.panel.bot.main.inventory;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryMainCard extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final Add add = new Add();
    private final View delete = new View();
    private final Product product = new Product();

    public InventoryMainCard() {
        setLayout(cardLayout);
        add("add",add);
        add("delete",delete);
        add("product",product);
        cardLayout.show(this,"product");
    }
}
