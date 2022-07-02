package retail.view.main.tab.bot.inventory.manipulator.panel.add;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddCard extends JPanel {
    private final AddInventoryManipulator addManipulator = new AddInventoryManipulator();
    private final NullInventoryManipulator nullManipulator = new NullInventoryManipulator();
    private final CardLayout card = new CardLayout();

    public AddCard() {
        setLayout(card);
        add("add", addManipulator);
        add("null", nullManipulator);
        card.show(this,"add");
    }
}
