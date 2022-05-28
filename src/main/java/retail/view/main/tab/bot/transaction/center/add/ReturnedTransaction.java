package retail.view.main.tab.bot.transaction.center.add;

import lombok.Getter;
import retail.shared.customcomponent.jtable.JTableTransaction;
import retail.shared.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
public class ReturnedTransaction extends JPanel {
    private final JPanel wrapper1 = new JPanel(new BorderLayout());
    private final JPanel wrapper2 = new JPanel(new BorderLayout());
    private final JPanel wrapper3 = new JPanel();
    private final JPanel wrapper4 = new JPanel();

    private final JTableTransaction topTable = new JTableTransaction(true,true,false);
    private final JScrollPane topScroll = new JScrollPane(topTable);
    private final CustomJTextField topTotal = new CustomJTextField("Total Amount", Color.BLACK,15);

    private final JTableTransaction botTable = new JTableTransaction(false,false,true);
    private final JScrollPane botScroll = new JScrollPane(botTable);

    private final CustomJTextField credit = new CustomJTextField("Credit", Color.BLACK,15);
    private final CustomJTextField newTotal = new CustomJTextField("Total Amount", Color.BLACK,15);

    public ReturnedTransaction() {
        setLayout(new GridLayout(2,1));
        setWrapper1();
        setWrapper2();
        add(wrapper1);
        add(wrapper2);
    }

    private void setWrapper1() {
        wrapper3.add(topTotal);
        wrapper1.add(wrapper3,BorderLayout.NORTH);
        wrapper1.add(topScroll,BorderLayout.CENTER);
    }

    private void setWrapper2() {
        wrapper4.add(credit);
        wrapper4.add(newTotal);
        wrapper2.add(wrapper4,BorderLayout.NORTH);
        wrapper2.add(botScroll,BorderLayout.CENTER);
    }
}