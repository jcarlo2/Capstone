package retail.controller.component.sales;

import org.jetbrains.annotations.NotNull;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.main.sales.SalesMainCard;
import retail.view.main.panel.bot.manipulator.reportmanipulator.panel.AddViewDeletePanel;
import retail.view.main.panel.bot.manipulator.reportmanipulator.panel.SalesManipulatorCard;
import retail.view.main.panel.top.TopBorderPanel;

import java.awt.*;

public class MainSalesController {
    private final AddViewDeletePanel addViewDeletePanel;
    private final SalesManipulatorCard salesManipulatorCard;
    private final SalesMainCard salesMainCard;
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);

    public MainSalesController(@NotNull TopBorderPanel topBorderPanel, @NotNull BottomBorderPanel bottomBorderPanel) {
        new AddSalesController(topBorderPanel,bottomBorderPanel);
        new DeleteSalesController(bottomBorderPanel);

        addViewDeletePanel = bottomBorderPanel.getManipulatorCard().getSalesReportManipulator().getAddViewDeletePanel();
        salesManipulatorCard = bottomBorderPanel.getManipulatorCard().getSalesReportManipulator().getSalesManipulatorCard();
        salesMainCard = bottomBorderPanel.getBottomMainCard().getSalesReport();

        salesReportActionListener();
    }

    private void salesReportActionListener() {
        addViewDeletePanel.getAdd().addActionListener(e -> {
            if(e.getSource() == addViewDeletePanel.getAdd()) {
                salesManipulatorCard.getCardLayout().show(salesManipulatorCard,"add");
                salesMainCard.getCardLayout().show(salesMainCard,"addReport");
                addViewDeletePanel.getAdd().setFont(sansSerif);
                addViewDeletePanel.getDelete().setFont(segoeUI);
                addViewDeletePanel.getView().setFont(segoeUI);
                addViewDeletePanel.getAdd().setEnabled(false);
                addViewDeletePanel.getDelete().setEnabled(true);
                addViewDeletePanel.getView().setEnabled(true);
            }
        });

        addViewDeletePanel.getDelete().addActionListener(e -> {
            salesManipulatorCard.getCardLayout().show(salesManipulatorCard,"delete");
            salesMainCard.getCardLayout().show(salesMainCard,"deleteReport");
            addViewDeletePanel.getAdd().setFont(segoeUI);
            addViewDeletePanel.getDelete().setFont(sansSerif);
            addViewDeletePanel.getView().setFont(segoeUI);
            addViewDeletePanel.getAdd().setEnabled(true);
            addViewDeletePanel.getDelete().setEnabled(false);
            addViewDeletePanel.getView().setEnabled(true);
        });

        addViewDeletePanel.getView().addActionListener(e -> {
            salesManipulatorCard.getCardLayout().show(salesManipulatorCard,"view");
            salesMainCard.getCardLayout().show(salesMainCard,"compareReport");
            addViewDeletePanel.getAdd().setFont(segoeUI);
            addViewDeletePanel.getDelete().setFont(segoeUI);
            addViewDeletePanel.getView().setFont(sansSerif);
            addViewDeletePanel.getAdd().setEnabled(true);
            addViewDeletePanel.getDelete().setEnabled(true);
            addViewDeletePanel.getView().setEnabled(false);
        });
    }
}
