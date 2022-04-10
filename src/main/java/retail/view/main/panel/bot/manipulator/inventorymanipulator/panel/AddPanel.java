package retail.view.main.panel.bot.manipulator.inventorymanipulator.panel;

import lombok.Getter;
import retail.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

import static retail.constant.Constant.COLOR_WHITE;

@Getter
public class AddPanel extends JPanel {
    private final JPanel addClearPanel = new JPanel();
    private final CustomJTextField id = new CustomJTextField("ID",COLOR_WHITE);
    private final CustomJTextField description = new CustomJTextField("Description",COLOR_WHITE);
    private final CustomJTextField price = new CustomJTextField("Price",COLOR_WHITE);
    private final CustomJTextField quantityByPiece = new CustomJTextField("Quantity By Piece",COLOR_WHITE);
    private final CustomJTextField quantityByBox = new CustomJTextField("Quantity By Box",COLOR_WHITE);
    private final CustomJTextField piecesPerBox = new CustomJTextField( "Pieces Per Box",COLOR_WHITE);
    private final JButton add = new JButton("Add");
    private final JButton clear = new JButton("Clear");

    public AddPanel() {
        setLayout(new GridLayout(12,1));

        setTextField();
        addClearPanel.setLayout(new GridLayout(1,2));
        addClearPanel.add(add);
        addClearPanel.add(clear);
        add(addClearPanel);
    }

    private void setTextField() {
        add(id);
        add(description);
        add(price);
        add(quantityByPiece);
        add(piecesPerBox);
        add(quantityByBox);}
}
