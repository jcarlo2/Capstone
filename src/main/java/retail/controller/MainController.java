package retail.controller;

import org.jetbrains.annotations.NotNull;
import retail.controller.login.LogInController;
import retail.controller.transaction.TransactionController;
import retail.model.facade.MainFacade;
import retail.view.BuildGUI;

public class MainController {
    private final MainFacade mainFacade;
    private final BuildGUI buildGUI;

    public MainController(@NotNull MainFacade mainFacade, @NotNull BuildGUI buildGUI) {
        this.mainFacade = mainFacade;
        this.buildGUI = buildGUI;

        new LogInController(buildGUI.getLogIn(),
                            buildGUI.getTopBorderPanel().getUserPanel(),
                            buildGUI.getMainFrame(),
                            mainFacade.getLogInFacade());
        new TransactionController(buildGUI,mainFacade);
    }
}
