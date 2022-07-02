package retail.controller.inventory;

import org.jetbrains.annotations.NotNull;
import retail.controller.inventory.product.ProductViewController;
import retail.model.facade.MainFacade;
import retail.view.BuildGUI;

public class InventoryController {
    public InventoryController(@NotNull BuildGUI buildGUI, @NotNull MainFacade mainFacade) {
        new ProductViewController(
                buildGUI.getBottomPanel().getBottomMainCard().getInventory().getProductCenter(),
                buildGUI.getBottomPanel().getManipulatorCard().getInventoryManipulator().getInventoryManipulatorCard().getProductManipulator(),
                mainFacade.getInventoryFacade().getProductFacade());
    }
}
