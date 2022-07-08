package retail.view.main.tab.bot.report.manipulator;

import lombok.Getter;
import retail.shared.custom.jcombobox.CustomComboBox;
import retail.shared.custom.jspinner.JSpinnerDate;
import retail.shared.custom.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

@Getter
public class ReportManipulator extends JPanel {
    private final CustomComboBox type = new CustomComboBox("Report Type", "Sales", "Delivery");

    private final JPanel wrapper1 = new JPanel(new BorderLayout());
    private final JPanel wrapper2 = new JPanel(new BorderLayout());
    private final CustomJTextField startDate = new CustomJTextField(false,"Start");
    private final CustomJTextField endDate = new CustomJTextField(false,"End");
    private final JSpinnerDate start = new JSpinnerDate();
    private final JSpinnerDate end = new JSpinnerDate();

    public ReportManipulator() {
        setLayout(new GridLayout(14,1));
        setSpinner();
        add(wrapper1);
        add(wrapper2);
    }

    private void setSpinner() {
        wrapper1.add(startDate,BorderLayout.WEST);
        wrapper1.add(start,BorderLayout.CENTER);
        wrapper2.add(endDate,BorderLayout.WEST);
        wrapper2.add(end,BorderLayout.CENTER);
    }
}
