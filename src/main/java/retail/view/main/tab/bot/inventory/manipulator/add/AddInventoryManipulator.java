package retail.view.main.tab.bot.inventory.manipulator.add;

import lombok.Getter;
import retail.shared.custom.jcombobox.JComboBoxProduct;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static retail.shared.constant.Constant.COLOR_BLACK;

@Getter
public class AddInventoryManipulator extends JPanel {
    private final JPanel wrapper1 = new JPanel(new GridLayout(1,3));
    private final JPanel wrapper2 = new JPanel(new GridLayout(1,2));
    private final JComboBoxProduct productId = new JComboBoxProduct("ID");
    private final CustomJTextField quantity = new CustomJTextField( "Quantity Per Pieces",COLOR_BLACK);
    private final CustomJTextField box = new CustomJTextField( "Quantity Per Box",COLOR_BLACK);
    private final CustomJTextField pieces = new CustomJTextField( "Pieces Per Box",COLOR_BLACK,false);
    private final CustomJTextField price = new CustomJTextField("Total Price",COLOR_BLACK);
    private final CustomJTextField discountPercentage = new CustomJTextField("Discount %%",COLOR_BLACK);
    private final CustomJTextField discountTotal = new CustomJTextField("Discount Total",COLOR_BLACK);
    private final CustomJTextField totalAmount = new CustomJTextField("Total Amount",COLOR_BLACK,false);
    private final CustomJTextField reportId = new CustomJTextField( "Report ID",COLOR_BLACK,false);
    private final JButton save = new JButton("Save");
    private final JButton add = new JButton("Add");
    private final JButton delete = new JButton("Delete");
    private final JButton clear = new JButton("Clear");

    public AddInventoryManipulator() {
        setLayout(new GridLayout(12,1));
        setWrapper1();
        setWrapper2();

        setComponent();
    }

    private void setWrapper1() {
        wrapper1.add(add);
        wrapper1.add(delete);
        wrapper1.add(clear);
    }

    private void setWrapper2() {
        wrapper2.add(discountPercentage);
        wrapper2.add(discountTotal);
    }

    private void setComponent() {
        add(productId);
        add(quantity);
        add(box);
        add(pieces);
        add(price);
        add(wrapper2);
        add(totalAmount);
        add(wrapper1);
        add(reportId);
        add(save);
    }
    
    public void clear() {
        quantity.setText("0");
        box.setText("0");
        price.setText("0");
        discountPercentage.setText("0");
        totalAmount.setText("0");
    }

    public String[] getData() {
        String[] data = new String[8];
        data[0] = Objects.requireNonNull(productId.getSelectedItem()).toString();
        data[1] = quantity.getText();
        data[2] = box.getText();
        data[3] = pieces.getText();
        data[4] = price.getText();
        data[5] = discountPercentage.getText();
        data[6] = discountTotal.getText();
        data[7] = totalAmount.getText();
        return data;
    }
}
