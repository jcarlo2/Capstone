package retail.controller.component.northbutton;

import org.jetbrains.annotations.NotNull;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.top.NorthButton;
import retail.view.main.panel.top.TopBorderPanel;

public class NorthButtonController {
    private final BottomBorderPanel bottomBorderPanel;
    private final NorthButton northButton;
    
    public NorthButtonController(@NotNull TopBorderPanel topBorderPanel, BottomBorderPanel bottomBorderPanel) {
        this.bottomBorderPanel = bottomBorderPanel;
        northButton = topBorderPanel.getNorthButton();

        northActionListener();
    }

    private void northActionListener() {
        northButton.getTransaction().addActionListener(e -> {
            if(e.getSource() == northButton.getTransaction()) {
                bottomBorderPanel.getBottomMainCard().getCard().show(bottomBorderPanel.getBottomMainCard(), "salesReport");
                bottomBorderPanel.getManipulatorCard().getCard().show(bottomBorderPanel.getManipulatorCard(), "salesReport");

                northButton.getTransaction().setEnabled(false);
                northButton.getInventory().setEnabled(true);
                northButton.getProductAnalysis().setEnabled(true);
                northButton.getImportExport().setEnabled(true);
                northButton.getLogFile().setEnabled(true);
            }
        });

        northButton.getInventory().addActionListener(e -> {
            if(e.getSource() == northButton.getInventory()) {
                bottomBorderPanel.getBottomMainCard().getCard().show(bottomBorderPanel.getBottomMainCard(), "inventory");
                bottomBorderPanel.getManipulatorCard().getCard().show(bottomBorderPanel.getManipulatorCard(), "inventory");

                northButton.getTransaction().setEnabled(true);
                northButton.getInventory().setEnabled(false);
                northButton.getProductAnalysis().setEnabled(true);
                northButton.getImportExport().setEnabled(true);
                northButton.getLogFile().setEnabled(true);
            }
        });

        northButton.getProductAnalysis().addActionListener(e -> {
            if(e.getSource() == northButton.getProductAnalysis()) {
                bottomBorderPanel.getBottomMainCard().getCard().show(bottomBorderPanel.getBottomMainCard(), "prodAnalysis");
                bottomBorderPanel.getManipulatorCard().getCard().show(bottomBorderPanel.getManipulatorCard(), "prodAnalysis");

                northButton.getTransaction().setEnabled(true);
                northButton.getInventory().setEnabled(true);
                northButton.getProductAnalysis().setEnabled(false);
                northButton.getImportExport().setEnabled(true);
                northButton.getLogFile().setEnabled(true);
            }
        });

        northButton.getImportExport().addActionListener(e -> {
            if(e.getSource() == northButton.getImportExport()) {
                bottomBorderPanel.getBottomMainCard().getCard().show(bottomBorderPanel.getBottomMainCard(), "impExp");
                bottomBorderPanel.getManipulatorCard().getCard().show(bottomBorderPanel.getManipulatorCard(), "impExp");

                northButton.getTransaction().setEnabled(true);
                northButton.getInventory().setEnabled(true);
                northButton.getProductAnalysis().setEnabled(true);
                northButton.getImportExport().setEnabled(false);
                northButton.getLogFile().setEnabled(true);
            }
        });

        northButton.getLogFile().addActionListener(e -> {
            if(e.getSource() == northButton.getLogFile()) {
                bottomBorderPanel.getBottomMainCard().getCard().show(bottomBorderPanel.getBottomMainCard(), "log");
                bottomBorderPanel.getManipulatorCard().getCard().show(bottomBorderPanel.getManipulatorCard(), "log");

                northButton.getTransaction().setEnabled(true);
                northButton.getInventory().setEnabled(true);
                northButton.getProductAnalysis().setEnabled(true);
                northButton.getImportExport().setEnabled(true);
                northButton.getLogFile().setEnabled(false);
            }
        });
    }
}
