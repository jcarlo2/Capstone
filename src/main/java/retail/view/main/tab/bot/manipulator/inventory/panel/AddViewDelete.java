package retail.view.main.tab.bot.manipulator.inventory.panel;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddViewDelete extends JPanel {
    private final JButton add = new JButton("Add");
    private final JButton view = new JButton("View");
    private final JButton product = new JButton("Product");

    public AddViewDelete() {
        setLayout(new GridLayout(1,3));
        add(add);
        add(view);
        add(product);
    }
}



















































