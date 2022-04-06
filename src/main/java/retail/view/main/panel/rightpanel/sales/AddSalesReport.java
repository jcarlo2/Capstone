package retail.view.main.panel.rightpanel.sales;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddSalesReport extends JPanel {
    private final JPanel west = new JPanel();
    private final JPanel center = new JPanel();
    private final JPanel south = new JPanel();
    private final String[] columnName = {"No.","Product ID", "Price","Sold","Sold Total","Damaged/Expired","Total Damaged/Expired","Total"};

    private final JPanel addClearPanel = new JPanel();
    private final JPanel addReportPanel = new JPanel();
    private final JButton add = new JButton("Add");
    private final JButton clear = new JButton("Clear");
    private final JTextField id = new JTextField(15);
    private final JTextField price = new JTextField(15);
    private final JTextField sold = new JTextField(15);
    private final JTextField soldTotal = new JTextField(15);
    private final JTextField damage = new JTextField(15);
    private final JTextField damageTotal = new JTextField(15);
    private final JTextField total = new JTextField(15);

    private final DefaultTableModel centerModel = new DefaultTableModel(columnName,0);
    private final JTable centerTable = new JTable(centerModel);
    private final JScrollPane centerScroll = new JScrollPane(centerTable);

    private final JTextField overall = new JTextField(15);

    public AddSalesReport() {
        setLayout(new BorderLayout());

        setWest();
        setCenter();
        setSouth();


        add(west,BorderLayout.WEST);
        add(center,BorderLayout.CENTER);
        add(south,BorderLayout.SOUTH);
    }

    public void setWest() {
        west.setLayout(new BorderLayout());
        addReportPanel.setLayout(new GridLayout(12,1));

        id.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Product ID",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        addReportPanel.add(id);

        price.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Product ID",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        addReportPanel.add(price);

        sold.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Product ID",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        addReportPanel.add(sold);

        soldTotal.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Product ID",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        addReportPanel.add(soldTotal);

        damage.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Product ID",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        addReportPanel.add(damage);

        damageTotal.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Product ID",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        addReportPanel.add(damageTotal);

        total.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Product ID",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        addReportPanel.add(total);

        west.add(addClearPanel,BorderLayout.NORTH);
        west.add(addReportPanel,BorderLayout.CENTER);
    }

    public void setCenter() {
        center.setLayout(new BorderLayout());
        center.add(centerScroll,BorderLayout.CENTER);
    }

    public void setSouth() {
        south.setLayout(new BorderLayout());
        south.add(overall);
    }

}





































