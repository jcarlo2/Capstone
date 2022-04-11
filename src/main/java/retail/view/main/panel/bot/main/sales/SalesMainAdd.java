package retail.view.main.panel.bot.main.sales;


import lombok.Getter;
import retail.customcomponent.jtable.CustomJTableSalesReport;

import javax.swing.*;
import java.awt.*;

@Getter
public class SalesMainAdd extends JPanel {
    private final CustomJTableSalesReport centerTable = new CustomJTableSalesReport();
    private final JScrollPane centerScroll = new JScrollPane(centerTable);
    private final JTextField totalAmount = new JTextField(15);

    public SalesMainAdd() {
        setLayout(new BorderLayout());


        add(centerScroll,BorderLayout.CENTER);
        add(totalAmount,BorderLayout.SOUTH);
    }

}





































