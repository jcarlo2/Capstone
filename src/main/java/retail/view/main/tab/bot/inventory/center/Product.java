package retail.view.main.tab.bot.inventory.center;

import lombok.Getter;
import retail.shared.custom.jtable.JTableProduct;

import javax.swing.*;
import java.awt.*;

@Getter
public class Product extends JPanel {
    private final JTableProduct table = new JTableProduct();
    private final JScrollPane scroll = new JScrollPane(table);

    public Product() {
        setLayout(new GridLayout(1,1));
        add(scroll);
    }
}
