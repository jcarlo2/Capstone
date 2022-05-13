package retail.view.main.tab.bot.center.inventory;

import lombok.Getter;
import retail.customcomponent.jtable.inventory.CustomJTableInventory;

import javax.swing.*;
import java.awt.*;

@Getter
public class Add extends JPanel {
    private final CustomJTableInventory tableInventory = new CustomJTableInventory(true);
    private final JScrollPane scroll = new JScrollPane(tableInventory);

    public Add() {
        setLayout(new BorderLayout());
        add(scroll,BorderLayout.CENTER);
    }
}
