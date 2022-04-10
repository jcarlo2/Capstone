package retail.view.main.panel.bot.manipulator.reportmanipulator.panel;

import lombok.Getter;
import retail.customcomponent.jlist.CustomJListSalesReport;

import javax.swing.*;
import java.awt.*;

@Getter
public class ViewPanel extends JPanel {


    private final CustomJListSalesReport viewReport = new CustomJListSalesReport();
    private final JScrollPane scroll = new JScrollPane(viewReport);
    private final JPanel buttonPanel = new JPanel();
    private final JButton firstTable = new JButton("First");
    private final JButton secondTable = new JButton("Second");
    private final JTextField message = new JTextField("List Of Sales Report");

    public ViewPanel() {
        setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(firstTable);
        buttonPanel.add(secondTable);
        message.setEditable(false);
        message.setHorizontalAlignment(SwingConstants.CENTER);

        add(message,BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
    }
}
