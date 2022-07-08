package retail.model.facade.inventory;

import lombok.Getter;
import retail.model.facade.inventory.add.AddInventoryFacade;
import retail.model.facade.inventory.add.NullInventoryFacade;
import retail.model.facade.inventory.product.ProductFacade;
import retail.model.facade.inventory.view.InventoryViewFacade;

@Getter
public class InventoryFacade {
    private final ProductFacade productFacade = new ProductFacade();
    private final AddInventoryFacade addInventoryFacade = new AddInventoryFacade();
    private final NullInventoryFacade nullInventoryFacade = new NullInventoryFacade();
    private final InventoryViewFacade inventoryViewFacade = new InventoryViewFacade();
}
