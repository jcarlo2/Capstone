package retail.view.main.tab.bot.inventory.center.add;

import lombok.Getter;
import retail.shared.custom.jtable.NullProductTable;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class NullInventoryCenter extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(0,7);
    private final NullProductTable table = new NullProductTable(model);
    private final JScrollPane scroll = new JScrollPane(table);

    private final JPanel wrapper1 = new JPanel(new GridBagLayout());
    private final CustomJTextField total = new CustomJTextField("Total", Color.BLACK,15,false);

    public NullInventoryCenter() {
        setLayout(new BorderLayout());
        wrapper1.add(total);
        add(wrapper1,BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);
    }
}
