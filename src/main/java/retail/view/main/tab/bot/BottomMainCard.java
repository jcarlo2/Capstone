package retail.view.main.tab.bot;

import lombok.Getter;
import retail.view.main.tab.bot.inventory.center.InventoryMainCard;
import retail.view.main.tab.bot.logfile.LogFile;
import retail.view.main.tab.bot.salesreport.SalesReport;
import retail.view.main.tab.bot.transaction.center.TransactionMainCard;

import javax.swing.*;
import java.awt.*;
@Getter
public class BottomMainCard extends JPanel {
    private final CardLayout card = new CardLayout();
    private final LogFile log = new LogFile();
    private final SalesReport sales = new SalesReport();
    private final TransactionMainCard transaction = new TransactionMainCard();
    private final InventoryMainCard inventory = new InventoryMainCard();

    public BottomMainCard() {
        setLayout(card);
        add("log",log);
        add("sales", sales);
        add("transaction", transaction);
        add("inventory", inventory);
        card.show(this,"transaction");
    }
}
