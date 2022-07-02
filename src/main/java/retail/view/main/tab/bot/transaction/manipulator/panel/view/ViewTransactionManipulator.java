package retail.view.main.tab.bot.transaction.manipulator.panel.view;

import lombok.Getter;
import retail.shared.custom.jcombobox.CustomComboBox;
import retail.shared.custom.jspinner.JSpinnerDate;
import retail.shared.custom.jlist.CustomJList;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
public class ViewTransactionManipulator extends JPanel {
    private final JPanel wrapper1 = new JPanel(new BorderLayout());
    private final JPanel wrapper2 = new JPanel(new GridLayout(4,1));
    private final JPanel wrapper3 = new JPanel(new BorderLayout());
    private final JPanel wrapper4 = new JPanel(new BorderLayout());
    private final JPanel wrapper5 = new JPanel(new GridLayout(1,2));

    private final CustomJList list = new CustomJList();
    private final JScrollPane scroll = new JScrollPane(list);
    private final CustomJTextField search = new CustomJTextField("Search", Color.BLACK);
    private final JButton delete = new JButton("Delete");
    private final CustomJTextField message = new CustomJTextField(false,"List Of Transaction Report");
    private final CustomJTextField startDate = new CustomJTextField(false,"Start");
    private final CustomJTextField endDate = new CustomJTextField(false,"End");
    private final JSpinnerDate start = new JSpinnerDate();
    private final JSpinnerDate end = new JSpinnerDate();
    private final CustomComboBox searchType = new CustomComboBox("Auto","Date","Search Type");
    private final CustomComboBox validType = new CustomComboBox("All","Valid Only","Valid Type");


    public ViewTransactionManipulator() {
        setLayout(new BorderLayout());
        search.setText("");
        validType.setSelectedIndex(1);

        wrapper3.add(startDate,BorderLayout.WEST);
        wrapper3.add(start,BorderLayout.CENTER);
        wrapper4.add(endDate,BorderLayout.WEST);
        wrapper4.add(end,BorderLayout.CENTER);

        wrapper5.add(searchType);
        wrapper5.add(validType);

        wrapper2.add(wrapper3);
        wrapper2.add(wrapper4);
        wrapper2.add(wrapper5);
        wrapper2.add(delete);

        wrapper1.add(search,BorderLayout.NORTH);
        wrapper1.add(message,BorderLayout.SOUTH);

        add(wrapper1,BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);
        add(wrapper2,BorderLayout.SOUTH);
    }
}