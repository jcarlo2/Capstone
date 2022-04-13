package retail.view.main.panel.bot.manipulator.transaction.panel.add;

import lombok.Getter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import retail.customcomponent.jcombobox.CustomJComboBox;
import retail.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

import static retail.util.constant.Constant.COLOR_BLACK;

@Getter
public class AddTransaction extends JPanel {
    private final JPanel addClearPanel = new JPanel();
    private final JPanel saveReset = new JPanel();
    private final JPanel soldAndSoldTotal = new JPanel();
    private final JPanel damExpAndTotal = new JPanel();

    private final CustomJComboBox id = new CustomJComboBox("ID");
    private final CustomJTextField price = new CustomJTextField("Price",COLOR_BLACK);
    private final CustomJTextField sold = new CustomJTextField("Sold By Piece",COLOR_BLACK);
    private final CustomJTextField soldTotal = new CustomJTextField("Total",COLOR_BLACK);
    private final CustomJTextField damage = new CustomJTextField("Discount %%",COLOR_BLACK);
    private final CustomJTextField damageTotal = new CustomJTextField("Total",COLOR_BLACK);
    private final CustomJTextField total = new CustomJTextField("Total Sales",COLOR_BLACK);
    private final CustomJTextField reportId = new CustomJTextField("Report ID",COLOR_BLACK);
    private final JButton add = new JButton("Add");
    private final JButton clear = new JButton("Clear");
    private final JButton delete = new JButton("Delete");
    private final JButton save = new JButton("Save");
    private final JButton generateId = new JButton("Generate ID");

    public AddTransaction() {
        setLayout(new GridLayout(12,2));

        price.setEditable(false);
        reportId.setEditable(false);
        total.setEditable(false);

        AutoCompleteDecorator.decorate(id);
        id.setEditable(true);

        saveReset.setLayout(new GridLayout(1,2));
        saveReset.add(save);
        saveReset.add(generateId);

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

    }
}
