package retail.view.main.tab.top;

import lombok.Getter;
import retail.customcomponent.CustomJButton;

import javax.swing.*;
import java.awt.*;

@Getter
public class NorthButton extends JPanel {
    CustomJButton transaction = new CustomJButton("Transaction");
    CustomJButton inventory = new CustomJButton("Inventory");
    CustomJButton productAnalysis = new CustomJButton("Sales Report");
    CustomJButton logFile = new CustomJButton("Log File");

    public NorthButton() {
        setLayout(new GridLayout());
        add(transaction);
        add(inventory);
        add(productAnalysis);
        add(logFile);
        transaction.setEnabled(false);
    }
}
