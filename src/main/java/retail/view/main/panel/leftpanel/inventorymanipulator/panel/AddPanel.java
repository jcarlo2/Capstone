package retail.view.main.panel.leftpanel.inventorymanipulator.panel;

import lombok.Getter;
import retail.component.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddPanel extends JPanel {
    private final JPanel addClearPanel = new JPanel();
    private final CustomJTextField id = new CustomJTextField("ID");
    private final CustomJTextField description = new CustomJTextField("Description");
    private final CustomJTextField price = new CustomJTextField("Price");
    private final CustomJTextField quantityByPiece = new CustomJTextField("Quantity By Piece");
    private final CustomJTextField quantityByBox = new CustomJTextField("Quantity By Box");
    private final CustomJTextField piecesPerBox = new CustomJTextField( "Pieces Per Box");
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
