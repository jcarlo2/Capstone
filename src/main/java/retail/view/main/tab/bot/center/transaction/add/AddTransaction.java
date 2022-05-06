package retail.view.main.tab.bot.center.transaction.add;


import lombok.Getter;
import retail.customcomponent.jtable.CustomJTableTransaction;
import retail.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddTransaction extends JPanel {
    private final CustomJTableTransaction centerTable = new CustomJTableTransaction();
    private final JScrollPane centerScroll = new JScrollPane(centerTable);
    private final CustomJTextField totalAmount = new CustomJTextField("Total Amount", Color.BLACK);

    public AddTransaction() {
        setLayout(new BorderLayout());
        totalAmount.setEditable(false);
        add(centerScroll,BorderLayout.CENTER);
        add(totalAmount,BorderLayout.NORTH);
    }
}





































