package retail.view.main.panel.rightpanel.inventory;

import lombok.Getter;
import retail.model.inventory.InventoryTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class Inventory extends JPanel {
    public DefaultTableModel model = new DefaultTableModel(0,7);
    private final InventoryTable inventoryTable = new InventoryTable(model);
    private final JScrollPane scroll = new JScrollPane(inventoryTable);

    public Inventory() {
        setLayout(new BorderLayout());

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll,BorderLayout.CENTER);
    }
}
