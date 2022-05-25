package retail.view.main.tab.bot;

import lombok.Getter;
import retail.view.main.tab.bot.inventory.center.InventoryMainCard;
import retail.view.main.tab.bot.logfile.LogFile;
import retail.view.main.tab.bot.salesreport.ProductAnalysis;
import retail.view.main.tab.bot.transaction.center.TransactionMainCard;

import javax.swing.*;
import java.awt.*;
@Getter
public class BottomMainCard extends JPanel {
    private final CardLayout card = new CardLayout();
    private final LogFile log = new LogFile();
    private final ProductAnalysis prodAnalysis = new ProductAnalysis();
    private final TransactionMainCard transactionCard = new TransactionMainCard();
    private final InventoryMainCard inventoryCard = new InventoryMainCard();

    public BottomMainCard() {
        setLayout(card);
        add("log",log);
        add("prodAnalysis",prodAnalysis);
        add("salesReport", transactionCard);
        add("inventory", inventoryCard);
        card.show(this,"salesReport");
    }
}
