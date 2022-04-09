package retail.view.main.panel.bot.manipulator.reportmanipulator.panel;

import retail.component.jtextfield.CustomJTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DeletePanel extends JPanel {
    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final JList<String> list = new JList<>(model);
    private final JScrollPane scroll = new JScrollPane(list);
    private final CustomJTextField search = new CustomJTextField("Search");
    private final JButton delete = new JButton("Delete");
    private final JTextField message = new JTextField("List Of Sales Report");

    private final JPanel center = new JPanel();
    private final JPanel bottom = new JPanel();

    public DeletePanel() {
        setLayout(new BorderLayout());
        message.setEditable(false);
        message.setHorizontalAlignment(SwingConstants.CENTER);

        center.setLayout(new BorderLayout());
        center.add(message,BorderLayout.NORTH);
        center.add(scroll,BorderLayout.CENTER);




        add(search,BorderLayout.NORTH);
        add(center,BorderLayout.CENTER);
        add(delete,BorderLayout.SOUTH);
    }
}
