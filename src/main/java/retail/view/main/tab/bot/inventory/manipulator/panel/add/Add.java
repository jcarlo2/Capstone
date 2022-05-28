package retail.view.main.tab.bot.inventory.manipulator.panel.add;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class Add extends JPanel {
    private final AddInventory addInventory = new AddInventory();
    private final JPanel wrapper = new JPanel();
    private final JButton add = new JButton("Add Inventory");
    private final JButton nullProduct = new JButton("Null Product");

    public Add() {
        setLayout(new BorderLayout());
        add.setEnabled(false);
        wrapper.setLayout(new GridLayout(1,2));
        wrapper.add(add);
        wrapper.add(nullProduct);
        add(addInventory,BorderLayout.CENTER);
        add(wrapper,BorderLayout.SOUTH);
    }
}
