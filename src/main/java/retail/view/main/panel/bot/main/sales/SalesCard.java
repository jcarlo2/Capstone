package retail.view.main.panel.bot.main.sales;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class SalesCard extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final CompareSalesReport compareSalesReport = new CompareSalesReport();
    private final AddSalesReport addSalesReport = new AddSalesReport();
    private final DeleteSalesReport deleteSalesReport = new DeleteSalesReport();

    public SalesCard() {
        setLayout(cardLayout);
        add("compareReport", compareSalesReport);
        add("addReport", addSalesReport);
        add("deleteReport", deleteSalesReport);
        cardLayout.show(this,"compareReport");
    }
}
