package retail.view.main.panel.bot.manipulator.reportmanipulator.panel;

import lombok.Getter;
import retail.customcomponent.jlist.CustomJList;
import retail.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
public class View extends JPanel {
    private final CustomJList list = new CustomJList();
    private final JScrollPane scroll = new JScrollPane(list);
    private final JPanel wrapper = new JPanel();
    private final JPanel wrapper1 = new JPanel();
    private final CustomJTextField search = new CustomJTextField("Search", Color.BLACK);
    private final JButton firstTable = new JButton("First");
    private final JButton secondTable = new JButton("Second");
    private final JTextField message = new JTextField("List Of Sales Report");

    public View() {
        setLayout(new BorderLayout());
        search.setText("");
        wrapper.setLayout(new GridLayout(1,2));
        wrapper.add(firstTable);
        wrapper.add(secondTable);
        message.setEditable(false);
        message.setHorizontalAlignment(SwingConstants.CENTER);

        wrapper1.setLayout(new BorderLayout());
        wrapper1.add(search,BorderLayout.NORTH);
        wrapper1.add(message,BorderLayout.CENTER);

        add(wrapper1,BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);
        add(wrapper,BorderLayout.SOUTH);
    }
}
