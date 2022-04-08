package retail.view.main.panel.leftpanel.inventorymanipulator.panel;

import lombok.Getter;
import retail.component.jtextfield.CustomJTextField;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

@Getter
public class DetailPanel extends JPanel {
    private final JPanel center = new JPanel();
    private final CustomJTextField id = new CustomJTextField("ID");
    private final CustomJTextField description = new CustomJTextField("Description");
    private final CustomJTextField price = new CustomJTextField("Price");
    private final CustomJTextField quantityByPiece = new CustomJTextField("Quantity By Piece");
    private final CustomJTextField quantityByBox = new CustomJTextField("Quantity By Box");
    private final CustomJTextField piecesPerBox = new CustomJTextField("Pieces Per Box");
    private final JTextArea fullDescription = new JTextArea(3,15);
    private final JScrollPane scroll = new JScrollPane(fullDescription);

    public DetailPanel() {
        setLayout(new BorderLayout());
        center.setLayout(new GridLayout(11,1));

        setTextField();
        setFullDescription();
        add(center,BorderLayout.CENTER);
        add(scroll,BorderLayout.SOUTH);
    }

    private void setFullDescription() {
        fullDescription.setLineWrap(true);
        fullDescription.setWrapStyleWord(true);
        fullDescription.setEditable(false);
        fullDescription.setFont(new Font("Monospaced", Font.PLAIN,12));
        fullDescription.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Full Description",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,12)));
    }

    private void setTextField() {
        id.setHorizontalAlignment(JLabel.CENTER);
        id.setEditable(false);
        id.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"ID",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        center.add(id);

        description.setHorizontalAlignment(JLabel.CENTER);
        description.setEditable(false);
        description.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Description",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        center.add(description);

        price.setHorizontalAlignment(JLabel.CENTER);
        price.setEditable(false);
        price.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Price",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        center.add(price);

        quantityByPiece.setHorizontalAlignment(JLabel.CENTER);
        quantityByPiece.setEditable(false);
        quantityByPiece.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Quantity By Piece",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        center.add(quantityByPiece);

        piecesPerBox.setHorizontalAlignment(JLabel.CENTER);
        piecesPerBox.setEditable(false);
        piecesPerBox.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Pieces Per Box",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        center.add(piecesPerBox);

        quantityByBox.setHorizontalAlignment(JLabel.CENTER);
        quantityByBox.setEditable(false);
        quantityByBox.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Quantity By Box",
                        TitledBorder.CENTER,JLabel.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        center.add(quantityByBox);
    }
}
