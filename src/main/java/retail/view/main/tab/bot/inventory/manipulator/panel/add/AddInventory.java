package retail.view.main.tab.bot.inventory.manipulator.panel.add;

import lombok.Getter;
import retail.shared.customcomponent.jcombobox.JComboBoxProduct;
import retail.shared.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

import static retail.shared.constant.Constant.COLOR_BLACK;

@Getter
public class AddInventory extends JPanel {
    private final JPanel wrapper = new JPanel();
    private final JPanel wrapper1 = new JPanel();
    private final JComboBoxProduct id = new JComboBoxProduct("ID");
    private final CustomJTextField price = new CustomJTextField("Price",COLOR_BLACK);
    private final CustomJTextField quantityByPiece = new CustomJTextField("Quantity By Piece",COLOR_BLACK);
    private final CustomJTextField quantityByBox = new CustomJTextField("Quantity By Box",COLOR_BLACK);
    private final CustomJTextField piecesPerBox = new CustomJTextField( "Pieces Per Box",COLOR_BLACK);
    private final CustomJTextField reportId = new CustomJTextField( "Report ID",COLOR_BLACK);
    private final JButton save = new JButton("Save");
    private final JButton generateId = new JButton("Generate ID");
    private final JButton add = new JButton("Add");
    private final JButton delete = new JButton("Delete");
    private final JButton clear = new JButton("Clear");

    public AddInventory() {
        setLayout(new GridLayout(12,2));
        price.setEditable(false);
        piecesPerBox.setEditable(false);
        reportId.setEditable(false);

        add(id);
        add(price);
        add(quantityByPiece);
        add(quantityByBox);
        add(piecesPerBox);

        wrapper.setLayout(new GridLayout(1,3));
        wrapper.add(add);
        wrapper.add(delete);
        wrapper.add(clear);
        add(wrapper);
        add(reportId);

        wrapper1.setLayout(new GridLayout(1,2));
        wrapper1.add(save);
        wrapper1.add(generateId);
        add(wrapper1);
    }
}
