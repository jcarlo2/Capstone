package retail.view.main.panel.bot.manipulator.inventory.panel;

import lombok.Getter;
import lombok.Setter;
import retail.customcomponent.jlist.CustomJList;
import retail.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

import static retail.util.constant.Constant.COLOR_BLACK;

@Getter
@Setter
public class View extends JPanel {
    private final CustomJList list = new CustomJList();
    private final JScrollPane scroll = new JScrollPane(list);
    private final CustomJTextField search = new CustomJTextField("Search",COLOR_BLACK);
    private final JButton delete = new JButton("Delete");
    private final JTextField message = new JTextField("List Of Inventory Report");
    private final JTextField deletable = new JTextField("Deletable");
    private final JButton deletableStatus = new JButton();

    private final JPanel center = new JPanel();
    private final JPanel bottom = new JPanel();
    private final JPanel wrapper = new JPanel();

    public View() {
        setLayout(new BorderLayout());
        search.setText("");
        deletable.setEditable(false);
        deletable.setHorizontalAlignment(SwingConstants.CENTER);
        message.setEditable(false);
        message.setHorizontalAlignment(SwingConstants.CENTER);

        center.setLayout(new BorderLayout());
        center.add(message,BorderLayout.NORTH);
        center.add(scroll,BorderLayout.CENTER);

        wrapper.setLayout(new GridLayout(1,2));
        wrapper.add(deletable);
        wrapper.add(deletableStatus);

        bottom.setLayout(new GridLayout(2,1));
        bottom.add(wrapper);
        bottom.add(delete);

        add(search,BorderLayout.NORTH);
        add(center,BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);
    }
}


