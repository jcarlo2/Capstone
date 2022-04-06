package retail.view.main.panel.rightpanel.sales;

import retail.model.CustomJTable.CustomJTableInventory;
import retail.model.CustomJTable.CustomJTableSaleReport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DeleteSalesReport extends JPanel{
    private final DefaultTableModel model = new DefaultTableModel(0,8);
    private final CustomJTableSaleReport table = new CustomJTableSaleReport(model);
    private final JScrollPane scroll = new JScrollPane(table);

    public DeleteSalesReport() {
        setLayout(new BorderLayout());

        add(scroll,BorderLayout.CENTER);
    }
}
