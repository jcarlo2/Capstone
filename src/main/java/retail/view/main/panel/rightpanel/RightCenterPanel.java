package retail.view.main.panel.rightpanel;

import lombok.Getter;
import retail.view.main.panel.rightpanel.logfile.LogFile;
import retail.view.main.panel.rightpanel.inventory.Inventory;
import retail.view.main.panel.rightpanel.print.ImportExport;
import retail.view.main.panel.rightpanel.productanalysis.ProductAnalysis;
import retail.view.main.panel.rightpanel.sales.SalesCard;

import javax.swing.*;
import java.awt.*;
@Getter
public class RightCenterPanel extends JPanel {
    private final CardLayout card = new CardLayout();
    private final ImportExport impExp = new ImportExport();
    private final LogFile log = new LogFile();
    private final ProductAnalysis prodAnalysis = new ProductAnalysis();
    private final SalesCard salesReport = new SalesCard();
    private final Inventory inventoryPanel = new Inventory();

    public RightCenterPanel() {
        setLayout(card);
        add("impExp",impExp);
        add("log",log);
        add("prodAnalysis",prodAnalysis);
        add("salesReport", salesReport);
        add("inventory", inventoryPanel);
        card.show(this,"salesReport");
    }
}
