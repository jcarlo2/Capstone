package retail.view.main.panel.bot.main.inventory;

import lombok.Getter;
import retail.customcomponent.jtable.CustomJTableInventory;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryMainView extends JPanel {
    private final CustomJTableInventory tableInventory = new CustomJTableInventory(false);
    private final JScrollPane scroll = new JScrollPane(tableInventory);

    public InventoryMainView() {
        setLayout(new BorderLayout());
        tableInventory.getColumnModel().removeColumn(tableInventory.getColumnModel().getColumn(6));
        tableInventory.getColumnModel().removeColumn(tableInventory.getColumnModel().getColumn(6));
        add(scroll,BorderLayout.CENTER);
    }
}
