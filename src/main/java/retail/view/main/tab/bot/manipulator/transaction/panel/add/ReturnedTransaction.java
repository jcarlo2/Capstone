package retail.view.main.tab.bot.manipulator.transaction.panel.add;

import lombok.Getter;
import retail.controller.database.TransactionDatabase;
import retail.customcomponent.jlist.CustomJList;
import retail.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;

import static retail.util.constant.Constant.COLOR_BLACK;

@Getter
public class ReturnedTransaction extends JPanel {
    private final TransactionDatabase transactionDatabase = new TransactionDatabase();
    private final JPanel wrapper = new JPanel();

    private final CustomJList list = new CustomJList();

    private final JPanel soldAndSoldTotal = new JPanel();
    private final JPanel discountPercentageAndTotal = new JPanel();
    private final JPanel addDeleteClear = new JPanel();

    private final CustomJTextField id = new CustomJTextField("Product ID",COLOR_BLACK);
    private final CustomJTextField price = new CustomJTextField("Price",COLOR_BLACK);
    private final CustomJTextField sold = new CustomJTextField("Sold By Piece",COLOR_BLACK);
    private final CustomJTextField soldTotal = new CustomJTextField("Total",COLOR_BLACK);
    private final CustomJTextField reportId = new CustomJTextField("Report ID",COLOR_BLACK);
    private final CustomJTextField discountPercentage = new CustomJTextField("Discount %",COLOR_BLACK);
    private final CustomJTextField discountTotal = new CustomJTextField("Total",COLOR_BLACK);

    private final JButton add = new JButton("Add");
    private final JButton clear = new JButton("Clear");
    private final JButton delete = new JButton("Delete");

    public ReturnedTransaction() {
        setLayout(new GridLayout(2,1));

        add();
        add(wrapper);
        add(list);
    }

    private void add() {
        wrapper.setLayout(new GridLayout(6,2));
        price.setEditable(false);

        discountPercentageAndTotal.setLayout(new GridLayout(1,2));
        discountPercentageAndTotal.add(discountPercentage);
        discountPercentageAndTotal.add(discountTotal);

        soldAndSoldTotal.setLayout(new GridLayout(1,2));
        soldAndSoldTotal.add(sold);
        soldAndSoldTotal.add(soldTotal);

        addDeleteClear.setLayout(new GridLayout(1,3));
        addDeleteClear.add(add);
        addDeleteClear.add(delete);
        addDeleteClear.add(clear);

        wrapper.add(id);
        wrapper.add(price);
        wrapper.add(soldAndSoldTotal);
        wrapper.add(discountPercentageAndTotal);
        wrapper.add(addDeleteClear);
        wrapper.add(reportId);
    }
}
