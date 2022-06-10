package retail.view.main.tab.bot.inventory.manipulator.panel;

import lombok.Getter;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static retail.shared.constant.Constant.COLOR_BLACK;

@Getter
public class Product extends JPanel {
    private final JPanel center = new JPanel();
    private final JPanel wrapper = new JPanel();
    private final CustomJTextField id = new CustomJTextField("ID",COLOR_BLACK);
    private final CustomJTextField description = new CustomJTextField("Description",COLOR_BLACK);
    private final CustomJTextField price = new CustomJTextField("Price",COLOR_BLACK);
    private final CustomJTextField piecesPerBox = new CustomJTextField("Pieces Per Box",COLOR_BLACK);
    private final JTextArea fullDescription = new JTextArea(3,15);
    private final JScrollPane scroll = new JScrollPane(fullDescription);
    private final JButton add = new JButton("Add");
    private final JButton delete = new JButton("Delete");
    private final JButton clear = new JButton("Clear");

    public Product() {
        setLayout(new BorderLayout());
        center.setLayout(new GridLayout(10,1));
        id.setText("");
        description.setText("");
        price.setText("");
        piecesPerBox.setText("");
        fullDescription.setText("");

        center.add(id);
        center.add(description);
        center.add(price);
        center.add(piecesPerBox);

        wrapper.setLayout(new GridLayout(1,3));
        wrapper.add(add);
        wrapper.add(delete);
        wrapper.add(clear);

        center.add(wrapper);

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
}
