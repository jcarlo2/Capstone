package retail.view.main.panel.leftpanel.reportmanipulator;

import lombok.Getter;
import retail.view.main.panel.leftpanel.reportmanipulator.panel.ReportCardPanel;
import retail.view.main.panel.leftpanel.reportmanipulator.panel.AddViewDeletePanel;

import javax.swing.*;
import java.awt.*;

@Getter
public class SalesReportManipulator extends JPanel {
    private final AddViewDeletePanel addViewDeletePanel = new AddViewDeletePanel();
    private final ReportCardPanel reportCardPanel = new ReportCardPanel();

    public SalesReportManipulator() {
        setLayout(new BorderLayout());

        add(addViewDeletePanel,BorderLayout.NORTH);
        add(reportCardPanel,BorderLayout.CENTER);
    }
}
