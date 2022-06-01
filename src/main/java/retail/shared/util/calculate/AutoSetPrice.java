package retail.shared.util.calculate;

import org.jetbrains.annotations.NotNull;
import retail.dao.ProductDAO;
import retail.shared.customcomponent.jcombobox.JComboBoxProduct;
import retail.shared.customcomponent.jtextfield.CustomJTextField;

import java.util.Objects;

public interface AutoSetPrice {
    default void autoSetPrice(ProductDAO productDAO, CustomJTextField priceField, @NotNull JComboBoxProduct id) {
        String productID = (String) id.getSelectedItem();
        if(Objects.isNull(productID)) return; // PREVENT NULL POINTER EXCEPTION WHEN REMOVING ALL ELEMENTS
        String price = String.valueOf(productDAO.get(productID).getPrice());
        priceField.setText(price);
    }
}
