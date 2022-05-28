package retail.controller.tab.northbutton;

import org.jetbrains.annotations.NotNull;
import retail.view.main.tab.bot.BottomPanel;
import retail.view.main.tab.top.NorthButton;
import retail.view.main.tab.top.TopBorderPanel;

public class NorthButtonController {
    private final BottomPanel bottomPanel;
    private final NorthButton northButton;
    
    public NorthButtonController(@NotNull TopBorderPanel topBorderPanel, BottomPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
        northButton = topBorderPanel.getNorthButton();

        northActionListener();
    }

    private void northActionListener() {
        northButton.getTransaction().addActionListener(e -> {
            if(e.getSource() == northButton.getTransaction()) {
                bottomPanel.getBottomMainCard().getCard().show(bottomPanel.getBottomMainCard(), "salesReport");
                bottomPanel.getManipulatorCard().getCard().show(bottomPanel.getManipulatorCard(), "salesReport");

                northButton.getTransaction().setEnabled(false);
                northButton.getInventory().setEnabled(true);
                northButton.getProductAnalysis().setEnabled(true);
                northButton.getLogFile().setEnabled(true);
            }
        });

        northButton.getInventory().addActionListener(e -> {
            if(e.getSource() == northButton.getInventory()) {
                bottomPanel.getBottomMainCard().getCard().show(bottomPanel.getBottomMainCard(), "inventory");
                bottomPanel.getManipulatorCard().getCard().show(bottomPanel.getManipulatorCard(), "inventory");

                northButton.getTransaction().setEnabled(true);
                northButton.getInventory().setEnabled(false);
                northButton.getProductAnalysis().setEnabled(true);
                northButton.getLogFile().setEnabled(true);
            }
        });

        northButton.getProductAnalysis().addActionListener(e -> {
            if(e.getSource() == northButton.getProductAnalysis()) {
                bottomPanel.getBottomMainCard().getCard().show(bottomPanel.getBottomMainCard(), "prodAnalysis");
                bottomPanel.getManipulatorCard().getCard().show(bottomPanel.getManipulatorCard(), "prodAnalysis");

                northButton.getTransaction().setEnabled(true);
                northButton.getInventory().setEnabled(true);
                northButton.getProductAnalysis().setEnabled(false);
                northButton.getLogFile().setEnabled(true);
            }
        });

        northButton.getLogFile().addActionListener(e -> {
            if(e.getSource() == northButton.getLogFile()) {
                bottomPanel.getBottomMainCard().getCard().show(bottomPanel.getBottomMainCard(), "log");
                bottomPanel.getManipulatorCard().getCard().show(bottomPanel.getManipulatorCard(), "log");

                northButton.getTransaction().setEnabled(true);
                northButton.getInventory().setEnabled(true);
                northButton.getProductAnalysis().setEnabled(true);
                northButton.getLogFile().setEnabled(false);
            }
        });
    }
}
