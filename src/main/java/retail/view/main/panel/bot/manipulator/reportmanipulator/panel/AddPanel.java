package retail.view.main.panel.bot.manipulator.reportmanipulator.panel;

import lombok.Getter;
import retail.component.jcombobox.CustomJComboBoxReport;
import retail.component.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddPanel extends JPanel {
    private final JPanel addClearPanel = new JPanel();
    private final JPanel saveReset = new JPanel();
    private final JPanel soldAndSoldTotal = new JPanel();
    private final JPanel damExpAndTotal = new JPanel();

    private final CustomJComboBoxReport id = new CustomJComboBoxReport("ID");
    private final CustomJTextField price = new CustomJTextField("Price");
    private final CustomJTextField sold = new CustomJTextField("Sold By Piece");
    private final CustomJTextField soldTotal = new CustomJTextField("Total");
    private final CustomJTextField damage = new CustomJTextField("Null Product");
    private final CustomJTextField damageTotal = new CustomJTextField("Total");
    private final CustomJTextField total = new CustomJTextField("Total Sales");
    private final CustomJTextField reportId = new CustomJTextField("Report ID");
    private final JButton add = new JButton("Add");
    private final JButton clear = new JButton("Clear");
    private final JButton delete = new JButton("Delete");
    private final JButton save = new JButton("Save");
    private final JButton reset = new JButton("Generate ID");

    public AddPanel() {
        setLayout(new GridLayout(12,2));
        price.setEditable(false);
        reportId.setEditable(false);
        total.setEditable(false);

        sold.setText("0");
        soldTotal.setText("0");
        damage.setText("0");
        damageTotal.setText("0");
        total.setText("0");

        saveReset.setLayout(new GridLayout(1,2));
        saveReset.add(save);
        saveReset.add(reset);

        addClearPanel.setLayout(new GridLayout(1,3));
        addClearPanel.add(add);
        addClearPanel.add(delete);
        addClearPanel.add(clear);

        soldAndSoldTotal.setLayout(new GridLayout(1,2));
        soldAndSoldTotal.add(sold);
        soldAndSoldTotal.add(soldTotal);

        damExpAndTotal.setLayout(new GridLayout(1,2));
        damExpAndTotal.add(damage);
        damExpAndTotal.add(damageTotal);

        id.setMaximumSize(new Dimension(300,300));

        add(id);
        add(price);
        add(soldAndSoldTotal);
        add(damExpAndTotal);
        add(total);
        add(addClearPanel);
        add(reportId);
        add(saveReset);

        add(Box.createHorizontalGlue());
        add(Box.createRigidArea(new Dimension(10, 0)));

    }
}
