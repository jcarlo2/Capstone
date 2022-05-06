package retail.view.main.tab.bot.center.transaction;

import lombok.Getter;
import retail.customcomponent.jtable.CustomJTableTransaction;

import javax.swing.*;
import java.awt.*;

@Getter
public class ViewMain extends JPanel {
    private final JPanel wrapper1 = new JPanel();
    private final JPanel wrapper2 = new JPanel();

    private final JPanel one = new JPanel();
    private final CustomJTableTransaction table = new CustomJTableTransaction();
    private final JScrollPane scroll = new JScrollPane(table);
    private final JTextField total = new JTextField(15);
    private final JTextField id = new JTextField(15);

    public ViewMain() {
        setLayout(new GridLayout(1,1));

        jTextFieldProperties();
        setWrapper1();

        one.setLayout(new BorderLayout());
        one.add(scroll,BorderLayout.CENTER);
        one.add(wrapper1,BorderLayout.SOUTH);

        add(one);
    }

    private void jTextFieldProperties() {
        total.setHorizontalAlignment(SwingConstants.CENTER);
        id.setHorizontalAlignment(SwingConstants.CENTER);
        total.setEditable(false);
        id.setEditable(false);
    }

    private void setWrapper1() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        wrapper1.setLayout(new GridBagLayout());
        wrapper1.add(id,constraints);
        wrapper1.add(total,constraints);
    }
}
