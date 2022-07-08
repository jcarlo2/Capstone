package retail.controller.north;

import org.jetbrains.annotations.NotNull;
import retail.shared.custom.jbutton.CustomJButton;
import retail.view.main.tab.bot.BottomPanel;
import retail.view.main.tab.top.North;

public class NorthController {
    private final CustomJButton transaction;
    private final CustomJButton inventory;
    private final CustomJButton report;
    private final CustomJButton log;
    private final BottomPanel bottomPanel;


    public NorthController(@NotNull North north, @NotNull BottomPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
        transaction = north.getTransaction();
        inventory = north.getInventory();
        report = north.getReport();
        log = north.getLogFile();

        setTransaction();
        setInventory();
        setReport();
    }

    private void setTransaction() {
        transaction.addActionListener(e -> {
            transaction.setEnabled(false);
            inventory.setEnabled(true);
            report.setEnabled(true);
            log.setEnabled(true);
            bottomPanel.getMain().show(bottomPanel.getMainPanel(),"transaction");
            bottomPanel.getManipulator().show(bottomPanel.getManipulatorPanel(),"transaction");
        });
    }

    private void setInventory() {
        inventory.addActionListener(e -> {
            transaction.setEnabled(true);
            inventory.setEnabled(false);
            report.setEnabled(true);
            log.setEnabled(true);
            bottomPanel.getMain().show(bottomPanel.getMainPanel(),"inventory");
            bottomPanel.getManipulator().show(bottomPanel.getManipulatorPanel(),"inventory");
        });
    }

    private void setReport() {
        report.addActionListener(e -> {
            transaction.setEnabled(true);
            inventory.setEnabled(true);
            report.setEnabled(false);
            log.setEnabled(true);
            bottomPanel.getMain().show(bottomPanel.getMainPanel(),"report");
            bottomPanel.getManipulator().show(bottomPanel.getManipulatorPanel(),"report");
        });
    }
}
