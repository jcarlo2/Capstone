package retail.controller.north;

import org.jetbrains.annotations.NotNull;
import retail.shared.custom.jbutton.CustomJButton;
import retail.view.main.tab.bot.BottomMainCard;
import retail.view.main.tab.bot.BottomManipulatorCard;
import retail.view.main.tab.bot.BottomPanel;
import retail.view.main.tab.top.NorthPanel;

public class NorthController {
    private final CustomJButton transaction;
    private final CustomJButton inventory;
    private final CustomJButton sales;
    private final CustomJButton log;
    private final BottomMainCard main;
    private final BottomManipulatorCard manipulator;


    public NorthController(@NotNull NorthPanel northPanel, @NotNull BottomPanel bottomPanel) {
        transaction = northPanel.getTransaction();
        inventory = northPanel.getInventory();
        sales = northPanel.getSales();
        log = northPanel.getLogFile();

        main = bottomPanel.getBottomMainCard();
        manipulator = bottomPanel.getManipulatorCard();

        setTransaction();
        setInventory();
    }

    private void setTransaction() {
        transaction.addActionListener(e -> {
            transaction.setEnabled(false);
            inventory.setEnabled(true);
            sales.setEnabled(true);
            log.setEnabled(true);
            main.getCard().show(main,"transaction");
            manipulator.getCard().show(manipulator,"transaction");
        });
    }

    private void setInventory() {
        inventory.addActionListener(e -> {
            transaction.setEnabled(true);
            inventory.setEnabled(false);
            sales.setEnabled(true);
            log.setEnabled(true);
            main.getCard().show(main,"inventory");
            manipulator.getCard().show(manipulator,"inventory");
        });
    }
}
