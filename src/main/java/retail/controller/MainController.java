package retail.controller;

import retail.controller.inventory.InventoryController;
import retail.controller.login.LogInController;
import retail.controller.northbutton.NorthButtonController;
import retail.model.MainModel;
import retail.view.BuildGUI;

public class MainController {
    private final LogInController logInController;
    private final NorthButtonController northButtonController;
    private final InventoryController inventoryController;
    private final BuildGUI buildGUI;
    private final MainModel mainModel;

    public MainController(MainModel mainModel, BuildGUI buildGUI) {
        this.buildGUI = buildGUI;
        this.mainModel = mainModel;

        logInController = new LogInController(buildGUI.getLogIn(), mainModel.getLogInModel(),buildGUI.getMainFrame());
        northButtonController = new NorthButtonController(buildGUI.getRightBorderPanel(),buildGUI.getLeftBorderPanel());
        inventoryController = new InventoryController(buildGUI.getRightBorderPanel().getRightCenterPanel()
                                ,buildGUI.getLeftBorderPanel().getLeftCenterPanel());
    }
}
