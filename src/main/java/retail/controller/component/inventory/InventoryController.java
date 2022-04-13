package retail.controller.component.inventory;

import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.main.inventory.InventoryMainCard;
import retail.view.main.panel.bot.manipulator.inventory.panel.AddViewDelete;
import retail.view.main.panel.bot.manipulator.inventory.panel.InventoryManipulatorCard;
import retail.view.main.panel.top.TopBorderPanel;

import java.awt.*;

public class InventoryController {
    private final AddViewDelete addViewDelete;
    private final InventoryManipulatorCard manipulatorCard;
    private final InventoryMainCard mainCard;

    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);

    public InventoryController(BottomBorderPanel bottomBorderPanel, TopBorderPanel topBorderPanel) {
        this.addViewDelete = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getAddViewDelete();
        this.manipulatorCard = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getInventoryManipulatorCard();
        this.mainCard = bottomBorderPanel.getBottomMainCard().getInventoryCard();

        new AddController(bottomBorderPanel,topBorderPanel);
        new ViewController(bottomBorderPanel);
        new ProductController(bottomBorderPanel);
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
            mainCard.getCardLayout().show(mainCard,"delete");
            manipulatorCard.getCardLayout().show(manipulatorCard,"delete");
            addViewDelete.getAdd().setEnabled(true);
            addViewDelete.getView().setEnabled(false);
            addViewDelete.getProduct().setEnabled(true);
            addViewDelete.getAdd().setFont(segoeUI);
            addViewDelete.getView().setFont(sansSerif);
            addViewDelete.getProduct().setFont(segoeUI);
        });

        addViewDelete.getProduct().addActionListener(e -> {
            mainCard.getCardLayout().show(mainCard,"product");
            manipulatorCard.getCardLayout().show(manipulatorCard,"productList");
            addViewDelete.getAdd().setEnabled(true);
            addViewDelete.getView().setEnabled(true);
            addViewDelete.getProduct().setEnabled(false);
            addViewDelete.getAdd().setFont(segoeUI);
            addViewDelete.getView().setFont(segoeUI);
            addViewDelete.getProduct().setFont(sansSerif);
        });
    }
}
