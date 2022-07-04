package retail.shared.custom.jpanel;

import lombok.Getter;
import retail.shared.custom.jspinner.JSpinnerDate;
import retail.shared.custom.jtable.JTableTransaction;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class ViewCenter extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(0,10);
    private final JTableTransaction table = new JTableTransaction(model,false,false,false);
    private final JTextField total = new CustomJTextField("Total", Color.BLACK,15);
    private final JTextField id = new CustomJTextField("Report Id", Color.BLACK,15);
    private final JSpinnerDate date = new JSpinnerDate();
    public ViewCenter() {
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
