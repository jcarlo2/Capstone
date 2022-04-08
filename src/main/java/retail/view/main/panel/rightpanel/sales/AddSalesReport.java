package retail.view.main.panel.rightpanel.sales;


import lombok.Getter;
import retail.component.jtable.CustomJTableSalesReport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class AddSalesReport extends JPanel {
    private final DefaultTableModel centerModel = new DefaultTableModel(0,8);
    private final CustomJTableSalesReport centerTable = new CustomJTableSalesReport(centerModel);
    private final JScrollPane centerScroll = new JScrollPane(centerTable);
    private final JTextField totalAmount = new JTextField(15);

    public AddSalesReport() {
        setLayout(new BorderLayout());


        add(centerScroll,BorderLayout.CENTER);
        add(totalAmount,BorderLayout.SOUTH);
    }

}





































