package retail.view.main.tab.bot.center.inventory;

import lombok.Getter;
import retail.customcomponent.jtable.inventory.CustomJTableInventory;

import javax.swing.*;
import java.awt.*;

@Getter
public class View extends JPanel {
    private final CustomJTableInventory tableInventory = new CustomJTableInventory(false);
    private final JScrollPane scroll = new JScrollPane(tableInventory);

    public View() {
        setLayout(new BorderLayout());
        tableInventory.getColumnModel().removeColumn(tableInventory.getColumnModel().getColumn(6));
        tableInventory.getColumnModel().removeColumn(tableInventory.getColumnModel().getColumn(6));
        add(scroll,BorderLayout.CENTER);
    }
}
