package retail.controller.sales;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductController;
import retail.controller.database.SalesReportController;
import retail.dto.SalesReportItemObject;
import retail.view.main.panel.leftpanel.LeftCenterPanel;
import retail.view.main.panel.leftpanel.reportmanipulator.panel.AddPanel;
import retail.view.main.panel.leftpanel.reportmanipulator.panel.AddViewDeletePanel;
import retail.view.main.panel.leftpanel.reportmanipulator.panel.ReportCardPanel;
import retail.view.main.panel.rightpanel.RightCenterPanel;

import java.awt.*;

public class SalesController {
    private final SalesReportController salesController = new SalesReportController();
    private final ProductController productController = new ProductController();
    private final RightCenterPanel rightCenterPanel;
    private final LeftCenterPanel leftCenterPanel;
    private final AddViewDeletePanel addViewDeletePanel;
    private final ReportCardPanel reportCardPanel;
    private final AddPanel addPanel;
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);

    public SalesController(RightCenterPanel rightCenterPanel, @NotNull LeftCenterPanel leftCenterPanel) {
        this.rightCenterPanel = rightCenterPanel;
        this.leftCenterPanel = leftCenterPanel;
        addViewDeletePanel = leftCenterPanel.getSalesReportManipulator().getAddViewDeletePanel();
        reportCardPanel = leftCenterPanel.getSalesReportManipulator().getReportCardPanel();
        addPanel = leftCenterPanel.getSalesReportManipulator().getReportCardPanel().getAddPanel();

        leftSalesReportActionListener();
    }

    public void leftDataToReportTable() {

    }

    public SalesReportItemObject getDataFromAddPanel() {
        String[] data = new String[7];
        return null;
    }

    public void leftSalesReportActionListener() {
        addViewDeletePanel.getAdd().addActionListener(e -> {
            if(e.getSource() == addViewDeletePanel.getAdd()) {
                reportCardPanel.getCardLayout().show(reportCardPanel,"add");
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
            addViewDeletePanel.getAdd().setFont(segoeUI);
            addViewDeletePanel.getDelete().setFont(sansSerif);
            addViewDeletePanel.getView().setFont(segoeUI);
            addViewDeletePanel.getAdd().setEnabled(true);
            addViewDeletePanel.getDelete().setEnabled(false);
            addViewDeletePanel.getView().setEnabled(true);
        });

        addViewDeletePanel.getView().addActionListener(e -> {
            reportCardPanel.getCardLayout().show(reportCardPanel,"view");
            addViewDeletePanel.getAdd().setFont(segoeUI);
            addViewDeletePanel.getDelete().setFont(segoeUI);
            addViewDeletePanel.getView().setFont(sansSerif);
            addViewDeletePanel.getAdd().setEnabled(true);
            addViewDeletePanel.getDelete().setEnabled(true);
            addViewDeletePanel.getView().setEnabled(false);
        });
    }
}
