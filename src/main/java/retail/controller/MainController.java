package retail.controller;

import retail.controller.inventory.InventoryController;
import retail.controller.login.LogInController;
import retail.controller.northbutton.NorthButtonController;
import retail.controller.sales.SalesMainController;
import retail.view.BuildGUI;

public class MainController {
    public MainController(BuildGUI buildGUI) {

        new LogInController(buildGUI.getLogIn(), buildGUI.getMainFrame(),buildGUI.getMainFrame().getMain().getTopBorderPanel().getUserPanel());

        new NorthButtonController(buildGUI.getTopBorderPanel(), buildGUI.getBottomBorderPanel());

        new InventoryController(buildGUI.getBottomBorderPanel());

        new SalesMainController(buildGUI.getTopBorderPanel(),buildGUI.getBottomBorderPanel());
    }
}
