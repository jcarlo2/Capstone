package retail.view.main.tab.bot.inventory.manipulator.add;

import lombok.Getter;
import retail.shared.custom.jcombobox.JComboBoxProduct;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static retail.shared.constant.Constant.COLOR_BLACK;

@Getter
public class NullInventoryManipulator extends JPanel {
    private final JPanel wrapper1 = new JPanel(new GridLayout(1,3));
    private final JComboBoxProduct productId = new JComboBoxProduct("ID");
    private final CustomJTextField price = new CustomJTextField("Price",COLOR_BLACK,false);
    private final CustomJTextField quantity = new CustomJTextField( "Quantity Per Pieces",COLOR_BLACK);
    private final CustomJTextField box = new CustomJTextField( "Quantity Per Box",COLOR_BLACK);
    private final CustomJTextField pieces = new CustomJTextField( "Pieces Per Box",COLOR_BLACK,false);
    private final CustomJTextField totalAmount = new CustomJTextField("Total Amount",COLOR_BLACK,false);
    private final CustomJTextField reportId = new CustomJTextField( "Report ID",COLOR_BLACK,false);
    private final JButton save = new JButton("Save");
    private final JButton add = new JButton("Add");
    private final JButton delete = new JButton("Delete");
    private final JButton clear = new JButton("Clear");

    public NullInventoryManipulator() {
        setLayout(new GridLayout(12,1));
        setWrapper1();
        setComponent();
    }

    private void setWrapper1() {
        wrapper1.add(add);
        wrapper1.add(delete);
        wrapper1.add(clear);
    }

    private void setComponent() {
        add(productId);
        add(price);
        add(quantity);
        add(box);
        add(pieces);
        add(totalAmount);
        add(wrapper1);
        add(reportId);
        add(save);
    }

    public void clear() {
        quantity.setText("0");
        box.setText("0");
        totalAmount.setText("0");
    }

    public String[] getData() {
        String[] data = new String[6];
        data[0] = Objects.requireNonNull(productId.getSelectedItem()).toString();
        data[1] = price.getText();
        data[2] = quantity.getText();
        data[3] = box.getText();
        data[4] = pieces.getText();
        data[5] = totalAmount.getText();
        return data;
    }
}
