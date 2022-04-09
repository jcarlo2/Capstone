package retail.view.main.panel.bot;

import lombok.Getter;
import retail.view.main.panel.bot.main.logfile.LogFile;
import retail.view.main.panel.bot.main.inventory.Inventory;
import retail.view.main.panel.bot.main.print.ImportExport;
import retail.view.main.panel.bot.main.productanalysis.ProductAnalysis;
import retail.view.main.panel.bot.main.sales.SalesCard;

import javax.swing.*;
import java.awt.*;
@Getter
public class BottomMainCard extends JPanel {
    private final CardLayout card = new CardLayout();
    private final ImportExport impExp = new ImportExport();
    private final LogFile log = new LogFile();
    private final ProductAnalysis prodAnalysis = new ProductAnalysis();
    private final SalesCard salesReport = new SalesCard();
    private final Inventory inventory = new Inventory();

    public BottomMainCard() {
        setLayout(card);
        add("impExp",impExp);
        add("log",log);
        add("prodAnalysis",prodAnalysis);
        add("salesReport", salesReport);
        add("inventory", inventory);
        card.show(this,"salesReport");
    }
}
