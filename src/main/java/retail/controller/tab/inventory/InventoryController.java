package retail.controller.tab.inventory;

import org.jetbrains.annotations.NotNull;
import retail.controller.tab.inventory.add.AddController;
import retail.controller.tab.inventory.product.ProductController;
import retail.controller.tab.inventory.view.ViewController;
import retail.view.main.tab.bot.BottomPanel;
import retail.view.main.tab.bot.inventory.center.InventoryMainCard;
import retail.view.main.tab.bot.inventory.manipulator.panel.AddViewDelete;
import retail.view.main.tab.bot.inventory.manipulator.panel.InventoryManipulatorCard;
import retail.view.main.tab.top.TopBorderPanel;

import java.awt.*;

public class InventoryController {
    private final AddViewDelete addViewDelete;
    private final InventoryManipulatorCard manipulatorCard;
    private final InventoryMainCard mainCard;
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);

    public InventoryController(@NotNull BottomPanel bottomPanel, TopBorderPanel topBorderPanel) {
        this.addViewDelete = bottomPanel.getManipulatorCard().getInventoryManipulator().getAddViewDelete();
        this.manipulatorCard = bottomPanel.getManipulatorCard().getInventoryManipulator().getInventoryManipulatorCard();
        this.mainCard = bottomPanel.getBottomMainCard().getInventoryCard();

        new AddController(bottomPanel,topBorderPanel);
        new ViewController(bottomPanel);
        new ProductController(bottomPanel);
        inventoryActionListener();
    }

    private void inventoryActionListener() {
        addViewDelete.getAdd().addActionListener(e -> {
            mainCard.getCardLayout().show(mainCard,"add");
            manipulatorCard.getCardLayout().show(manipulatorCard,"add");
            addViewDelete.getAdd().setEnabled(false);
            addViewDelete.getView().setEnabled(true);
            addViewDelete.getProduct().setEnabled(true);
            addViewDelete.getAdd().setFont(sansSerif);
            addViewDelete.getView().setFont(segoeUI);
            addViewDelete.getProduct().setFont(segoeUI);
        });

        addViewDelete.getView().addActionListener(e -> {
            mainCard.getCardLayout().show(mainCard,"view");
            manipulatorCard.getCardLayout().show(manipulatorCard,"view");
            addViewDelete.getAdd().setEnabled(true);
            addViewDelete.getView().setEnabled(false);
            addViewDelete.getProduct().setEnabled(true);
            addViewDelete.getAdd().setFont(segoeUI);
            addViewDelete.getView().setFont(sansSerif);
            addViewDelete.getProduct().setFont(segoeUI);
        });

        addViewDelete.getProduct().addActionListener(e -> {
            mainCard.getCardLayout().show(mainCard,"product");
            manipulatorCard.getCardLayout().show(manipulatorCard,"product");
            addViewDelete.getAdd().setEnabled(true);
            addViewDelete.getView().setEnabled(true);
            addViewDelete.getProduct().setEnabled(false);
            addViewDelete.getAdd().setFont(segoeUI);
            addViewDelete.getView().setFont(segoeUI);
            addViewDelete.getProduct().setFont(sansSerif);
        });
    }
}
