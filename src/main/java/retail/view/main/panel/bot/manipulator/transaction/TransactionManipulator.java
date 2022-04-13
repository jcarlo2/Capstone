package retail.view.main.panel.bot.manipulator.transaction;

import lombok.Getter;
import retail.customcomponent.jlist.CustomJList;
import retail.view.main.panel.bot.manipulator.transaction.panel.TransactionManipulatorCard;
import retail.view.main.panel.bot.manipulator.transaction.panel.AddViewDeletePanel;
import retail.view.main.panel.bot.manipulator.transaction.panel.add.AddCard;

import javax.swing.*;
import java.awt.*;

@Getter
public class TransactionManipulator extends JPanel {
    private final AddViewDeletePanel addViewDeletePanel = new AddViewDeletePanel();
    private final TransactionManipulatorCard transactionManipulatorCard = new TransactionManipulatorCard();

    public TransactionManipulator() {
        setLayout(new BorderLayout());

        add(addViewDeletePanel,BorderLayout.NORTH);
        add(transactionManipulatorCard,BorderLayout.CENTER);
    }

    public CustomJList getViewList() {
        return  getTransactionManipulatorCard().getView().getList();
    }

    public CustomJList getDeleteList() {
        return  getTransactionManipulatorCard().getDelete().getList();
    }

    public AddCard getAddCard() {
       return transactionManipulatorCard.getAdd().getAddCard();
    }
}
