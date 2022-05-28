package retail.controller.tab.inventory.add;

import org.jetbrains.annotations.NotNull;
import retail.view.main.tab.bot.BottomPanel;
import retail.view.main.tab.bot.inventory.manipulator.panel.add.Add;
import retail.view.main.tab.top.TopBorderPanel;

public class AddController {
    private final Add add;
    public AddController(@NotNull BottomPanel bottomPanel, TopBorderPanel topBorderPanel) {
        add = bottomPanel.getManipulatorCard().getInventoryManipulator().getAddPanel();
        new AddInventoryController(bottomPanel,topBorderPanel);

        addPanelActionListener();
    }

    private void addPanelActionListener() {
        add.getAdd().addActionListener(e -> {
            add.getAdd().setEnabled(false);
            add.getNullProduct().setEnabled(true);
        });

        add.getNullProduct().addActionListener(e -> {
            add.getAdd().setEnabled(true);
            add.getNullProduct().setEnabled(false);
        });
    }
}
