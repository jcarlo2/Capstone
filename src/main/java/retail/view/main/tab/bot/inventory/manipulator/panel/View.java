package retail.view.main.tab.bot.inventory.manipulator.panel;

import lombok.Getter;
import lombok.Setter;
import retail.shared.customcomponent.jlist.CustomJList;
import retail.shared.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

import static retail.shared.constant.Constant.COLOR_BLACK;

@Getter
@Setter
public class View extends JPanel {
    private final CustomJList list = new CustomJList();
    private final JScrollPane scroll = new JScrollPane(list);
    private final CustomJTextField search = new CustomJTextField("Search",COLOR_BLACK);
    private final JButton delete = new JButton("Delete");
    private final JTextField message = new JTextField("List Of Inventory Report");

    private final JPanel center = new JPanel();

    public View() {
        setLayout(new BorderLayout());
        search.setText("");
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


