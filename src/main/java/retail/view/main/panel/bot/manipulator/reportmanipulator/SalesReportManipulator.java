package retail.view.main.panel.bot.manipulator.reportmanipulator;

import lombok.Getter;
import retail.view.main.panel.bot.manipulator.reportmanipulator.panel.SalesManipulatorCard;
import retail.view.main.panel.bot.manipulator.reportmanipulator.panel.AddViewDeletePanel;

import javax.swing.*;
import java.awt.*;

@Getter
public class SalesReportManipulator extends JPanel {
    private final AddViewDeletePanel addViewDeletePanel = new AddViewDeletePanel();
    private final SalesManipulatorCard salesManipulatorCard = new SalesManipulatorCard();

    public SalesReportManipulator() {
        setLayout(new BorderLayout());

        add(addViewDeletePanel,BorderLayout.NORTH);
        add(salesManipulatorCard,BorderLayout.CENTER);
    }
}
