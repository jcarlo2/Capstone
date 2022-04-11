package retail.view.main.panel.bot.manipulator.inventorymanipulator;

import lombok.Getter;
import retail.view.main.panel.bot.manipulator.inventorymanipulator.panel.*;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryManipulator extends JPanel {
    private final AddViewDelete addViewDelete = new AddViewDelete();
    private final InventoryManipulatorCard inventoryManipulatorCard = new InventoryManipulatorCard();
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);

    public InventoryManipulator() {
        setLayout(new BorderLayout());

        addViewDelete.getProduct().setFont(sansSerif);
        addViewDelete.getProduct().setEnabled(false);

        add(addViewDelete,BorderLayout.NORTH);
        add(inventoryManipulatorCard,BorderLayout.CENTER);
    }

    public View getView() {
        return getInventoryManipulatorCard().getView();
    }

    public ProductList getProduct() {
        return getInventoryManipulatorCard().getProductList();
    }

    public Delete getDeletePanel() {
        return getInventoryManipulatorCard().getDelete();
    }

    public Add getAddPanel() {
        return getInventoryManipulatorCard().getAdd();
    }
}
