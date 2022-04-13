package retail.view.main.panel.bot.main.transaction;


import lombok.Getter;
import retail.customcomponent.jtable.CustomJTableTransaction;

import javax.swing.*;
import java.awt.*;

@Getter
public class Add extends JPanel {
    private final CustomJTableTransaction centerTable = new CustomJTableTransaction();
    private final JScrollPane centerScroll = new JScrollPane(centerTable);
    private final JTextField totalAmount = new JTextField(15);

    public Add() {
        setLayout(new BorderLayout());


        add(centerScroll,BorderLayout.CENTER);
        add(totalAmount,BorderLayout.SOUTH);
    }

}





































