package retail.view.main.tab.bot.transaction.manipulator;

import lombok.Getter;
import retail.view.main.tab.bot.transaction.manipulator.panel.AddViewPanel;
import retail.view.main.tab.bot.transaction.manipulator.panel.TransactionManipulatorCard;
import retail.view.main.tab.bot.transaction.manipulator.panel.view.View;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.AddCard;

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
}
