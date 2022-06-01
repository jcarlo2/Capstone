package retail.controller.tab.transaction;

import org.jetbrains.annotations.NotNull;
import retail.controller.tab.transaction.add.ReturnedController;
import retail.controller.tab.transaction.view.ViewController;
import retail.controller.viewcontroller.TransactionAdd;
import retail.dao.AddTransactionDAO;
import retail.getter.transaction.add.AddTransaction;
import retail.getter.transaction.add.ReturnTransaction;
import retail.model.User;
import retail.shared.customcomponent.jlist.CustomJList;
import retail.view.main.tab.bot.BottomPanel;
import retail.view.main.tab.bot.transaction.center.TransactionMainCard;
import retail.view.main.tab.bot.transaction.center.add.AddCard;
import retail.view.main.tab.bot.transaction.manipulator.panel.AddViewPanel;
import retail.view.main.tab.bot.transaction.manipulator.panel.TransactionManipulatorCard;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.Add;

import java.awt.*;

public class TransactionController {
    private final AddTransactionDAO addTransactionDAO = new AddTransactionDAO();
    private final AddViewPanel addViewPanel;
    private final TransactionManipulatorCard transactionManipulatorCard;
    private final TransactionMainCard transactionMainCard;
    private final AddCard addCard;
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);
    private final ViewController viewController;
    private final Add add;
    private final CustomJList list;

    public TransactionController(@NotNull BottomPanel bottomPanel, User user) {
        new TransactionAdd(user, new AddTransaction(bottomPanel));

        new ReturnedController(new ReturnTransaction(bottomPanel),user);


        viewController = new ViewController(bottomPanel);
        add = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd();
        addViewPanel = bottomPanel.getManipulatorCard().getTransactionManipulator().getAddViewPanel();
        transactionManipulatorCard = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard();
        transactionMainCard = bottomPanel.getBottomMainCard().getTransactionCard();
        addCard = transactionMainCard.getAddCard();
        list = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransaction().getList();

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
            list.populateTransactionList(addTransactionDAO.getTransactionReportList());
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
