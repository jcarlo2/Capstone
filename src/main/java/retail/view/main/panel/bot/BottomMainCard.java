package retail.view.main.panel.bot;

import lombok.Getter;
import retail.view.main.panel.bot.main.inventory.InventoryMainCard;
import retail.view.main.panel.bot.main.logfile.LogFile;
import retail.view.main.panel.bot.main.print.ImportExport;
import retail.view.main.panel.bot.main.salesreport.ProductAnalysis;
import retail.view.main.panel.bot.main.transaction.TransactionMainCard;

import javax.swing.*;
import java.awt.*;
@Getter
public class BottomMainCard extends JPanel {
    private final CardLayout card = new CardLayout();
    private final ImportExport impExp = new ImportExport();
    private final LogFile log = new LogFile();
    private final ProductAnalysis prodAnalysis = new ProductAnalysis();
    private final TransactionMainCard transactionCard = new TransactionMainCard();
    private final InventoryMainCard inventoryCard = new InventoryMainCard();

    public BottomMainCard() {
        setLayout(card);
        add("impExp",impExp);
        add("log",log);
        add("prodAnalysis",prodAnalysis);
        add("salesReport", transactionCard);
        add("inventory", inventoryCard);
        card.show(this,"salesReport");
    }
}
