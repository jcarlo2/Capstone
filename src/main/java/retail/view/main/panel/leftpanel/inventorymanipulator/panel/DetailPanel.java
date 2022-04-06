package retail.view.main.panel.leftpanel.inventorymanipulator.panel;

import lombok.Getter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

@Getter
public class DetailPanel extends JPanel {
    private final JPanel center = new JPanel();
    private final JTextField id = new JTextField(15);
    private final JTextField description = new JTextField(15);
    private final JTextField price = new JTextField(15);
    private final JTextField quantityByPiece = new JTextField(15);
    private final JTextField quantityByBox = new JTextField(15);
    private final JTextField piecesPerBox = new JTextField(15);
    private final JTextArea fullDescription = new JTextArea(3,15);
    private final JScrollPane scroll = new JScrollPane(fullDescription);

    public DetailPanel() {
        setLayout(new BorderLayout());
        center.setLayout(new GridLayout(12,1));

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
