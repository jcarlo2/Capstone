package retail.model.facade.inventory;

import lombok.Getter;
import retail.model.facade.inventory.add.AddInventoryFacade;
import retail.model.facade.inventory.product.ProductFacade;
import retail.model.facade.inventory.view.ViewInventoryFacade;

@Getter
public class InventoryFacade {
    private final ProductFacade productFacade = new ProductFacade();
    private final AddInventoryFacade addInventoryFacade = new AddInventoryFacade();
    private final ViewInventoryFacade viewInventoryFacade = new ViewInventoryFacade();
}
