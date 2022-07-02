package retail.view.main.tab.bot.inventory.center.add;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddInventoryCard extends JPanel {
    private final AddInventoryCenter addCenter = new AddInventoryCenter();
    private final NullInventoryCenter nullCenter = new NullInventoryCenter();
    private final CardLayout card = new CardLayout();

    public AddInventoryCard() {
        setLayout(card);
        add("add",addCenter);
        add("null",nullCenter);
        card.show(this,"add");
    }
}
