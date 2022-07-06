package retail.controller.north;

import org.jetbrains.annotations.NotNull;
import retail.shared.custom.jbutton.CustomJButton;
import retail.view.main.tab.bot.BottomPanel;
import retail.view.main.tab.top.North;

public class NorthController {
    private final CustomJButton transaction;
    private final CustomJButton inventory;
    private final CustomJButton sales;
    private final CustomJButton log;
    private final BottomPanel bottomPanel;


    public NorthController(@NotNull North north, @NotNull BottomPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
        transaction = north.getTransaction();
        inventory = north.getInventory();
        sales = north.getSales();
        log = north.getLogFile();

        setTransaction();
        setInventory();
    }

    private void setTransaction() {
        transaction.addActionListener(e -> {
            transaction.setEnabled(false);
            inventory.setEnabled(true);
            sales.setEnabled(true);
            log.setEnabled(true);
            bottomPanel.getMain().show(bottomPanel.getMainPanel(),"transaction");
            bottomPanel.getManipulator().show(bottomPanel.getManipulatorPanel(),"transaction");
        });
    }

    private void setInventory() {
        inventory.addActionListener(e -> {
            transaction.setEnabled(true);
            inventory.setEnabled(false);
            sales.setEnabled(true);
            log.setEnabled(true);
            bottomPanel.getMain().show(bottomPanel.getMainPanel(),"inventory");
            bottomPanel.getManipulator().show(bottomPanel.getManipulatorPanel(),"inventory");
        });
    }
}
