package retail.model.facade.inventory.add;

import retail.model.service.Calculate;
import retail.model.service.Creator;
import retail.model.service.inventory.add.AddInventoryService;
import retail.model.service.inventory.product.ProductViewService;

public class AddInventoryFacade {
    private final AddInventoryService service = new AddInventoryService();
    private final ProductViewService productService = new ProductViewService();
    private final Creator creator = new Creator();
    private final Calculate calculate = new Calculate();
}
