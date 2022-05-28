package retail.shared.util.update;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductDatabase;
import retail.shared.pojo.Product;
import retail.shared.customcomponent.jcombobox.JComboBoxProduct;

import java.util.ArrayList;

public interface JComboBoxUpdate {

    default @NotNull ArrayList<String> getAllProductID(@NotNull ProductDatabase productDatabase) {
        ArrayList<Product> product = productDatabase.showProduct();
        ArrayList<String> list = new ArrayList<>();
        for(Product item : product) {
            list.add(item.getId());
        }
        return list;
    }

    default void setProductIdList(@NotNull JComboBoxProduct id, ProductDatabase productDatabase) {
        id.removeAllItems();
        ArrayList<String> list = getAllProductID(productDatabase);
        for(String str : list) {
            id.getComboBoxModel().addElement(str);
        }
    }

    default boolean isNotSameData(@NotNull JComboBoxProduct box, @NotNull ProductDatabase productDatabase) {
        int count = box.getItemCount();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Product> prodList = productDatabase.showProduct();
        for(int i=0;i<count;i++) {
            list.add(box.getComboBoxModel().getElementAt(i));
        }
        for(Product product : prodList) {
            if(!list.contains(product.getId())) return true;
        }
        return list.size() != prodList.size();
    }
}
