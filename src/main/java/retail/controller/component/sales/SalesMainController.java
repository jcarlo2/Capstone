package retail.controller.component.sales;

import org.jetbrains.annotations.NotNull;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.main.sales.SalesCard;
import retail.view.main.panel.bot.manipulator.reportmanipulator.panel.AddViewDeletePanel;
import retail.view.main.panel.bot.manipulator.reportmanipulator.panel.ReportCardPanel;
import retail.view.main.panel.top.TopBorderPanel;

import java.awt.*;

public class SalesMainController {
    private final AddViewDeletePanel addViewDeletePanel;
    private final ReportCardPanel reportCardPanel;
    private final SalesCard salesCard;
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);

    public SalesMainController(@NotNull TopBorderPanel topBorderPanel, @NotNull BottomBorderPanel bottomBorderPanel) {
        new SalesAddController(topBorderPanel,bottomBorderPanel);
        new SalesDeleteController(bottomBorderPanel);

        addViewDeletePanel = bottomBorderPanel.getManipulatorCard().getSalesReportManipulator().getAddViewDeletePanel();
        reportCardPanel = bottomBorderPanel.getManipulatorCard().getSalesReportManipulator().getReportCardPanel();
        salesCard = bottomBorderPanel.getBottomMainCard().getSalesReport();

        leftSalesReportActionListener();
    }

    private void leftSalesReportActionListener() {
        addViewDeletePanel.getAdd().addActionListener(e -> {
            if(e.getSource() == addViewDeletePanel.getAdd()) {
                reportCardPanel.getCardLayout().show(reportCardPanel,"add");
                salesCard.getCardLayout().show(salesCard,"addReport");
                addViewDeletePanel.getAdd().setFont(sansSerif);
                addViewDeletePanel.getDelete().setFont(segoeUI);
                addViewDeletePanel.getView().setFont(segoeUI);
                addViewDeletePanel.getAdd().setEnabled(false);
                addViewDeletePanel.getDelete().setEnabled(true);
                addViewDeletePanel.getView().setEnabled(true);
            }
        });

        addViewDeletePanel.getDelete().addActionListener(e -> {
            reportCardPanel.getCardLayout().show(reportCardPanel,"delete");
            salesCard.getCardLayout().show(salesCard,"deleteReport");
            addViewDeletePanel.getAdd().setFont(segoeUI);
            addViewDeletePanel.getDelete().setFont(sansSerif);
            addViewDeletePanel.getView().setFont(segoeUI);
            addViewDeletePanel.getAdd().setEnabled(true);
            addViewDeletePanel.getDelete().setEnabled(false);
            addViewDeletePanel.getView().setEnabled(true);
        });

        addViewDeletePanel.getView().addActionListener(e -> {
            reportCardPanel.getCardLayout().show(reportCardPanel,"view");
            salesCard.getCardLayout().show(salesCard,"compareReport");
            addViewDeletePanel.getAdd().setFont(segoeUI);
            addViewDeletePanel.getDelete().setFont(segoeUI);
            addViewDeletePanel.getView().setFont(sansSerif);
            addViewDeletePanel.getAdd().setEnabled(true);
            addViewDeletePanel.getDelete().setEnabled(true);
            addViewDeletePanel.getView().setEnabled(false);
        });
    }
}
