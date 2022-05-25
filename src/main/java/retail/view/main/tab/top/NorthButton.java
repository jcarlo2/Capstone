package retail.view.main.tab.top;

import lombok.Getter;
import retail.shared.customcomponent.CustomJButton;

import javax.swing.*;
import java.awt.*;

@Getter
public class NorthButton extends JPanel {
    private final CustomJButton transaction = new CustomJButton("Transaction",20);
    private final CustomJButton inventory = new CustomJButton("Inventory",20);
    private final CustomJButton productAnalysis = new CustomJButton("Sales Report",20);
    private final CustomJButton logFile = new CustomJButton("Log File",20);

    public NorthButton() {
        setLayout(new GridLayout());
        add(transaction);
        add(inventory);
        add(productAnalysis);
        add(logFile);
        transaction.setEnabled(false);
    }
}
