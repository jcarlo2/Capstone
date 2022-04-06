package retail.view.main.panel.leftpanel.inventorymanipulator;

import lombok.Getter;
import retail.view.main.panel.leftpanel.inventorymanipulator.panel.*;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryManipulator extends JPanel {
    private final AddDeleteUpdateDetailPanel addDeleteUpdateDetailPanel = new AddDeleteUpdateDetailPanel();
    private final InventoryCardPanel inventoryCardPanel = new InventoryCardPanel();
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);

    public InventoryManipulator() {
        setLayout(new BorderLayout());

        addDeleteUpdateDetailPanel.getAdd().setFont(sansSerif);
        addDeleteUpdateDetailPanel.getAdd().setEnabled(false);

        add(addDeleteUpdateDetailPanel,BorderLayout.NORTH);
        add(inventoryCardPanel,BorderLayout.CENTER);
    }

    public UpdatePanel getUpdatePanel() {
        return getInventoryCardPanel().getUpdatePanel();
    }

    public DetailPanel getDetailPanel() {
        return getInventoryCardPanel().getDetailPanel();
    }

    public DeletePanel getDeletePanel() {
        return getInventoryCardPanel().getDeletePanel();
    }

    public AddPanel getAddPanel() {
        return getInventoryCardPanel().getAddPanel();
    }
}
