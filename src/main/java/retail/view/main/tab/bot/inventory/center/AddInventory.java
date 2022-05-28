package retail.view.main.tab.bot.inventory.center;

import lombok.Getter;
import retail.shared.customcomponent.jtable.JTableInventory;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddInventory extends JPanel {
    private final JTableInventory table = new JTableInventory();
    private final JScrollPane scroll = new JScrollPane(table);

    public AddInventory() {
        setLayout(new BorderLayout());
        add(scroll,BorderLayout.CENTER);
    }
}
