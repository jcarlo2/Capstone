package retail.view.main.tab.bot.inventory.center.add;

import lombok.Getter;
import retail.shared.custom.jtable.AddDeliveryTable;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class AddInventoryCenter extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(0,8);
    private final AddDeliveryTable table = new AddDeliveryTable(model);
    private final JScrollPane scroll = new JScrollPane(table);

    private final JPanel wrapper1 = new JPanel(new GridBagLayout());
    private final CustomJTextField total = new CustomJTextField("Total", Color.BLACK,15,false);

    public AddInventoryCenter() {
        setLayout(new BorderLayout());
        wrapper1.add(total);
        add(wrapper1,BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);
    }
}
