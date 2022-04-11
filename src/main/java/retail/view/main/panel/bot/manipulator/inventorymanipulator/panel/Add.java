package retail.view.main.panel.bot.manipulator.inventorymanipulator.panel;

import lombok.Getter;
import retail.customcomponent.jcombobox.CustomJComboBox;
import retail.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

import static retail.util.constant.Constant.COLOR_BLACK;

@Getter
public class Add extends JPanel {
    private final JPanel wrapper = new JPanel();
    private final JPanel wrapper1 = new JPanel();
    private final CustomJComboBox id = new CustomJComboBox("ID");
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

    public Add() {
        setLayout(new GridLayout(12,1));
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