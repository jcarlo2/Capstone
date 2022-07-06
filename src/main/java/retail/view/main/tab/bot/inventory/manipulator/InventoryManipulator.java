package retail.view.main.tab.bot.inventory.manipulator;

import lombok.Getter;
import retail.shared.custom.jpanel.ViewManipulator;
import retail.view.main.tab.bot.inventory.manipulator.add.AddManipulator;
import retail.view.main.tab.bot.inventory.manipulator.product.ProductManipulator;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryManipulator extends JPanel {
    private final JPanel wrapper1 = new JPanel();
    private final JButton add = new JButton("Add");
    private final JButton view = new JButton("View");
    private final JButton product = new JButton("Product");

    private final JPanel wrapper2 = new JPanel();
    private final AddManipulator addManipulator = new AddManipulator();
    private final ViewManipulator viewManipulator = new ViewManipulator("Delivery","Null","ReportType");
    private final ProductManipulator productManipulator = new ProductManipulator();
    private final CardLayout cardLayout = new CardLayout();

    public InventoryManipulator() {
        setLayout(new BorderLayout());
        setWrapper1();
        setWrapper2();

        add(wrapper1,BorderLayout.NORTH);
        add(wrapper2,BorderLayout.CENTER);
    }

    private void setWrapper1() {
        wrapper1.setLayout(new GridLayout(1,3));
        product.setEnabled(false);
        wrapper1.add(add);
        wrapper1.add(view);
        wrapper1.add(product);
    }

    private void setWrapper2() {
        wrapper2.setLayout(cardLayout);
        wrapper2.add(addManipulator,"add");
        wrapper2.add(viewManipulator,"view");
        wrapper2.add(productManipulator,"product");
        cardLayout.show(wrapper2,"product");
    }

}
