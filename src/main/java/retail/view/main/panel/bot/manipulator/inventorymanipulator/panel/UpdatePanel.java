package retail.view.main.panel.bot.manipulator.inventorymanipulator.panel;

import lombok.Getter;
import lombok.Setter;
import retail.component.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class UpdatePanel extends JPanel {
    private final JPanel addClearPanel = new JPanel();
    private final JTextField updateValueMessage = new JTextField(15);
    private final CustomJTextField id = new CustomJTextField("ID");
    private final CustomJTextField idToUpdate = new CustomJTextField("ID To Update");
    private final CustomJTextField description = new CustomJTextField("Description");
    private final CustomJTextField price = new CustomJTextField("Price");
    private final CustomJTextField quantityByPiece = new CustomJTextField("Quantity By Piece");
    private final CustomJTextField quantityByBox = new CustomJTextField("Quantity By Box");
    private final CustomJTextField piecesPerBox = new CustomJTextField("Pieces Per Box");
    private final JButton update = new JButton("Update");
    private final JButton clear = new JButton("Clear");

    public UpdatePanel() {
        setLayout(new GridLayout(12,1));
        addClearPanel.setLayout(new GridLayout(1,2));

        setTextField();

        addClearPanel.add(update);
        addClearPanel.add(clear);
        add(addClearPanel);
    }

    private void setTextField() {
        add(idToUpdate);

        updateValueMessage.setText("Update Value");
        updateValueMessage.setEnabled(false);
        updateValueMessage.setHorizontalAlignment(JLabel.CENTER);
        add(updateValueMessage);
        add(id);
        add(description);
        add(price);
        add(quantityByPiece);
        add(piecesPerBox);
        add(quantityByBox);
    }
}


