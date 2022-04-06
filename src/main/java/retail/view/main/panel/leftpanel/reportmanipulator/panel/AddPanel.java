package retail.view.main.panel.leftpanel.reportmanipulator.panel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class AddPanel extends JPanel {
    private final JPanel addClear = new JPanel();
    private final DefaultComboBoxModel model = new DefaultComboBoxModel();
    private final JComboBox<String> id = new JComboBox<>(model);
    private final JTextField price = new JTextField(15);
    private final JTextField sold = new JTextField(15);
    private final JTextField soldTotal = new JTextField(15);
    private final JTextField damage = new JTextField(15);
    private final JTextField damageTotal = new JTextField(15);
    private final JTextField total = new JTextField(15);
    private final JTextField reportId = new JTextField(15);
    private final JButton add = new JButton("Add");
    private final JButton clear = new JButton("Clear");
    private final JButton delete = new JButton("Delete");

    public AddPanel() {
        setAddPanel();
    }


    public void setAddPanel() {
        setLayout(new GridLayout(12,1));

        id.setEditable(true);
        id.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Product ID",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(id);

        price.setEditable(false);
        price.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Price",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(price);

        sold.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Sold By Pieces",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(sold);

        soldTotal.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Sold Total",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(soldTotal);

        damage.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Expired/Damaged Pieces",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(damage);

        damageTotal.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Expired/Damaged Total",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(damageTotal);

        total.setEditable(false);
        total.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Total Amount",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(total);

        addClear.setLayout(new GridLayout(1,3));
        addClear.add(add);
        addClear.add(delete);
        addClear.add(clear);
        add(addClear);

        reportId.setEditable(false);
        reportId.setBorder(BorderFactory.createTitledBorder
                (new LineBorder(Color.WHITE),"Report ID",
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
        add(reportId);
    }
}
