package retail.view.main.tab.bot.center.salesreport;

import javax.swing.*;
import java.awt.*;

public class ProductAnalysis extends JPanel {
    public ProductAnalysis() {
        JTextArea text = new JTextArea(35,50);
        text.setText("Product Analysis");
        add(text);

        JTextField field = new JTextField(15);
        field.setLayout(new BorderLayout());
        JPopupMenu pop = new JPopupMenu();
        pop.add("STRING");
        field.add(pop,BorderLayout.CENTER);
        add(field);
        setFocusable(false);
        setVisible(true);
    }
}
