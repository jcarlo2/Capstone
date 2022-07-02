package retail.view.main.tab.bot.inventory.manipulator.panel.product;

import lombok.Getter;
import retail.shared.custom.jtextfield.CustomJTextField;
import retail.shared.custom.textarea.CustomTextArea;

import javax.swing.*;
import java.awt.*;

import static retail.shared.constant.Constant.COLOR_BLACK;

@Getter
public class ProductManipulator extends JPanel {
    private final JPanel wrapper1 = new JPanel(new GridLayout(10,1));
    private final JPanel wrapper2 = new JPanel(new GridLayout(1,2));
    private final JPanel wrapper3 = new JPanel(new GridLayout(1,2));
    private final CustomJTextField id = new CustomJTextField("ID",COLOR_BLACK,15,false);
    private final CustomJTextField newId = new CustomJTextField("Update ID",COLOR_BLACK,15,true);
    private final CustomJTextField description = new CustomJTextField("Description",COLOR_BLACK,15,true);
    private final CustomJTextField price = new CustomJTextField("Price",COLOR_BLACK,15,true);
    private final CustomJTextField piecesPerBox = new CustomJTextField("Pieces Per Box",COLOR_BLACK,15,true);
    private final CustomTextArea fullDescription = new CustomTextArea(5,15,false);
    private final JScrollPane scroll = new JScrollPane(fullDescription);
    private final JButton update = new JButton("Update");
    private final JButton clear = new JButton("Clear");
    private final JButton add = new JButton("Add");
    private final JButton delete = new JButton("Delete");

    public ProductManipulator() {
        setLayout(new BorderLayout());

        wrapper2.add(update);
        wrapper2.add(clear);

        wrapper3.add(add);
        wrapper3.add(delete);

        wrapper1.add(id);
        wrapper1.add(newId);
        wrapper1.add(description);
        wrapper1.add(price);
        wrapper1.add(piecesPerBox);
        wrapper1.add(wrapper2);
        wrapper1.add(Box.createVerticalBox());
        wrapper1.add(Box.createVerticalBox());
        wrapper1.add(Box.createVerticalBox());
        wrapper1.add(wrapper3);

        add(wrapper1,BorderLayout.CENTER);
        add(scroll,BorderLayout.SOUTH);
    }


    public void clear() {
        id.setText("");
        description.setText("");
        fullDescription.setText("");
        price.setText("");
        piecesPerBox.setText("");
    }

    public String[] getData() {
        String[] data = new String[6];
        data[0] = id.getText();
        data[1] = description.getText();
        data[2] = price.getText();
        data[3] = newId.getText();
        data[4] = piecesPerBox.getText();
        return data;
    }

}
