package retail.model.facade.inventory.product;

import org.jetbrains.annotations.NotNull;
import retail.model.service.Calculate;
import retail.model.service.Creator;
import retail.model.service.inventory.ProductViewService;
import retail.shared.entity.Merchandise;
import retail.shared.pojo.ProductDisplay;

import java.util.ArrayList;

public class ProductFacade {
    private final ProductViewService service = new ProductViewService();
    private final Creator creator = new Creator();
    private final Calculate calculate = new Calculate();

    public ArrayList<Merchandise> getAllProduct() {
        return service.getAllProduct();
    }

    public ArrayList<Merchandise> search(String str) {
        return service.search(str);
    }

    public ProductDisplay convertToDisplay(String[] data) {
        return creator.convertToDisplay(data);
    }

    public boolean update(String[] data) {
        return service.update(creator.convertToDisplay(data));
    }

    public boolean verifyProductDetail(String @NotNull [] data) {
        return calculate.isValidNumber(data[2],data[3],data[4]) && !data[0].isBlank();
    }

    public void addProduct(String[] data) {
        service.addProduct(creator.createMerchandise(data));
    }

    public boolean isProductExist(String id) {
        return service.isProductExist(id);
    }

    public void delete(String id) {
        service.delete(id);
    }
}
