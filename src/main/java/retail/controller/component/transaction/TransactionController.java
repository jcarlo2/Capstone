package retail.controller.component.transaction;

import org.jetbrains.annotations.NotNull;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.main.transaction.TransactionMainCard;
import retail.view.main.panel.bot.manipulator.transaction.panel.AddViewDeletePanel;
import retail.view.main.panel.bot.manipulator.transaction.panel.TransactionManipulatorCard;
import retail.view.main.panel.top.TopBorderPanel;

import java.awt.*;

public class TransactionController {
    private final AddViewDeletePanel addViewDeletePanel;
    private final TransactionManipulatorCard transactionManipulatorCard;
    private final TransactionMainCard transactionMainCard;
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);

    public TransactionController(@NotNull TopBorderPanel topBorderPanel, @NotNull BottomBorderPanel bottomBorderPanel) {
        new AddController(topBorderPanel,bottomBorderPanel);
        new DeleteController(bottomBorderPanel);

        addViewDeletePanel = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getAddViewDeletePanel();
        transactionManipulatorCard = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard();
        transactionMainCard = bottomBorderPanel.getBottomMainCard().getTransactionCard();

        salesReportActionListener();
    }

    private void salesReportActionListener() {
        addViewDeletePanel.getAdd().addActionListener(e -> {
            if(e.getSource() == addViewDeletePanel.getAdd()) {
                transactionManipulatorCard.getCardLayout().show(transactionManipulatorCard,"add");
                transactionMainCard.getCardLayout().show(transactionMainCard,"addReport");
                addViewDeletePanel.getAdd().setFont(sansSerif);
                addViewDeletePanel.getDelete().setFont(segoeUI);
                addViewDeletePanel.getView().setFont(segoeUI);
                addViewDeletePanel.getAdd().setEnabled(false);
                addViewDeletePanel.getDelete().setEnabled(true);
                addViewDeletePanel.getView().setEnabled(true);
            }
        });

        addViewDeletePanel.getDelete().addActionListener(e -> {
            transactionManipulatorCard.getCardLayout().show(transactionManipulatorCard,"delete");
            transactionMainCard.getCardLayout().show(transactionMainCard,"deleteReport");
            addViewDeletePanel.getAdd().setFont(segoeUI);
            addViewDeletePanel.getDelete().setFont(sansSerif);
            addViewDeletePanel.getView().setFont(segoeUI);
            addViewDeletePanel.getAdd().setEnabled(true);
            addViewDeletePanel.getDelete().setEnabled(false);
            addViewDeletePanel.getView().setEnabled(true);
        });

        addViewDeletePanel.getView().addActionListener(e -> {
            transactionManipulatorCard.getCardLayout().show(transactionManipulatorCard,"view");
            transactionMainCard.getCardLayout().show(transactionMainCard,"compareReport");
            addViewDeletePanel.getAdd().setFont(segoeUI);
            addViewDeletePanel.getDelete().setFont(segoeUI);
            addViewDeletePanel.getView().setFont(sansSerif);
            addViewDeletePanel.getAdd().setEnabled(true);
            addViewDeletePanel.getDelete().setEnabled(true);
            addViewDeletePanel.getView().setEnabled(false);
        });
    }
}
