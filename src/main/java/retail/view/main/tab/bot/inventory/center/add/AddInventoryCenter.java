package retail.view.main.tab.bot.inventory.center.add;

import lombok.Getter;
import retail.shared.custom.jtable.AddDeliveryTable;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

@Getter
public class AddInventoryCenter extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(0,9);
    private final AddDeliveryTable table = new AddDeliveryTable(model);
    private final JScrollPane scroll = new JScrollPane(table);

    public AddInventoryCenter() {
        setLayout(new BorderLayout());
        add(scroll,BorderLayout.CENTER);
    }
}
