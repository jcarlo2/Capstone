package retail.view.main.tab.bot.center.transaction.add;

import lombok.Getter;
import retail.customcomponent.jtable.CustomJTableTransaction;
import retail.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
public class ReturnedTransaction extends JPanel {
    private final JPanel wrapper1 = new JPanel();
    private final JPanel wrapper2 = new JPanel();

    private final CustomJTableTransaction topTable = new CustomJTableTransaction();
    private final JScrollPane topScroll = new JScrollPane(topTable);
    private final CustomJTextField topTotal = new CustomJTextField("Total Amount", Color.BLACK);

    private final CustomJTableTransaction botTable = new CustomJTableTransaction();
    private final JScrollPane botScroll = new JScrollPane(botTable);
    private final CustomJTextField botTotal = new CustomJTextField("Total Amount", Color.BLACK);

    public ReturnedTransaction() {
        setLayout(new GridLayout(2,1));
        setWrapper1();
        setWrapper2();

        add(wrapper1);
        add(wrapper2);
    }

    private void setWrapper1() {
        wrapper1.setLayout(new BorderLayout());
        wrapper1.add(topTotal,BorderLayout.NORTH);
        wrapper1.add(topScroll,BorderLayout.CENTER);
    }

    private void setWrapper2() {
        wrapper2.setLayout(new BorderLayout());
        wrapper2.add(botTotal,BorderLayout.NORTH);
        wrapper2.add(botScroll,BorderLayout.CENTER);
    }
}
