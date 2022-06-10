package retail.view.main.tab.bot.transaction.center.add;


import lombok.Getter;
import retail.shared.custom.jtable.JTableTransaction;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddTransactionCenter extends JPanel {
    private final JPanel wrapper = new JPanel(new GridBagLayout());
    private final JTableTransaction table = new JTableTransaction(false,false,false);
    private final JScrollPane centerScroll = new JScrollPane(table);
    private final CustomJTextField totalAmount = new CustomJTextField("Total Amount", Color.BLACK,15);

    public AddTransactionCenter() {
        setLayout(new BorderLayout());
        wrapper.add(totalAmount);
        add(wrapper,BorderLayout.NORTH);
        add(centerScroll,BorderLayout.CENTER);
    }

    public void setTotal(String input) {
        totalAmount.setText(input);
    }

    public String getTotalAmountText() {
        return totalAmount.getText();
    }

    
}





































