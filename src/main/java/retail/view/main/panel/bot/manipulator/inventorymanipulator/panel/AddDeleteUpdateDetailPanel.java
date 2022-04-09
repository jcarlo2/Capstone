package retail.view.main.panel.bot.manipulator.inventorymanipulator.panel;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddDeleteUpdateDetailPanel extends JPanel {
    private final JPanel top = new JPanel();
    private final JPanel bottom = new JPanel();
    private final JButton add = new JButton("Add");
    private final JButton delete = new JButton("Delete");
    private final JButton update = new JButton("Update");
    private final JButton detail = new JButton("Details");
    private Boolean isDetail = false;
    private Boolean isUpdate = false;

    public AddDeleteUpdateDetailPanel() {
        setLayout(new GridLayout(2,1));
        top.setLayout(new GridLayout(1,1));
        bottom.setLayout(new GridLayout(1,3));

        top.add(detail);
        bottom.add(add);
        bottom.add(delete);
        bottom.add(update);

        add(top);
        add(bottom);
    }
}



















































