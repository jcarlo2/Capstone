package retail.view.main.tab.bot.transaction.center.add;

import lombok.Getter;
import retail.shared.custom.jdialog.ReturnJDialog;

import javax.swing.*;
import java.awt.*;

@Getter
public class AddCenter extends JPanel {
    private final AddTransactionCenter addTransactionCenter = new AddTransactionCenter();
    private final ReturnedTransactionCenter returnedTransactionCenter = new ReturnedTransactionCenter();
    private final ReturnJDialog returnDialog = new ReturnJDialog();
    private final CardLayout card = new CardLayout();

    public AddCenter() {
        setLayout(card);
        add("add", addTransactionCenter);
        add("return", returnedTransactionCenter);
        card.show(this, "add");
    }
}
