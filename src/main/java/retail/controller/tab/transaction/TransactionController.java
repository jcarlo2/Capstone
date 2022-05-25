package retail.controller.tab.transaction;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.TransactionDatabase;
import retail.controller.tab.transaction.add.AddController;
import retail.controller.tab.transaction.add.ReturnedController;
import retail.controller.tab.transaction.view.ViewController;
import retail.shared.customcomponent.jlist.CustomJList;
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.bot.transaction.center.TransactionMainCard;
import retail.view.main.tab.bot.transaction.center.add.AddCard;
import retail.view.main.tab.bot.transaction.manipulator.panel.AddViewPanel;
import retail.view.main.tab.bot.transaction.manipulator.panel.TransactionManipulatorCard;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.Add;
import retail.view.main.tab.top.TopBorderPanel;

import java.awt.*;

public class TransactionController {
    private final TransactionDatabase transactionDatabase = new TransactionDatabase();
    private final AddViewPanel addViewPanel;
    private final TransactionManipulatorCard transactionManipulatorCard;
    private final TransactionMainCard transactionMainCard;
    private final AddCard addCard;
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);
    private final ViewController viewController;
    private final Add add;
    private final CustomJList list;

    public TransactionController(@NotNull TopBorderPanel topBorderPanel, @NotNull BottomBorderPanel bottomBorderPanel) {
        new AddController(topBorderPanel,bottomBorderPanel);
        new ReturnedController(bottomBorderPanel.getBottomMainCard(),bottomBorderPanel.getManipulatorCard());
        viewController = new ViewController(bottomBorderPanel);
        add = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd();
        addViewPanel = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getAddViewPanel();
        transactionManipulatorCard = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard();
        transactionMainCard = bottomBorderPanel.getBottomMainCard().getTransactionCard();
        addCard = transactionMainCard.getAddCard();
        list = bottomBorderPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransaction().getList();

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
            list.populateTransactionList(transactionDatabase.getTransactionReportList());
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
