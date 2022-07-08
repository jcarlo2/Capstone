package retail.model.service.inventory.product;

import org.jetbrains.annotations.NotNull;
import retail.model.service.Service;
import retail.shared.entity.Merchandise;
import retail.shared.pojo.ProductDisplay;

import java.util.ArrayList;

public class ProductViewService implements Service {

    public ArrayList<Merchandise> getAllProduct() {
        return product.getAllProduct();
    }

    public ArrayList<Merchandise> search(String str) {
        ArrayList<Merchandise> productList = product.getAllProduct();
        ArrayList<Merchandise> returnList = new ArrayList<>();
        for(Merchandise product : productList) {
            String id = product.getId().toLowerCase();
            if(id.contains(str.toLowerCase())) returnList.add(product);
        }
        return returnList;
    }

    public boolean update(@NotNull ProductDisplay display) {
        if(!product.isProductExist(display.getId())) return false;
        product.updateProduct(display);
        return true;
    }

    public void addProduct(Merchandise merchandise) {
        product.addProduct(merchandise);
    }

    public boolean isProductExist(String id) {
        return product.isProductExist(id);
    }

    public void delete(String id) {
        product.delete(id);
    }

    public String findPriceById(String productId) {
        return product.findPriceById(productId);
    }

    public String findBoxPiecesById(String id) {
        return product.findPiecesPerBoxById(id);
    }

    public String findQuantityById(String id) {
        return product.findQuantityById(id);
    }
}
