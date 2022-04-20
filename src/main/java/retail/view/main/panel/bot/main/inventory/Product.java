package retail.view.main.panel.bot.main.inventory;

import lombok.Getter;
import retail.customcomponent.jtable.CustomJTableProduct;

import javax.swing.*;
import java.awt.*;

@Getter
public class Product extends JPanel {
    private final CustomJTableProduct table = new CustomJTableProduct();
    private final JScrollPane scroll = new JScrollPane(table);

    public Product() {
        setLayout(new GridLayout(1,1));
        add(scroll);
    }
}