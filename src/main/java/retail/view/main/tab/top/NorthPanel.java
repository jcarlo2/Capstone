package retail.view.main.tab.top;

import lombok.Getter;
import retail.shared.custom.jbutton.CustomJButton;

import javax.swing.*;
import java.awt.*;

@Getter
public class NorthPanel extends JPanel {
    private final CustomJButton transaction = new CustomJButton("Transaction");
    private final CustomJButton inventory = new CustomJButton("Inventory");
    private final CustomJButton sales = new CustomJButton("Sales Report");
    private final CustomJButton logFile = new CustomJButton("Log File");

    public NorthPanel() {
        setLayout(new GridLayout());
        add(transaction);
        add(inventory);
        add(sales);
        add(logFile);
        transaction.setEnabled(false);
    }
}
