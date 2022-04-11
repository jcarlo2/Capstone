package retail.view.main.panel.bot.main.sales;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class SalesMainCard extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final SalesMainView salesMainView = new SalesMainView();
    private final SalesMainAdd salesMainAdd = new SalesMainAdd();
    private final SalesMainDelete salesMainDelete = new SalesMainDelete();

    public SalesMainCard() {
        setLayout(cardLayout);
        add("compareReport", salesMainView);
        add("addReport", salesMainAdd);
        add("deleteReport", salesMainDelete);
        cardLayout.show(this,"compareReport");
    }
}
