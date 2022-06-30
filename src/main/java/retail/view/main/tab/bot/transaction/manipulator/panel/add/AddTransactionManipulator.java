package retail.view.main.tab.bot.transaction.manipulator.panel.add;

import lombok.Getter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import retail.shared.custom.jcombobox.JComboBoxProduct;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static retail.shared.constant.Constant.COLOR_BLACK;

@Getter
public class AddTransactionManipulator extends JPanel {
    private final JPanel addDeleteClear = new JPanel();
    private final JPanel saveGenerate = new JPanel();
    private final JPanel soldAndSoldTotal = new JPanel();
    private final JPanel discountPercentageAndTotal = new JPanel();

    private final JComboBoxProduct product = new JComboBoxProduct("ID");
    private final JTextField price = new CustomJTextField("Price",COLOR_BLACK,false);
    private final JTextField sold = new CustomJTextField("Sold By Piece",COLOR_BLACK,true);
    private final JTextField soldTotal = new CustomJTextField("Total",COLOR_BLACK,false);
    private final JTextField reportId = new CustomJTextField("Report ID",COLOR_BLACK,false);
    private final JTextField discountPercent = new CustomJTextField("Discount %",COLOR_BLACK,true);
    private final JTextField discountAmount = new CustomJTextField("Total",COLOR_BLACK,true);
    private final JButton add = new JButton("Add");
    private final JButton clear = new JButton("Clear");
    private final JButton delete = new JButton("Delete");
    private final JButton save = new JButton("Save");
    private final JButton generateId = new JButton("Generate ID");

    public AddTransactionManipulator() {
        setLayout(new GridLayout(12,2));

        AutoCompleteDecorator.decorate(product);

        saveGenerate.setLayout(new GridLayout(1,2));
        saveGenerate.add(save);
        saveGenerate.add(generateId);

        addDeleteClear.setLayout(new GridLayout(1,3));
        addDeleteClear.add(add);
        addDeleteClear.add(delete);
        addDeleteClear.add(clear);

        discountPercentageAndTotal.setLayout(new GridLayout(1,2));
        discountPercentageAndTotal.add(discountPercent);
        discountPercentageAndTotal.add(discountAmount);

        soldAndSoldTotal.setLayout(new GridLayout(1,2));
        soldAndSoldTotal.add(sold);
        soldAndSoldTotal.add(soldTotal);

        product.setMaximumSize(new Dimension(300,300));

        add(product);
        add(price);
        add(soldAndSoldTotal);
        add(discountPercentageAndTotal);
        add(addDeleteClear);
        add(reportId);
        add(saveGenerate);
    }

    public void clear() {
        sold.setText("0");
        soldTotal.setText("0");
        discountPercent.setText("0");
        discountAmount.setText("0");
    }

    public String[] getAllData() {
        final String[] data = new String[7];
        data[0] = Objects.requireNonNull(product.getSelectedItem()).toString();
        data[1] = price.getText();
        data[2] = sold.getText();
        data[3] = soldTotal.getText();
        data[4] = discountPercent.getText();
        data[5] = discountAmount.getText();
        return data;
    }

}
