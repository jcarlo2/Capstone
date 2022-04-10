package retail.view.main.panel.bot.manipulator.reportmanipulator.panel;

import lombok.Getter;
import retail.customcomponent.jlist.CustomJListSalesReport;
import retail.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

import static retail.constant.Constant.COLOR_WHITE;

@Getter
public class DeletePanel extends JPanel {
    private final CustomJListSalesReport list = new CustomJListSalesReport();
    private final JScrollPane scroll = new JScrollPane(list);
    private final CustomJTextField search = new CustomJTextField("Search",COLOR_WHITE);
    private final JButton delete = new JButton("Delete");
    private final JTextField message = new JTextField("List Of Sales Report");
    private final JTextField deletable = new JTextField("Deletable");
    private final JButton yesNo = new JButton();

    private final JPanel center = new JPanel();
    private final JPanel bottom = new JPanel();
    private final JPanel wrapper = new JPanel();

    public DeletePanel() {
        setLayout(new BorderLayout());
        deletable.setEditable(false);
        deletable.setHorizontalAlignment(SwingConstants.CENTER);
        message.setEditable(false);
        message.setHorizontalAlignment(SwingConstants.CENTER);

        center.setLayout(new BorderLayout());
        center.add(message,BorderLayout.NORTH);
        center.add(scroll,BorderLayout.CENTER);

        wrapper.setLayout(new GridLayout(1,2));
        wrapper.add(deletable);
        wrapper.add(yesNo);

        bottom.setLayout(new GridLayout(2,1));
        bottom.add(wrapper);
        bottom.add(delete);

        add(search,BorderLayout.NORTH);
        add(center,BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);
    }
}
