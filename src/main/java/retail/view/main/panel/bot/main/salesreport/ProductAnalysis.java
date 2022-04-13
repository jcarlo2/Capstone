package retail.view.main.panel.bot.main.salesreport;

import javax.swing.*;

public class ProductAnalysis extends JPanel {

    public ProductAnalysis() {
        JTextArea text = new JTextArea(35,50);
        text.setText("Product Analysis");
        add(text);

        setFocusable(false);
        setVisible(true);
    }
}
