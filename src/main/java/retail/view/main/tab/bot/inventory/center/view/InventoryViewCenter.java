package retail.view.main.tab.bot.inventory.center.view;

import lombok.Getter;
import retail.shared.custom.jspinner.JSpinnerDate;
import retail.shared.custom.jtable.JTableInventory;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class InventoryViewCenter extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(0,8);
    private final JTableInventory table = new JTableInventory(model,true);
    private final JTextField total = new CustomJTextField("Total", Color.BLACK,15);
    private final JTextField id = new CustomJTextField("Report Id", Color.BLACK,15);
    private final JSpinnerDate date = new JSpinnerDate();
    public InventoryViewCenter() {
        setLayout(new GridLayout(1,1));
        id.setText("");


        JPanel wrapper2 = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        wrapper2.add(id,constraints);
        wrapper2.add(total,constraints);

        JPanel wrapper1 = new JPanel(new BorderLayout());
        JScrollPane scroll = new JScrollPane(table);
        wrapper1.add(scroll,BorderLayout.CENTER);
        wrapper1.add(wrapper2,BorderLayout.NORTH);

        add(wrapper1);
    }
}
