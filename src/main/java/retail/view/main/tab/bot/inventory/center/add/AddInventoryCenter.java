package retail.view.main.tab.bot.inventory.center.add;

import lombok.Getter;
import retail.shared.custom.jtable.JTableInventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class AddInventoryCenter extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(0,8);
    private final JTableInventory table = new JTableInventory(model);
    private final JScrollPane scroll = new JScrollPane(table);

    public AddInventoryCenter() {
        setLayout(new BorderLayout());
        add(scroll,BorderLayout.CENTER);
    }
}
