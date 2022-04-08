package retail.controller;

import retail.controller.inventory.InventoryController;
import retail.controller.login.LogInController;
import retail.controller.northbutton.NorthButtonController;
import retail.controller.sales.SalesController;
import retail.view.BuildGUI;

public class MainController {
    public MainController(BuildGUI buildGUI) {
        new LogInController(buildGUI.getLogIn(), buildGUI.getMainFrame(),buildGUI.getLeftBorderPanel().getUserPanel());

        new NorthButtonController(buildGUI.getRightBorderPanel(), buildGUI.getLeftBorderPanel());

        new InventoryController(buildGUI.getRightBorderPanel().getRightCenterPanel()
                , buildGUI.getLeftBorderPanel().getLeftCenterPanel());

        new SalesController(buildGUI.getRightBorderPanel().getRightCenterPanel()
                , buildGUI.getLeftBorderPanel());
    }
}
