package retail.view.main.panel.top;

import lombok.Getter;
import retail.customcomponent.CustomJButton;

import javax.swing.*;
import java.awt.*;

@Getter
public class NorthButton extends JPanel {
    CustomJButton salesReport = new CustomJButton("Sales Report");
    CustomJButton inventory = new CustomJButton("Inventory");
    CustomJButton importExport = new CustomJButton("Import/Export");
    CustomJButton productAnalysis = new CustomJButton("Analysis");
    CustomJButton logFile = new CustomJButton("Log File");

    public NorthButton() {
        setLayout(new GridLayout());
        add(salesReport);
        add(inventory);
        add(productAnalysis);
        add(importExport);
        add(logFile);
        salesReport.setEnabled(false);
    }
}
