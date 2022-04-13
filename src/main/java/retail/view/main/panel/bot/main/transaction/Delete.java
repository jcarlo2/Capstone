package retail.view.main.panel.bot.main.transaction;

import lombok.Getter;
import retail.customcomponent.jtable.CustomJTableTransaction;

import javax.swing.*;
import java.awt.*;

@Getter
public class Delete extends JPanel{
    private final CustomJTableTransaction table = new CustomJTableTransaction();
    private final JScrollPane scroll = new JScrollPane(table);

    public Delete() {
        setLayout(new BorderLayout());
        add(scroll,BorderLayout.CENTER);
    }
}
