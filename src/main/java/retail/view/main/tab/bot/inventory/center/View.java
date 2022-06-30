package retail.view.main.tab.bot.inventory.center;

import lombok.Getter;
import retail.shared.custom.jtable.JTableInventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class View extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(0,8);
    private final JTableInventory tableInventory = new JTableInventory(model);
    private final JScrollPane scroll = new JScrollPane(tableInventory);

    public View() {
        setLayout(new BorderLayout());
        tableInventory.getColumnModel().removeColumn(tableInventory.getColumnModel().getColumn(6));
        tableInventory.getColumnModel().removeColumn(tableInventory.getColumnModel().getColumn(6));
        add(scroll,BorderLayout.CENTER);
    }
}
