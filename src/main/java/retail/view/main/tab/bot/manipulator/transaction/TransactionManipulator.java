package retail.view.main.tab.bot.manipulator.transaction;

import lombok.Getter;
import retail.view.main.tab.bot.manipulator.transaction.panel.AddViewPanel;
import retail.view.main.tab.bot.manipulator.transaction.panel.TransactionManipulatorCard;
import retail.view.main.tab.bot.manipulator.transaction.panel.view.View;
import retail.view.main.tab.bot.manipulator.transaction.panel.add.AddCard;

import javax.swing.*;
import java.awt.*;

@Getter
public class TransactionManipulator extends JPanel {
    private final AddViewPanel addViewPanel = new AddViewPanel();
    private final TransactionManipulatorCard transactionManipulatorCard = new TransactionManipulatorCard();

    public TransactionManipulator() {
        setLayout(new BorderLayout());

        add(addViewPanel,BorderLayout.NORTH);
        add(transactionManipulatorCard,BorderLayout.CENTER);
    }

    public View getView() {
        return  getTransactionManipulatorCard().getView();
    }

    public AddCard getAddCard() {
       return transactionManipulatorCard.getAdd().getAddCard();
    }
}
