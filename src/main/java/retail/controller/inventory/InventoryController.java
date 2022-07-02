package retail.controller.inventory;

import org.jetbrains.annotations.NotNull;
import retail.controller.inventory.add.AddInventoryController;
import retail.controller.inventory.product.ProductViewController;
import retail.model.facade.MainFacade;
import retail.view.BuildGUI;
import retail.view.main.tab.bot.inventory.center.InventoryMainCard;
import retail.view.main.tab.bot.inventory.manipulator.panel.AddViewProduct;
import retail.view.main.tab.bot.inventory.manipulator.panel.InventoryManipulatorCard;

public class InventoryController {
    private final AddViewProduct addViewProductPanel;
    private final InventoryManipulatorCard manipulator;
    private final InventoryMainCard main;

    public InventoryController(@NotNull BuildGUI buildGUI, @NotNull MainFacade mainFacade) {
        this.addViewProductPanel = buildGUI.getBottomPanel().getManipulatorCard().getInventory().getAddViewProduct();
        this.manipulator = buildGUI.getBottomPanel().getManipulatorCard().getInventory().getInventory();
        this.main = buildGUI.getBottomPanel().getBottomMainCard().getInventory();

        new ProductViewController(
                buildGUI.getBottomPanel().getBottomMainCard().getInventory().getProduct(),
                buildGUI.getBottomPanel().getManipulatorCard().getInventory().getInventory().getProductManipulator(),
                mainFacade.getInventoryFacade().getProductFacade());

        new AddInventoryController(
                buildGUI.getBottomPanel().getBottomMainCard().getInventory().getAdd().getAddCenter(),
                buildGUI.getBottomPanel().getManipulatorCard().getInventory().getInventory().getAdd().getAddPanel().getAddManipulator(),
                mainFacade.getInventoryFacade().getAddInventoryFacade(),
                buildGUI.getTopBorderPanel().getUserPanel());
        add();
        view();
        product();
        addReport();
        nullReport();
    }

    public void add() {
        addViewProductPanel.getAdd().addActionListener(e -> {
            addViewProductPanel.getAdd().setEnabled(false);
            addViewProductPanel.getView().setEnabled(true);
            addViewProductPanel.getProduct().setEnabled(true);

            manipulator.getCardLayout().show(manipulator,"add");
            main.getCardLayout().show(main,"add");
        });
    }

    public void view() {
        addViewProductPanel.getView().addActionListener(e -> {
            addViewProductPanel.getAdd().setEnabled(true);
            addViewProductPanel.getView().setEnabled(false);
            addViewProductPanel.getProduct().setEnabled(true);

            manipulator.getCardLayout().show(manipulator,"view");
            main.getCardLayout().show(main,"view");
        });
    }

    public void product() {
        addViewProductPanel.getProduct().addActionListener(e -> {
            addViewProductPanel.getAdd().setEnabled(true);
            addViewProductPanel.getView().setEnabled(true);
            addViewProductPanel.getProduct().setEnabled(false);

            manipulator.getCardLayout().show(manipulator,"product");
            main.getCardLayout().show(main,"product");
        });
    }

    public void addReport() {
        manipulator.getAdd().getAddProduct().addActionListener(e -> {
            manipulator.getAdd().getAddProduct().setEnabled(false);
            manipulator.getAdd().getNullProduct().setEnabled(true);

            manipulator.getAdd().getAddPanel().getCard().show(manipulator.getAdd().getAddPanel(),"add");
            main.getAdd().getCard().show(main.getAdd(),"add");
        });
    }

    public void nullReport() {
        manipulator.getAdd().getNullProduct().addActionListener(e -> {
            manipulator.getAdd().getAddProduct().setEnabled(true);
            manipulator.getAdd().getNullProduct().setEnabled(false);

            manipulator.getAdd().getAddPanel().getCard().show(manipulator.getAdd().getAddPanel(),"null");
            main.getAdd().getCard().show(main.getAdd(),"null");
        });
    }
}
