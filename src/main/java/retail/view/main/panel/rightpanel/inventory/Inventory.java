package retail.view.main.panel.rightpanel.inventory;

import lombok.Getter;
import retail.model.CustomJTable.CustomJTableInventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class Inventory extends JPanel {
    public DefaultTableModel model = new DefaultTableModel(0,7);
    private final CustomJTableInventory customJTableInventory = new CustomJTableInventory(model);
    private final JScrollPane scroll = new JScrollPane(customJTableInventory);

    public Inventory() {
        setLayout(new BorderLayout());

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll,BorderLayout.CENTER);
    }
}
