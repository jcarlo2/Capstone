package retail.shared.customcomponent.jcombobox;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductDatabase;
import retail.model.Product;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class JComboBoxProduct extends JComboBox<String> {
    private final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

    public JComboBoxProduct(String title) {
        setCustomBorder(title);
        centerText();
        setModel(comboBoxModel);
        setProductIdList();
        setPrototypeDisplayValue("");

    }

    private void centerText() {
        ((JTextField) getEditor().getEditorComponent()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void setCustomBorder(String title) {
        setBorder(BorderFactory.createTitledBorder
            (null,title,
                    TitledBorder.CENTER,TitledBorder.CENTER,
                    new Font("SansSerif",Font.BOLD,15)));
    }

    private @NotNull ArrayList<String> getAllProductID() {
        ArrayList<Product> product = new ProductDatabase().showProduct();
        ArrayList<String> list = new ArrayList<>();
            for(Product item : product) {
                list.add(item.getId());
            }
        return list;
    }

    // MUST CALL EVERY PRESSED IN ADD-JBUTTON IN INVENTORY PRODUCT PANEL
    public void setProductIdList() {
        removeAllItems();
        ArrayList<String> list = getAllProductID();
            for(String id : list) {
               comboBoxModel.addElement(id);
            }
    }
}
