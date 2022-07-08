package retail.view.main.tab.bot.report.center;

import javax.swing.*;
import java.awt.*;

public class ReportCenter extends JPanel {
    private final JTabbedPane tab = new JTabbedPane();

    private final SalesPanel delivery = new SalesPanel();
    private final JPanel sales = new JPanel();
    private final JPanel graph = new JPanel();

    public ReportCenter() {
        setLayout(new BorderLayout());
        setTab();
        add(tab,BorderLayout.CENTER);
    }

    private void setTab() {
        tab.add("Sales",sales);
        tab.add("Delivery",delivery);
        tab.add("Graph",graph);
    }
}
