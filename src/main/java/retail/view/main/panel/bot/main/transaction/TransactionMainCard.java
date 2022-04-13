package retail.view.main.panel.bot.main.transaction;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class TransactionMainCard extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final View view = new View();
    private final Add add = new Add();
    private final Delete delete = new Delete();

    public TransactionMainCard() {
        setLayout(cardLayout);
        add("compareReport", view);
        add("addReport", add);
        add("deleteReport", delete);
        cardLayout.show(this,"compareReport");
    }
}
