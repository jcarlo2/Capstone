package retail.view.main.panel.rightpanel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Getter
public class NorthButton extends JPanel {
    JButton salesReport = new JButton("Sales Report");
    JButton inventory = new JButton("Inventory");
    JButton importExport = new JButton("Import/Export");
    JButton productAnalysis = new JButton("Product Analysis");
    JButton logFile = new JButton("Log File");

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
