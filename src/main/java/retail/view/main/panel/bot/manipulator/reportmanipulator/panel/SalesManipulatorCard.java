package retail.view.main.panel.bot.manipulator.reportmanipulator.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class SalesManipulatorCard extends JPanel {
    private final Add inventoryManipulatorAdd = new Add();
    private final Delete delete = new Delete();
    private final View view = new View();
    private final CardLayout cardLayout = new CardLayout();

    public SalesManipulatorCard() {
        setLayout(cardLayout);
        add("add", inventoryManipulatorAdd);
        add("delete", delete);
        add("view", view);
        cardLayout.show(this,"view");
    }
}
