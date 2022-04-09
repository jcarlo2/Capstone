package retail.view.main.panel.bot.main.inventory;

import lombok.Getter;
import retail.component.jtable.CustomJTableInventory;

import javax.swing.*;
import java.awt.*;

@Getter
public class Inventory extends JPanel {
    private final CustomJTableInventory tableInventory = new CustomJTableInventory();
    private final JScrollPane scroll = new JScrollPane(tableInventory);

    public Inventory() {
        setLayout(new BorderLayout());

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll,BorderLayout.CENTER);
    }
}
