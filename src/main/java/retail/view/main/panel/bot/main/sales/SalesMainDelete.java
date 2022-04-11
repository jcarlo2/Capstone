package retail.view.main.panel.bot.main.sales;

import lombok.Getter;
import retail.customcomponent.jtable.CustomJTableSalesReport;

import javax.swing.*;
import java.awt.*;

@Getter
public class SalesMainDelete extends JPanel{
    private final CustomJTableSalesReport table = new CustomJTableSalesReport();
    private final JScrollPane scroll = new JScrollPane(table);

    public SalesMainDelete() {
        setLayout(new BorderLayout());
        add(scroll,BorderLayout.CENTER);
    }
}
