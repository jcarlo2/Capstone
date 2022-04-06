package retail.view.main.panel.leftpanel.reportmanipulator.panel;

import javax.swing.*;
import java.awt.*;

public class DeletePanel extends JPanel {
    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final JList<String> list = new JList<>(model);
    private final JScrollPane scroll = new JScrollPane(list);
    private final JTextField search = new JTextField("Search",15);
    private final JButton delete = new JButton("Delete");

    public DeletePanel() {
        setLayout(new BorderLayout());
        add(scroll,BorderLayout.CENTER);
        add(search,BorderLayout.NORTH);
        add(delete,BorderLayout.SOUTH);
    }
}
