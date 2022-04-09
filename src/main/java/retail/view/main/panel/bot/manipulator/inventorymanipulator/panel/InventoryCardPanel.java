package retail.view.main.panel.bot.manipulator.inventorymanipulator.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryCardPanel extends JPanel {
    private final AddPanel addPanel = new AddPanel();
    private final DeletePanel deletePanel = new DeletePanel();
    private final UpdatePanel updatePanel = new UpdatePanel();
    private final DetailPanel detailPanel = new DetailPanel();
    private final CardLayout cardLayout = new CardLayout();

    public InventoryCardPanel() {
        setLayout(cardLayout);

        add(addPanel,"add");
        add(deletePanel,"delete");
        add(updatePanel,"update");
        add(detailPanel,"detail");
        cardLayout.show(this,"add");
    }
}





























