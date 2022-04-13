package retail.view.main.panel.bot.manipulator.inventory.panel.add;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class Add extends JPanel {
    private final AddCard addCard = new AddCard();
    private final JPanel wrapper = new JPanel();
    private final JButton addInventory = new JButton("Add Inventory");
    private final JButton nullProduct = new JButton("Null Product");

    public Add() {
        setLayout(new BorderLayout());

        wrapper.setLayout(new GridLayout(1,2));
        wrapper.add(addInventory);
        wrapper.add(nullProduct);

        add(addCard,BorderLayout.CENTER);
        add(wrapper,BorderLayout.SOUTH);
    }
}
