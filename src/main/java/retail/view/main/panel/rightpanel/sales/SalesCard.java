package retail.view.main.panel.rightpanel.sales;

import javax.swing.*;
import java.awt.*;

public class SalesCard extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final CompareSalesReport compareSalesReport = new CompareSalesReport();
    private final AddSalesReport addSalesReport = new AddSalesReport();

    public SalesCard() {
        setLayout(cardLayout);
        add("compareReport", compareSalesReport);
        add("addReport", addSalesReport);
        cardLayout.show(this,"addReport");
    }
}
