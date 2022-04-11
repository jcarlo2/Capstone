package retail.view.main.panel.bot.main.sales;

import lombok.Getter;
import retail.customcomponent.jtable.CustomJTableSalesReport;

import javax.swing.*;
import java.awt.*;

@Getter
public class SalesMainView extends JPanel {
    private final JPanel totalSalesReport = new JPanel();

    private final JPanel one = new JPanel();
    private final CustomJTableSalesReport firstTable = new CustomJTableSalesReport();
    private final JScrollPane firstScroll = new JScrollPane(firstTable);
    private final JTextField totalFirst = new JTextField();

    private final JPanel two = new JPanel();
    private final CustomJTableSalesReport secondTable = new CustomJTableSalesReport();
    private final JScrollPane secondScroll = new JScrollPane(secondTable);
    private final JTextField totalSecond = new JTextField();

    public SalesMainView() {
        setLayout(new GridLayout(2,1));
        one.setLayout(new BorderLayout());

        totalFirst.setHorizontalAlignment(SwingConstants.CENTER);
        totalSecond.setHorizontalAlignment(SwingConstants.CENTER);


        one.add(firstScroll,BorderLayout.CENTER);
        one.add(totalFirst,BorderLayout.SOUTH);

        two.setLayout(new BorderLayout());
        two.add(secondScroll,BorderLayout.CENTER);
        two.add(totalSecond,BorderLayout.SOUTH);

        add(one);
        add(two);
    }
}
