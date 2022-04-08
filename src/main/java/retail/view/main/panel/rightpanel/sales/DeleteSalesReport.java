package retail.view.main.panel.rightpanel.sales;

import retail.component.jtable.CustomJTableSalesReport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DeleteSalesReport extends JPanel{
    private final DefaultTableModel model = new DefaultTableModel(0,8);
    private final CustomJTableSalesReport table = new CustomJTableSalesReport(model);
    private final JScrollPane scroll = new JScrollPane(table);

    public DeleteSalesReport() {
        setLayout(new BorderLayout());

        add(scroll,BorderLayout.CENTER);
    }
}
