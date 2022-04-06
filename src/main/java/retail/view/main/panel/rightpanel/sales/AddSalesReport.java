package retail.view.main.panel.rightpanel.sales;


import retail.model.CustomJTable.CustomJTableInventory;
import retail.model.CustomJTable.CustomJTableSaleReport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddSalesReport extends JPanel {
    private final JPanel west = new JPanel();
    private final JPanel center = new JPanel();
    private final JPanel south = new JPanel();

    private final JPanel addClearPanel = new JPanel();
    private final JPanel addReportPanel = new JPanel();
    private final JButton add = new JButton("Add");
    private final JButton clear = new JButton("Clear");


    private final DefaultTableModel centerModel = new DefaultTableModel(0,8);
    private final CustomJTableSaleReport centerTable = new CustomJTableSaleReport(centerModel);
    private final JScrollPane centerScroll = new JScrollPane(centerTable);

    private final JTextField overall = new JTextField(15);

    public AddSalesReport() {
        setLayout(new BorderLayout());

        setCenter();
        setSouth();

        add(west,BorderLayout.WEST);
        add(center,BorderLayout.CENTER);
        add(south,BorderLayout.SOUTH);
    }

    public void setCenter() {
        center.setLayout(new BorderLayout());
        centerScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        centerScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        center.add(centerScroll,BorderLayout.CENTER);
    }

    public void setSouth() {
        south.setLayout(new BorderLayout());
        south.add(overall);
    }

}





































