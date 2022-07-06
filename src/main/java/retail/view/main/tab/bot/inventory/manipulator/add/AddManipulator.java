package retail.view.main.tab.bot.inventory.manipulator.add;

import lombok.Getter;
import retail.shared.custom.jcombobox.JComboBoxProduct;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static retail.shared.constant.Constant.COLOR_BLACK;

@Getter
public class AddManipulator extends JPanel {
    private final JPanel wrapper1 = new JPanel();
    private final JButton addProduct = new JButton("Add Inventory");
    private final JButton nullProduct = new JButton("Null Product");

    private final JPanel wrapper2 = new JPanel(new GridLayout(1,3));
    private final JPanel wrapper3 = new JPanel(new GridLayout(12,1));
    private final JComboBoxProduct productId = new JComboBoxProduct("ID");
    private final CustomJTextField price = new CustomJTextField("Delivery Price",COLOR_BLACK,true);
    private final CustomJTextField quantityPerPiece = new CustomJTextField("Quantity Per Piece",COLOR_BLACK);
    private final CustomJTextField quantityPerBox = new CustomJTextField("Quantity Per Box",COLOR_BLACK);
    private final CustomJTextField piecesPerBox = new CustomJTextField( "Pieces Per Box",COLOR_BLACK,false);
    private final CustomJTextField reportId = new CustomJTextField( "Report ID",COLOR_BLACK,false);
    private final JButton save = new JButton("Save");
    private final JButton add = new JButton("Add");
    private final JButton delete = new JButton("Delete");
    private final JButton clear = new JButton("Clear");

    public AddManipulator() {
        setLayout(new BorderLayout());
        setWrapper1();
        setWrapper2();
        setWrapper3();

        add(wrapper3,BorderLayout.CENTER);
        add(wrapper1,BorderLayout.SOUTH);
    }

    private void setWrapper1() {
        addProduct.setEnabled(false);
        wrapper1.setLayout(new GridLayout(1,2));
        wrapper1.add(addProduct);
        wrapper1.add(nullProduct);
    }

    private void setWrapper2() {
        wrapper2.add(add);
        wrapper2.add(delete);
        wrapper2.add(clear);
    }

    private void setWrapper3() {
        wrapper3.add(productId);
        wrapper3.add(price);
        wrapper3.add(quantityPerPiece);
        wrapper3.add(quantityPerBox);
        wrapper3.add(piecesPerBox);
        wrapper3.add(wrapper2);
        wrapper3.add(reportId);
        wrapper3.add(save);
    }
    
    public void clear() {
        quantityPerPiece.setText("0");
        quantityPerBox.setText("0");
    }

    public String[] getData() {
        String[] data = new String[5];
        data[0] = Objects.requireNonNull(productId.getSelectedItem()).toString();
        data[1] = price.getText();
        data[2] = quantityPerPiece.getText();
        data[3] = quantityPerBox.getText();
        data[4] = piecesPerBox.getText();
        return data;
    }
}
