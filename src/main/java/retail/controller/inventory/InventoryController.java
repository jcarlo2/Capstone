package retail.controller.inventory;

import org.jetbrains.annotations.NotNull;
import retail.controller.inventory.add.AddInventoryController;
import retail.controller.inventory.product.ProductViewController;
import retail.controller.inventory.view.ViewInventoryController;
import retail.model.facade.MainFacade;
import retail.view.BuildGUI;
import retail.view.main.tab.bot.inventory.center.InventoryMain;
import retail.view.main.tab.bot.inventory.manipulator.InventoryManipulator;

import javax.swing.table.DefaultTableModel;

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

        new AddInventoryController(
                buildGUI.getBottomPanel().getInventoryMain().getAdd(),
                buildGUI.getBottomPanel().getInventoryManipulator().getAddManipulator(),
                mainFacade.getInventoryFacade().getAddInventoryFacade(),
                buildGUI.getTopBorderPanel().getUser());

        new ViewInventoryController(
                buildGUI.getBottomPanel().getInventoryMain().getView(),
                buildGUI.getBottomPanel().getInventoryManipulator().getViewManipulator(),
                mainFacade.getInventoryFacade().getViewInventoryFacade());
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
        manipulator.getAddManipulator().getAddProduct().addActionListener(e -> {
            manipulator.getAddManipulator().getAddProduct().setEnabled(false);
            manipulator.getAddManipulator().getNullProduct().setEnabled(true);
            ((DefaultTableModel)main.getAdd().getTable().getModel()).setRowCount(0);
        });
    }

    public void nullReport() {
        manipulator.getAddManipulator().getNullProduct().addActionListener(e -> {
            manipulator.getAddManipulator().getAddProduct().setEnabled(true);
            manipulator.getAddManipulator().getNullProduct().setEnabled(false);
            ((DefaultTableModel)main.getAdd().getTable().getModel()).setRowCount(0);
        });
    }
}
