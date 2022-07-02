package retail.view.main.tab.bot.inventory.manipulator.panel.add;

import lombok.Getter;
import retail.shared.custom.jcombobox.JComboBoxProduct;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static retail.shared.constant.Constant.COLOR_BLACK;

@Getter
public class AddInventoryManipulator extends JPanel {
    private final JPanel wrapper1 = new JPanel();
    private final JComboBoxProduct productId = new JComboBoxProduct("ID");
    private final CustomJTextField price = new CustomJTextField("Price",COLOR_BLACK,true);
    private final CustomJTextField quantityPerPiece = new CustomJTextField("Quantity Per Piece",COLOR_BLACK);
    private final CustomJTextField quantityPerBox = new CustomJTextField("Quantity Per Box",COLOR_BLACK);
    private final CustomJTextField piecesPerBox = new CustomJTextField( "Pieces Per Box",COLOR_BLACK);
    private final CustomJTextField reportId = new CustomJTextField( "Report ID",COLOR_BLACK);
    private final JButton save = new JButton("Save");
    private final JButton add = new JButton("Add");
    private final JButton delete = new JButton("Delete");
    private final JButton clear = new JButton("Clear");

    public AddInventoryManipulator() {
        setLayout(new GridLayout(12,2));
        price.setEditable(false);
        piecesPerBox.setEditable(false);
        reportId.setEditable(false);

        add(productId);
        add(price);
        add(quantityPerPiece);
        add(quantityPerBox);
        add(piecesPerBox);

        wrapper1.setLayout(new GridLayout(1,3));
        wrapper1.add(add);
        wrapper1.add(delete);
        wrapper1.add(clear);
        add(wrapper1);
        add(reportId);

        add(save);
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
