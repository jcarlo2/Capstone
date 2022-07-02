package retail.view.main.tab.bot.inventory.manipulator.panel.add;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class Add extends JPanel {
    private final AddCard addPanel = new AddCard();
    private final JPanel wrapper = new JPanel();
    private final JButton addProduct = new JButton("Add Inventory");
    private final JButton nullProduct = new JButton("Null Product");

    public Add() {
        setLayout(new BorderLayout());
        addProduct.setEnabled(false);
        wrapper.setLayout(new GridLayout(1,2));
        wrapper.add(addProduct);
        wrapper.add(nullProduct);
        add(addPanel,BorderLayout.CENTER);
        add(wrapper,BorderLayout.SOUTH);
    }
}
