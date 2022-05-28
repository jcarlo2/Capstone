package retail.view.main.tab.bot.transaction.center.add;


import lombok.Getter;
import retail.shared.customcomponent.jtable.JTableTransaction;
import retail.shared.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddTransaction extends JPanel {
    private final JTableTransaction table = new JTableTransaction(false,false,false);
    private final JScrollPane centerScroll = new JScrollPane(table);
    private final CustomJTextField totalAmount = new CustomJTextField("Total Amount", Color.BLACK,false);

    public AddTransaction() {
        setLayout(new BorderLayout());
        add(centerScroll,BorderLayout.CENTER);
        add(totalAmount,BorderLayout.NORTH);
    }
}





































