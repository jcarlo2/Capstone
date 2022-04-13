package retail.view.main.panel.bot.manipulator.inventory.panel.add;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddCard extends JPanel {
    private final AddInventory add = new AddInventory();
    private final NullProduct nullProduct = new NullProduct();
    private final CardLayout card = new CardLayout();
    public AddCard() {
        setLayout(card);
        add("add",add);
        add("null",nullProduct);
        card.show(this,"add");
    }
}
