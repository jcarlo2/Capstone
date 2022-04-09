package retail.controller.sales;

import org.jetbrains.annotations.NotNull;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.manipulator.reportmanipulator.panel.DeletePanel;

public class SalesDeleteController {
    private final DeletePanel deletePanel;
    public SalesDeleteController(@NotNull BottomBorderPanel bottomBorderPanel) {
        deletePanel = bottomBorderPanel.getManipulatorCard().getSalesReportManipulator().getReportCardPanel().getDeletePanel();


    }



}
