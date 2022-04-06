package retail.view.main.panel.leftpanel.inventorymanipulator.panel;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

@Getter
@Setter
public class UpdatePanel extends JPanel {
    private final JPanel addClearPanel = new JPanel();
    private final JTextField id = new JTextField(15);
    private final JTextField idToUpdate = new JTextField(15);
    private final JTextField updateValueMessage = new JTextField(15);
    private final JTextField description = new JTextField(15);
    private final JTextField price = new JTextField(15);
    private final JTextField quantityByPiece = new JTextField(15);
    private final JTextField quantityByBox = new JTextField(15);
    private final JTextField piecesPerBox = new JTextField(15);
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
        idToUpdate.setHorizontalAlignment(JLabel.CENTER);
        idToUpdate.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"ID To Update",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(idToUpdate);

        updateValueMessage.setText("Update Value");
        updateValueMessage.setEnabled(false);
        updateValueMessage.setHorizontalAlignment(JLabel.CENTER);
        add(updateValueMessage);

        id.setHorizontalAlignment(JLabel.CENTER);
        id.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"ID",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(id);

        description.setHorizontalAlignment(JLabel.CENTER);
        description.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Description",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(description);

        price.setHorizontalAlignment(JLabel.CENTER);
        price.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Price",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(price);

        quantityByPiece.setHorizontalAlignment(JLabel.CENTER);
        quantityByPiece.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Quantity By Piece",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(quantityByPiece);

        piecesPerBox.setHorizontalAlignment(JLabel.CENTER);
        piecesPerBox.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Pieces Per Box",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(piecesPerBox);

        quantityByBox.setHorizontalAlignment(JLabel.CENTER);
        quantityByBox.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Quantity By Box",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(quantityByBox);
    }
}


