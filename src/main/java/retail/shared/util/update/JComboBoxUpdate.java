package retail.shared.util.update;

import org.jetbrains.annotations.NotNull;
import retail.dao.ProductDAO;
import retail.shared.pojo.Product;
import retail.shared.customcomponent.jcombobox.JComboBoxProduct;

import java.util.ArrayList;

public interface JComboBoxUpdate {

    default @NotNull ArrayList<String> getAllProductID(@NotNull ProductDAO productDAO) {
        ArrayList<Product> product = productDAO.showProduct();
        ArrayList<String> list = new ArrayList<>();
        for(Product item : product) {
            list.add(item.getId());
        }
        return list;
    }

    default void setProductIdList(@NotNull JComboBoxProduct id, ProductDAO productDAO) {
        id.removeAllItems();
        ArrayList<String> list = getAllProductID(productDAO);
        for(String str : list) {
            id.getComboBoxModel().addElement(str);
        }
    }

    default boolean isNotSameData(@NotNull JComboBoxProduct box, @NotNull ProductDAO productDAO) {
        int count = box.getItemCount();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Product> prodList = productDAO.showProduct();
        for(int i=0;i<count;i++) {
            list.add(box.getComboBoxModel().getElementAt(i));
        }
        for(Product product : prodList) {
            if(!list.contains(product.getId())) return true;
        }
        return list.size() != prodList.size();
    }
}
