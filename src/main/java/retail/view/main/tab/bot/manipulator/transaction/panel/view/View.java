package retail.view.main.tab.bot.manipulator.transaction.panel.view;

import lombok.Getter;
import retail.customcomponent.jlist.CustomJList;
import retail.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
public class View extends JPanel {
    private final CustomJList list = new CustomJList();
    private final JScrollPane scroll = new JScrollPane(list);
    private final JPanel wrapper1 = new JPanel();
    private final JPanel wrapper2 = new JPanel();
    private final CustomJTextField search = new CustomJTextField("Search", Color.BLACK);
    private final JButton delete = new JButton("Delete");
    private final JTextField message = new JTextField("List Of Transaction Report");

    public View() {
        setLayout(new BorderLayout());
        search.setText("");
        message.setEditable(false);
        message.setHorizontalAlignment(SwingConstants.CENTER);

        wrapper1.setLayout(new BorderLayout());
        wrapper1.add(search,BorderLayout.NORTH);
        wrapper1.add(message,BorderLayout.SOUTH);

        add(wrapper1,BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);
        add(delete,BorderLayout.SOUTH);
    }
}
