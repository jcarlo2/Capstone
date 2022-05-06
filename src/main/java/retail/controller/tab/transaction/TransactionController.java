package retail.controller.tab.transaction;

import org.jetbrains.annotations.NotNull;
import retail.controller.tab.transaction.add.AddController;
import retail.controller.tab.transaction.add.ReturnedController;
import retail.controller.tab.transaction.view.ViewController;
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.bot.center.transaction.TransactionMainCard;
import retail.view.main.tab.bot.center.transaction.add.AddCard;
import retail.view.main.tab.bot.manipulator.transaction.panel.AddViewPanel;
import retail.view.main.tab.bot.manipulator.transaction.panel.TransactionManipulatorCard;
import retail.view.main.tab.bot.manipulator.transaction.panel.add.Add;
import retail.view.main.tab.top.TopBorderPanel;

import java.awt.*;

public class TransactionController {
    private final AddViewPanel addViewPanel;
    private final TransactionManipulatorCard transactionManipulatorCard;
    private final TransactionMainCard transactionMainCard;
    private final AddCard addCard;
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);
    private final ViewController viewController;
    private final Add add;

    public TransactionController(@NotNull TopBorderPanel topBorderPanel, @NotNull BottomBorderPanel bottomBorderPanel) {
        new AddController(topBorderPanel,bottomBorderPanel);
        new ReturnedController(bottomBorderPanel.getBottomMainCard(),bottomBorderPanel.getManipulatorCard());
        viewController = new ViewController(bottomBorderPanel);
        add = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd();
        addViewPanel = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getAddViewPanel();
        transactionManipulatorCard = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard();
        transactionMainCard = bottomBorderPanel.getBottomMainCard().getTransactionCard();
        addCard = transactionMainCard.getAddCard();

        transactionAddViewActionListener();
        addAndReturnedActionListener();
        add.getAddReport().setEnabled(false);
    }

    private void addAndReturnedActionListener() {
        add.getAddReport().addActionListener(e -> {
            add.getAddCard().getCard().show(add.getAddCard(),"add");
            addCard.getCard().show(addCard,"add");
            add.getAddReport().setEnabled(false);
            add.getReturnReport().setEnabled(true);
        });

        add.getReturnReport().addActionListener(e -> {
            add.getAddCard().getCard().show(add.getAddCard(),"return");
            addCard.getCard().show(addCard,"return");
            add.getReturnReport().setEnabled(false);
            add.getAddReport().setEnabled(true);
        });
    }

    private void transactionAddViewActionListener() {
        addViewPanel.getAdd().addActionListener(e -> {
            if(e.getSource() == addViewPanel.getAdd()) {
                transactionManipulatorCard.getCardLayout().show(transactionManipulatorCard,"add");
                transactionMainCard.getCardLayout().show(transactionMainCard,"add");
                addViewPanel.getAdd().setFont(sansSerif);
                addViewPanel.getView().setFont(segoeUI);
                addViewPanel.getAdd().setEnabled(false);
                addViewPanel.getView().setEnabled(true);
            }
        });

        addViewPanel.getView().addActionListener(e -> {
            transactionManipulatorCard.getCardLayout().show(transactionManipulatorCard,"view");
            transactionMainCard.getCardLayout().show(transactionMainCard,"view");
            addViewPanel.getAdd().setFont(segoeUI);
            addViewPanel.getView().setFont(sansSerif);
            addViewPanel.getAdd().setEnabled(true);
            addViewPanel.getView().setEnabled(false);
            viewController.populateListWithReport();
        });
    }
}
