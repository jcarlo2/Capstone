package retail.controller.transaction;

import org.jetbrains.annotations.NotNull;
import retail.controller.transaction.add.AddTransactionController;
import retail.controller.transaction.add.ReturnTransactionController;
import retail.model.facade.MainFacade;
import retail.view.BuildGUI;
import retail.view.main.tab.bot.transaction.center.TransactionMainCard;
import retail.view.main.tab.bot.transaction.manipulator.panel.AddViewPanel;
import retail.view.main.tab.bot.transaction.manipulator.panel.TransactionManipulatorCard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionController implements ActionListener {
    private final AddViewPanel addViewPanel;
    private final TransactionManipulatorCard manipulatorCard;
    private final TransactionMainCard mainCard;

    public TransactionController(@NotNull BuildGUI buildGUI, @NotNull MainFacade mainFacade) {
        this.addViewPanel = buildGUI.getBottomPanel().getManipulatorCard().getTransactionManipulator().getAddViewPanel();
        this.manipulatorCard = buildGUI.getBottomPanel().getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard();
        this.mainCard = buildGUI.getBottomPanel().getBottomMainCard().getTransactionCard();

        new AddTransactionController(buildGUI.getTopBorderPanel().getUserPanel(),
                                     buildGUI.getMainFrame().getMain().getBottomPanel().getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransactionManipulator(),
                                     buildGUI.getMainFrame().getMain().getBottomPanel().getBottomMainCard().getTransactionCard().getAddCard().getAddTransactionCenter(),
                                     mainFacade.getTransactionFacade().getAddTransactionFacade());

        new ReturnTransactionController(buildGUI.getTopBorderPanel().getUserPanel(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransactionManipulator(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getBottomMainCard().getTransactionCard().getAddCard().getReturnedTransactionCenter(),
                buildGUI.getMainFrame().getMain().getBottomPanel().getBottomMainCard().getTransactionCard().getAddCard().getReturnDialog(),
                mainFacade.getTransactionFacade().getReturnTransactionFacade());
        addViewPanel.addEventListener(this);
        addViewPanel.viewEventListener(this);
        manipulatorCard.getAdd().addActionListener(this);
        manipulatorCard.getAdd().returnActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addViewPanel.getAdd()) {
            manipulatorCard.getCardLayout().show(manipulatorCard, "add");
            mainCard.getCardLayout().show(mainCard, "add");
            addViewPanel.getView().setEnabled(true);
            addViewPanel.getAdd().setEnabled(false);
        } else if(e.getSource() == addViewPanel.getView()){
            manipulatorCard.getCardLayout().show(manipulatorCard, "view");
            mainCard.getCardLayout().show(mainCard, "view");
            addViewPanel.getView().setEnabled(false);
            addViewPanel.getAdd().setEnabled(true);
        } else if(e.getSource() == manipulatorCard.getAdd().getAddReport()) {
            manipulatorCard.getAdd().getAddCard().getCard().show(manipulatorCard.getAdd().getAddCard(), "add");
            mainCard.getAddCard().getCard().show(mainCard.getAddCard(), "add");
            manipulatorCard.getAdd().getAddReport().setEnabled(false);
            manipulatorCard.getAdd().getReturnReport().setEnabled(true);
        } else if(e.getSource() == manipulatorCard.getAdd().getReturnReport()) {
            manipulatorCard.getAdd().getAddCard().getCard().show(manipulatorCard.getAdd().getAddCard(), "return");
            mainCard.getAddCard().getCard().show(mainCard.getAddCard(), "return");
            manipulatorCard.getAdd().getAddReport().setEnabled(true);
            manipulatorCard.getAdd().getReturnReport().setEnabled(false);
        }
    }
}
