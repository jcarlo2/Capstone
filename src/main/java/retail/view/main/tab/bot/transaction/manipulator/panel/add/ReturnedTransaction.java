package retail.view.main.tab.bot.transaction.manipulator.panel.add;

import lombok.Getter;
import retail.shared.customcomponent.jlist.CustomJList;
import retail.shared.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

import static retail.shared.constant.Constant.COLOR_BLACK;

@Getter
public class ReturnedTransaction extends JPanel {
    private final CustomJList list = new CustomJList();
    private final JScrollPane scroll = new JScrollPane(list);
    private final CustomJTextField reportId = new CustomJTextField("Search Transaction",COLOR_BLACK);
    private final JPanel wrapper1 = new JPanel(new BorderLayout());
    private final JPanel wrapper2 = new JPanel(new GridLayout(2,1));
    private final JPanel wrapper3 = new JPanel(new GridLayout(1,3));
    private final JButton add = new JButton("Add");
    private final JButton delete = new JButton("Delete");
    private final JButton save = new JButton("Save");
    private final CustomJTextField newReportId = new CustomJTextField("New Transaction ID",COLOR_BLACK,false);

    public ReturnedTransaction() {
        setLayout(new BorderLayout());
        setWrapper1();
        setWrapper2();
        add(wrapper1,BorderLayout.CENTER);
        add(wrapper2,BorderLayout.NORTH);
    }

    private void setWrapper2() {
        wrapper3.add(add);
        wrapper3.add(delete);
        wrapper3.add(save);
        wrapper2.add(wrapper3);
        wrapper2.add(newReportId);
    }

    private void setWrapper1() {
        wrapper1.add(reportId,BorderLayout.NORTH);
        wrapper1.add(scroll,BorderLayout.CENTER);
    }
}
