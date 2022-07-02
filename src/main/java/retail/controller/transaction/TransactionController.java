package retail.controller.transaction;

import org.jetbrains.annotations.NotNull;
import retail.controller.transaction.add.AddTransactionController;
import retail.controller.transaction.add.ReturnTransactionController;
import retail.controller.transaction.view.ViewTransactionController;
import retail.model.facade.MainFacade;
import retail.view.BuildGUI;
import retail.view.main.tab.bot.transaction.center.TransactionMainCard;
import retail.view.main.tab.bot.transaction.manipulator.panel.AddViewPanel;
import retail.view.main.tab.bot.transaction.manipulator.panel.TransactionManipulatorCard;

public class TransactionController{
    private final AddViewPanel addViewPanel;
    private final TransactionManipulatorCard manipulatorCard;
    private final TransactionMainCard mainCard;

    public TransactionController(@NotNull BuildGUI buildGUI, @NotNull MainFacade mainFacade) {
        this.addViewPanel = buildGUI.getBottomPanel().getManipulatorCard().getTransaction().getAddViewPanel();
        this.manipulatorCard = buildGUI.getBottomPanel().getManipulatorCard().getTransaction().getTransactionManipulatorCard();
        this.mainCard = buildGUI.getBottomPanel().getBottomMainCard().getTransaction();

        new AddTransactionController(
                buildGUI.getTopBorderPanel().getUserPanel(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getManipulatorCard().getTransaction().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransactionManipulator(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getBottomMainCard().getTransaction().getAddCard().getAddTransactionCenter(),
                mainFacade.getTransactionFacade().getAddTransactionFacade());

        new ReturnTransactionController(
                buildGUI.getTopBorderPanel().getUserPanel(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getManipulatorCard().getTransaction().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransactionManipulator(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getBottomMainCard().getTransaction().getAddCard().getReturnedTransactionCenter(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getBottomMainCard().getTransaction().getAddCard().getReturnDialog(),
                mainFacade.getTransactionFacade().getReturnTransactionFacade());

        new ViewTransactionController(
                buildGUI.getMainFrame().getMain().getBottomPanel().getBottomMainCard().getTransaction().getViewTransactionCenter(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getManipulatorCard().getTransaction().getTransactionManipulatorCard().getViewTransactionManipulator(),
                mainFacade.getTransactionFacade().getViewTransactionFacade());

        add();
        view();
        addReport();
        returnReport();
    }

    private void add() {
        addViewPanel.getAdd().addActionListener(e ->{
            manipulatorCard.getCardLayout().show(manipulatorCard, "add");
            mainCard.getCardLayout().show(mainCard, "add");
            addViewPanel.getView().setEnabled(true);
            addViewPanel.getAdd().setEnabled(false);
        }) ;
    }

    private void view() {
        addViewPanel.getView().addActionListener(e ->{
            manipulatorCard.getCardLayout().show(manipulatorCard, "view");
            mainCard.getCardLayout().show(mainCard, "view");
            addViewPanel.getView().setEnabled(false);
            addViewPanel.getAdd().setEnabled(true);
        }) ;
    }

    private void addReport() {
        manipulatorCard.getAdd().getAddReport().addActionListener(e ->{
            manipulatorCard.getAdd().getAddCard().getCard().show(manipulatorCard.getAdd().getAddCard(), "add");
            mainCard.getAddCard().getCard().show(mainCard.getAddCard(), "add");
            manipulatorCard.getAdd().getAddReport().setEnabled(false);
            manipulatorCard.getAdd().getReturnReport().setEnabled(true);
        }) ;
    }

    private void returnReport() {
        manipulatorCard.getAdd().getReturnReport().addActionListener(e ->{
            manipulatorCard.getAdd().getAddCard().getCard().show(manipulatorCard.getAdd().getAddCard(), "return");
            mainCard.getAddCard().getCard().show(mainCard.getAddCard(), "return");
            manipulatorCard.getAdd().getAddReport().setEnabled(true);
            manipulatorCard.getAdd().getReturnReport().setEnabled(false);
        }) ;
    }
}
