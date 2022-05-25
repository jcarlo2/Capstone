package retail.view.main.tab.bot.inventory.center;

import lombok.Getter;
import retail.shared.customcomponent.jtable.JTableInventory;

import javax.swing.*;
import java.awt.*;

@Getter
public class Add extends JPanel {
    private final JTableInventory tableInventory = new JTableInventory(true);
    private final JScrollPane scroll = new JScrollPane(tableInventory);

    public Add() {
        setLayout(new BorderLayout());
        add(scroll,BorderLayout.CENTER);
    }
}
