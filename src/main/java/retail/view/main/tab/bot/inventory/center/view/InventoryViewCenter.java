package retail.view.main.tab.bot.inventory.center.view;

import lombok.Getter;
import retail.shared.custom.jtable.AddDeliveryTable;
import retail.shared.custom.jtable.NullProductTable;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class InventoryViewCenter extends JPanel {
    private final DefaultTableModel model1 = new DefaultTableModel(0,8);
    private final AddDeliveryTable table1 = new AddDeliveryTable(model1);
    private final JScrollPane scroll1 = new JScrollPane(table1);

    private final DefaultTableModel model2 = new DefaultTableModel(0,7);
    private final NullProductTable table2 = new NullProductTable(model2);
    private final JScrollPane scroll2 = new JScrollPane(table2);

    private final JTextField total = new CustomJTextField("Total", Color.BLACK,15);
    private final JTextField id = new CustomJTextField("Report Id", Color.BLACK,15);

    private final CardLayout card = new CardLayout();
    private final JPanel wrapper2 = new JPanel(new GridBagLayout());
    private final JPanel wrapper3 = new JPanel(card);

    public InventoryViewCenter() {
        setLayout(new BorderLayout());

        setWrapper3();
        setWrapper2();

        add(wrapper3,BorderLayout.CENTER);
        add(wrapper2,BorderLayout.NORTH);
    }

    private void setWrapper3() {
        wrapper3.add("delivery",scroll1);
        wrapper3.add("null",scroll2);
        card.show(wrapper3,"null");
    }

    private void setWrapper2() {
        id.setText("");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        wrapper2.add(id,constraints);
        wrapper2.add(total,constraints);
    }
}
