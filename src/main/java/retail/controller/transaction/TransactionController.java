package retail.controller.transaction;

import org.jetbrains.annotations.NotNull;
import retail.controller.transaction.add.AddTransactionController;
import retail.controller.transaction.add.ReturnTransactionController;
import retail.controller.transaction.view.ViewTransactionController;
import retail.model.facade.MainFacade;
import retail.view.BuildGUI;
import retail.view.main.tab.bot.transaction.center.TransactionMain;
import retail.view.main.tab.bot.transaction.manipulator.TransactionManipulator;

public class TransactionController{
    private final TransactionManipulator manipulator;
    private final TransactionMain main;

    public TransactionController(@NotNull BuildGUI buildGUI, @NotNull MainFacade mainFacade) {
        this.main = buildGUI.getBottomPanel().getTransactionMain();
        this.manipulator = buildGUI.getBottomPanel().getTransactionManipulator();

        new AddTransactionController(
                buildGUI.getTopBorderPanel().getUser(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getTransactionManipulator().getAddManipulator().getAddTransactionManipulator(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getTransactionMain().getAddCenter().getAddTransactionCenter(),
                mainFacade.getTransactionFacade().getAddTransactionFacade());

        new ReturnTransactionController(
                buildGUI.getTopBorderPanel().getUser(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getTransactionManipulator().getAddManipulator().getReturnedTransactionManipulator(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getTransactionMain().getAddCenter().getReturnedTransactionCenter(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getTransactionMain().getAddCenter().getReturnDialog(),
                mainFacade.getTransactionFacade().getReturnTransactionFacade());

        new ViewTransactionController(
                buildGUI.getMainFrame().getMain().getBottomPanel().getTransactionMain().getTransactionViewCenter(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getTransactionManipulator().getViewManipulator(),
                mainFacade.getTransactionFacade().getViewTransactionFacade());

        add();
        view();
        addReport();
        returnReport();
    }

    private void add() {
        manipulator.getAdd().addActionListener(e ->{
            manipulator.getCardLayout().show(manipulator.getWrapper2(), "add");
            main.getCardLayout().show(main, "add");
            manipulator.getView().setEnabled(true);
            manipulator.getAdd().setEnabled(false);
        }) ;
    }

    private void view() {
        manipulator.getView().addActionListener(e ->{
            manipulator.getCardLayout().show(manipulator.getWrapper2(), "view");
            main.getCardLayout().show(main, "view");
            manipulator.getView().setEnabled(false);
            manipulator.getAdd().setEnabled(true);
        }) ;
    }

    private void addReport() {
        manipulator.getAddManipulator().getAddReport().addActionListener(e ->{
            manipulator.getAddManipulator().getCard().show(manipulator.getAddManipulator().getWrapper2(), "add");
            main.getAddCenter().getCard().show(main.getAddCenter(), "add");
            manipulator.getAddManipulator().getAddReport().setEnabled(false);
            manipulator.getAddManipulator().getReturnReport().setEnabled(true);
        }) ;
    }

    private void returnReport() {
        manipulator.getAddManipulator().getReturnReport().addActionListener(e ->{
            manipulator.getAddManipulator().getCard().show(manipulator.getAddManipulator().getWrapper2(), "return");
            main.getAddCenter().getCard().show(main.getAddCenter(), "return");
            manipulator.getAddManipulator().getAddReport().setEnabled(true);
            manipulator.getAddManipulator().getReturnReport().setEnabled(false);
        }) ;
    }
}
