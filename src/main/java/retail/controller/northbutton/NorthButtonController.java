package retail.controller.northbutton;

import org.jetbrains.annotations.NotNull;
import retail.view.main.panel.leftpanel.LeftBorderPanel;
import retail.view.main.panel.rightpanel.NorthButton;
import retail.view.main.panel.rightpanel.RightBorderPanel;

public class NorthButtonController {
    private final RightBorderPanel rightBorderPanel;
    private final LeftBorderPanel leftBorderPanel;
    private final NorthButton northButton;
    
    public NorthButtonController(@NotNull RightBorderPanel rightBorderPanel, LeftBorderPanel leftBorderPanel) {
        this.rightBorderPanel = rightBorderPanel;
        this.leftBorderPanel = leftBorderPanel;
        northButton = rightBorderPanel.getNorthButton();

        northActionListener();
    }

    private void northActionListener() {
        northButton.getSalesReport().addActionListener(e -> {
            if(e.getSource() == northButton.getSalesReport()) {
                rightBorderPanel.getRightCenterPanel().getCard().show(rightBorderPanel.getRightCenterPanel(),"salesReport");
                leftBorderPanel.getLeftCenterPanel().getCard().show(leftBorderPanel.getLeftCenterPanel(), "salesReport");

                northButton.getSalesReport().setEnabled(false);
                northButton.getInventory().setEnabled(true);
                northButton.getProductAnalysis().setEnabled(true);
                northButton.getImportExport().setEnabled(true);
                northButton.getLogFile().setEnabled(true);
            }
        });

        northButton.getInventory().addActionListener(e -> {
            if(e.getSource() == northButton.getInventory()) {
                rightBorderPanel.getRightCenterPanel().getCard().show(rightBorderPanel.getRightCenterPanel(),"inventory");
                leftBorderPanel.getLeftCenterPanel().getCard().show(leftBorderPanel.getLeftCenterPanel(), "inventory");

                northButton.getSalesReport().setEnabled(true);
                northButton.getInventory().setEnabled(false);
                northButton.getProductAnalysis().setEnabled(true);
                northButton.getImportExport().setEnabled(true);
                northButton.getLogFile().setEnabled(true);
            }
        });

        northButton.getProductAnalysis().addActionListener(e -> {
            if(e.getSource() == northButton.getProductAnalysis()) {
                rightBorderPanel.getRightCenterPanel().getCard().show(rightBorderPanel.getRightCenterPanel(),"prodAnalysis");
                leftBorderPanel.getLeftCenterPanel().getCard().show(leftBorderPanel.getLeftCenterPanel(), "prodAnalysis");

                northButton.getSalesReport().setEnabled(true);
                northButton.getInventory().setEnabled(true);
                northButton.getProductAnalysis().setEnabled(false);
                northButton.getImportExport().setEnabled(true);
                northButton.getLogFile().setEnabled(true);
            }
        });

        northButton.getImportExport().addActionListener(e -> {
            if(e.getSource() == northButton.getImportExport()) {
                rightBorderPanel.getRightCenterPanel().getCard().show(rightBorderPanel.getRightCenterPanel(),"impExp");
                leftBorderPanel.getLeftCenterPanel().getCard().show(leftBorderPanel.getLeftCenterPanel(), "impExp");

                northButton.getSalesReport().setEnabled(true);
                northButton.getInventory().setEnabled(true);
                northButton.getProductAnalysis().setEnabled(true);
                northButton.getImportExport().setEnabled(false);
                northButton.getLogFile().setEnabled(true);
            }
        });

        northButton.getLogFile().addActionListener(e -> {
            if(e.getSource() == northButton.getLogFile()) {
                rightBorderPanel.getRightCenterPanel().getCard().show(rightBorderPanel.getRightCenterPanel(),"log");
                leftBorderPanel.getLeftCenterPanel().getCard().show(leftBorderPanel.getLeftCenterPanel(), "log");

                northButton.getSalesReport().setEnabled(true);
                northButton.getInventory().setEnabled(true);
                northButton.getProductAnalysis().setEnabled(true);
                northButton.getImportExport().setEnabled(true);
                northButton.getLogFile().setEnabled(false);
            }
        });
    }
}
