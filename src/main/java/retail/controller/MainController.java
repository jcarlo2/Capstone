package retail.controller;

import org.jetbrains.annotations.NotNull;
import retail.controller.inventory.InventoryController;
import retail.controller.login.LogInController;
import retail.controller.north.NorthController;
import retail.controller.transaction.TransactionController;
import retail.model.facade.MainFacade;
import retail.view.BuildGUI;

public class MainController {

    public MainController(@NotNull MainFacade mainFacade, @NotNull BuildGUI buildGUI) {

        new LogInController(buildGUI.getLogIn(),
                            buildGUI.getTopBorderPanel().getUser(),
                            buildGUI.getMainFrame(),
                            mainFacade.getLogInFacade());

        new NorthController(buildGUI.getTopBorderPanel().getNorth(),
                            buildGUI.getBottomPanel());
        new TransactionController(buildGUI,mainFacade);

        new InventoryController(buildGUI,mainFacade);
    }
}
