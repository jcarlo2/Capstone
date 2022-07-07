package retail.controller.inventory;

import org.jetbrains.annotations.NotNull;
import retail.controller.inventory.product.ProductViewController;
import retail.model.facade.MainFacade;
import retail.view.BuildGUI;
import retail.view.main.tab.bot.inventory.center.InventoryMain;
import retail.view.main.tab.bot.inventory.manipulator.InventoryManipulator;

public class InventoryController {
    private final InventoryManipulator manipulator;
    private final InventoryMain main;

    public InventoryController(@NotNull BuildGUI buildGUI, @NotNull MainFacade mainFacade) {
        this.manipulator = buildGUI.getBottomPanel().getInventoryManipulator();
        this.main = buildGUI.getBottomPanel().getInventoryMain();

        new ProductViewController(
                buildGUI.getBottomPanel().getInventoryMain().getProduct(),
                buildGUI.getBottomPanel().getInventoryManipulator().getProductManipulator(),
                mainFacade.getInventoryFacade().getProductFacade());


        add();
        view();
        product();
        addReport();
        nullReport();
    }

    public void add() {
        manipulator.getAdd().addActionListener(e -> {
            manipulator.getAdd().setEnabled(false);
            manipulator.getView().setEnabled(true);
            manipulator.getProduct().setEnabled(true);

            manipulator.getCardLayout().show(manipulator.getWrapper2(),"add");
            main.getCardLayout().show(main,"add");
        });
    }

    public void view() {
        manipulator.getView().addActionListener(e -> {
            manipulator.getAdd().setEnabled(true);
            manipulator.getView().setEnabled(false);
            manipulator.getProduct().setEnabled(true);

            manipulator.getCardLayout().show(manipulator.getWrapper2(),"view");
            main.getCardLayout().show(main,"view");
        });
    }

    public void product() {
        manipulator.getProduct().addActionListener(e -> {
            manipulator.getAdd().setEnabled(true);
            manipulator.getView().setEnabled(true);
            manipulator.getProduct().setEnabled(false);

            manipulator.getCardLayout().show(manipulator.getWrapper2(),"product");
            main.getCardLayout().show(main,"product");
        });
    }

    public void addReport() {
        manipulator.getAddManipulatorCard().getAddProduct().addActionListener(e -> {
            manipulator.getAddManipulatorCard().getAddProduct().setEnabled(false);
            manipulator.getAddManipulatorCard().getNullProduct().setEnabled(true);

            manipulator.getAddManipulatorCard().getCard().show(manipulator.getAddManipulatorCard().getWrapper1(), "add");
            main.getAdd().getCard().show(main.getAdd(),"add");
        });
    }

    public void nullReport() {
        manipulator.getAddManipulatorCard().getNullProduct().addActionListener(e -> {
            manipulator.getAddManipulatorCard().getAddProduct().setEnabled(true);
            manipulator.getAddManipulatorCard().getNullProduct().setEnabled(false);

            manipulator.getAddManipulatorCard().getCard().show(manipulator.getAddManipulatorCard().getWrapper1(), "null");
            main.getAdd().getCard().show(main.getAdd(),"null");
        });
    }
}
