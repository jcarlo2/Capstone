package retail.view.main.panel.bot.manipulator.inventorymanipulator.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class InventoryManipulatorCard extends JPanel {
    private final Add add = new Add();
    private final Delete delete = new Delete();
    private final View view = new View();
    private final ProductList productList = new ProductList();
    private final CardLayout cardLayout = new CardLayout();

    public InventoryManipulatorCard() {
        setLayout(cardLayout);

        add(add,"add");
        add(delete,"delete");
        add(view,"view");
        add(productList,"productList");
        cardLayout.show(this,"productList");
    }
}





























