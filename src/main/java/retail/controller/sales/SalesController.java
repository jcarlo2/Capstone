package retail.controller.sales;

import org.jetbrains.annotations.NotNull;
import retail.view.main.panel.leftpanel.LeftBorderPanel;
import retail.view.main.panel.leftpanel.reportmanipulator.panel.AddViewDeletePanel;
import retail.view.main.panel.leftpanel.reportmanipulator.panel.ReportCardPanel;
import retail.view.main.panel.rightpanel.RightCenterPanel;
import retail.view.main.panel.rightpanel.sales.SalesCard;

import java.awt.*;

public class SalesController {
    private final AddViewDeletePanel addViewDeletePanel;
    private final ReportCardPanel reportCardPanel;
    private final SalesCard salesCard;
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);

    public SalesController(@NotNull RightCenterPanel rightCenterPanel, @NotNull LeftBorderPanel leftBorderPanel) {
        new SalesAddController(rightCenterPanel,leftBorderPanel);
        addViewDeletePanel = leftBorderPanel.getLeftCenterPanel().getSalesReportManipulator().getAddViewDeletePanel();
        reportCardPanel = leftBorderPanel.getLeftCenterPanel().getSalesReportManipulator().getReportCardPanel();
        salesCard = rightCenterPanel.getSalesReport();


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
