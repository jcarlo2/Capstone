package retail.view.main.tab.bot.inventory.center.add;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddCenterCard extends JPanel {
    private final CardLayout card = new CardLayout();
    private final AddInventoryCenter addInventoryCenter = new AddInventoryCenter();
    private final NullInventoryCenter nullInventoryCenter = new NullInventoryCenter();

    public AddCenterCard() {
        setLayout(card);
        add("add",addInventoryCenter);
        add("null",nullInventoryCenter);
        card.show(this,"add");
    }
}
