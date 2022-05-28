package retail.view.main.tab.bot.inventory.center;

import lombok.Getter;
import retail.shared.customcomponent.jtable.JTableInventory;

import javax.swing.*;
import java.awt.*;

@Getter
public class View extends JPanel {
    private final JTableInventory tableInventory = new JTableInventory();
    private final JScrollPane scroll = new JScrollPane(tableInventory);

    public View() {
        setLayout(new BorderLayout());
        tableInventory.getColumnModel().removeColumn(tableInventory.getColumnModel().getColumn(6));
        tableInventory.getColumnModel().removeColumn(tableInventory.getColumnModel().getColumn(6));
        add(scroll,BorderLayout.CENTER);
    }
}
