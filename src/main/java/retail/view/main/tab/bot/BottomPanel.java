package retail.view.main.tab.bot;

import lombok.Getter;
import retail.view.main.tab.bot.inventory.center.InventoryMain;
import retail.view.main.tab.bot.inventory.manipulator.InventoryManipulator;
import retail.view.main.tab.bot.logfile.LogFile;
import retail.view.main.tab.bot.salesreport.SalesReport;
import retail.view.main.tab.bot.transaction.center.TransactionMain;
import retail.view.main.tab.bot.transaction.manipulator.TransactionManipulator;

import javax.swing.*;
import java.awt.*;

@Getter
public class BottomPanel extends JPanel {
    private final CardLayout main = new CardLayout();
    private final JPanel mainPanel = new JPanel(main);
    private final LogFile log = new LogFile();
    private final SalesReport sales = new SalesReport();
    private final TransactionMain transactionMain = new TransactionMain();
    private final InventoryMain inventoryMain = new InventoryMain();

    private final CardLayout manipulator = new CardLayout();
    private final JPanel manipulatorPanel = new JPanel(manipulator);
    private final InventoryManipulator inventoryManipulator = new InventoryManipulator();
    private final TransactionManipulator transactionManipulator = new TransactionManipulator();


    public BottomPanel() {
        setLayout(new BorderLayout());
        setMainPanel();
        setManipulatorPanel();

        add(mainPanel,BorderLayout.CENTER);
        add(manipulatorPanel,BorderLayout.WEST);
    }

    private void setManipulatorPanel() {
        manipulatorPanel.add("inventory", inventoryManipulator);
        manipulatorPanel.add("transaction", transactionManipulator);
        manipulator.show(manipulatorPanel, "transaction");
    }

    private void setMainPanel() {
        mainPanel.add("log",log);
        mainPanel.add("sales", sales);
        mainPanel.add("transaction", transactionMain);
        mainPanel.add("inventory", inventoryMain);
        main.show(mainPanel,"transaction");
    }
}
