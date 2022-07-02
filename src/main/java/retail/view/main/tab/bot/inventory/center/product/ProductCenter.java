package retail.view.main.tab.bot.inventory.center.product;

import lombok.Getter;
import retail.shared.custom.jdialog.ProductJDialog;
import retail.shared.custom.jtable.JTableProduct;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class ProductCenter extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(0,7);
    private final JTableProduct table = new JTableProduct(model);
    private final JScrollPane scroll = new JScrollPane(table);
    private final JPanel wrapper = new JPanel();
    private final CustomJTextField search = new CustomJTextField("Search By Id", Color.BLACK,15, true);
    private final ProductJDialog productDialog = new ProductJDialog();

    public ProductCenter() {
        setLayout(new BorderLayout());
        search.setText("");

        wrapper.setLayout(new GridBagLayout());
        wrapper.add(search);

        add(wrapper, BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);
    }
}
