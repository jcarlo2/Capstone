package retail.view.main.tab.bot.transaction.manipulator.panel.add;

import lombok.Getter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import retail.shared.customcomponent.jcombobox.JComboBoxProduct;
import retail.shared.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

import static retail.shared.constant.Constant.COLOR_BLACK;

@Getter
public class AddTransaction extends JPanel {
    private final JPanel addDeleteClear = new JPanel();
    private final JPanel saveGenerate = new JPanel();
    private final JPanel soldAndSoldTotal = new JPanel();
    private final JPanel discountPercentageAndTotal = new JPanel();

    private final JComboBoxProduct id = new JComboBoxProduct("ID");
    private final CustomJTextField price = new CustomJTextField("Price",COLOR_BLACK,false);
    private final CustomJTextField sold = new CustomJTextField("Sold By Piece",COLOR_BLACK);
    private final CustomJTextField soldTotal = new CustomJTextField("Total",COLOR_BLACK,false);
    private final CustomJTextField reportId = new CustomJTextField("Report ID",COLOR_BLACK,false);
    private final CustomJTextField discountPercentage = new CustomJTextField("Discount %",COLOR_BLACK);
    private final CustomJTextField discountTotal = new CustomJTextField("Total",COLOR_BLACK);
    private final JButton add = new JButton("Add");
    private final JButton clear = new JButton("Clear");
    private final JButton delete = new JButton("Delete");
    private final JButton save = new JButton("Save");
    private final JButton generateId = new JButton("Generate ID");

    public AddTransaction() {
        setLayout(new GridLayout(12,2));

        AutoCompleteDecorator.decorate(id);

        saveGenerate.setLayout(new GridLayout(1,2));
        saveGenerate.add(save);
        saveGenerate.add(generateId);

        addDeleteClear.setLayout(new GridLayout(1,3));
        addDeleteClear.add(add);
        addDeleteClear.add(delete);
        addDeleteClear.add(clear);

        discountPercentageAndTotal.setLayout(new GridLayout(1,2));
        discountPercentageAndTotal.add(discountPercentage);
        discountPercentageAndTotal.add(discountTotal);

        soldAndSoldTotal.setLayout(new GridLayout(1,2));
        soldAndSoldTotal.add(sold);
        soldAndSoldTotal.add(soldTotal);

        id.setMaximumSize(new Dimension(300,300));

        add(id);
        add(price);
        add(soldAndSoldTotal);
        add(discountPercentageAndTotal);
        add(addDeleteClear);
        add(reportId);
        add(saveGenerate);
    }
}
