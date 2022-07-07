package retail.view.main.tab.bot.inventory.manipulator.add;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddManipulatorCard extends JPanel {
    private final CardLayout card = new CardLayout();
    private final AddInventoryManipulator addInventory = new AddInventoryManipulator();
    private final NullInventoryManipulator nullInventory = new NullInventoryManipulator();

    private final JPanel wrapper1 = new JPanel(card);

    private final JPanel wrapper2 = new JPanel(new GridLayout(1,2));
    private final JButton addProduct = new JButton("Add Inventory");
    private final JButton nullProduct = new JButton("Null Product");

    public AddManipulatorCard() {
        setLayout(new BorderLayout());
        setWrapper1();
        setWrapper2();
        add(wrapper1,BorderLayout.CENTER);
        add(wrapper2,BorderLayout.SOUTH);
    }

    private void setWrapper1() {
        wrapper1.add("add", addInventory);
        wrapper1.add("null", nullInventory);
        card.show(wrapper1, "add");
    }

    private void setWrapper2() {
        addProduct.setEnabled(false);
        wrapper2.add(addProduct);
        wrapper2.add(nullProduct);
    }
}
