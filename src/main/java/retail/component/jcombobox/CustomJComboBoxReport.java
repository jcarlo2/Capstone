package retail.component.jcombobox;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductController;
import retail.model.ProductObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class CustomJComboBoxReport extends JComboBox<String> {
    private  JScrollPane scroll;
    private final ProductController productController = new ProductController();
    private final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

    public CustomJComboBoxReport(String title) {
        setCustomBorder(title);
        centerText();
        setProductIdList();
        setModel(comboBoxModel);
        setPrototypeDisplayValue("");
    }

    private void centerText() {
        ((JTextField) getEditor().getEditorComponent()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void setCustomBorder(String title) {
        setBorder(BorderFactory.createTitledBorder
            (new LineBorder(Color.WHITE),title,
                    TitledBorder.CENTER,TitledBorder.CENTER,
                    new Font("SansSerif",Font.BOLD,15)));
    }

    private @NotNull ArrayList<String> getAllProductID() {
        ArrayList<ProductObject> product = productController.show();
        ArrayList<String> list = new ArrayList<>();
            for(ProductObject item : product) {
                list.add(item.getId());
            }
        return list;
    }

    // MUST CALL EVERY PRESSED IN ADD-JBUTTON IN ADD PANEL
    public void setProductIdList() {
        removeAllItems();
        ArrayList<String> list = getAllProductID();
            for(String id : list) {
               comboBoxModel.addElement(id);
            }
    }
}
