package retail.view.main.panel.bot.main.inventory;

import lombok.Getter;
import retail.customcomponent.jtable.CustomJTableInventory;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryMainAdd extends JPanel {
    private final CustomJTableInventory tableInventory = new CustomJTableInventory(true);
    private final JScrollPane scroll = new JScrollPane(tableInventory);

    public InventoryMainAdd() {
        setLayout(new BorderLayout());
        add(scroll,BorderLayout.CENTER);
    }
}
