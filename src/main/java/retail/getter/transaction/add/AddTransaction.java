package retail.getter.transaction.add;


import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.shared.customcomponent.jcombobox.JComboBoxProduct;
import retail.shared.customcomponent.jtable.JTableProduct;
import retail.shared.customcomponent.jtable.JTableTransaction;
import retail.shared.customcomponent.jtextfield.CustomJTextField;
import retail.view.main.tab.bot.BottomPanel;

import javax.swing.*;

@Getter
public class AddTransaction {
    // MANIPULATOR
    private final JComboBoxProduct id;
    private final CustomJTextField price;
    private final CustomJTextField sold;
    private final CustomJTextField soldTotal;
    private final CustomJTextField reportId;
    private final CustomJTextField discountPercentage;
    private final CustomJTextField discountTotal;
    private final JButton add;
    private final JButton clear;
    private final JButton delete;
    private final JButton save;
    private final JButton generateId;

    // MAIN
    private final JTableTransaction table;
    private final CustomJTextField totalAmount;

    // OTHER
    private final JTableProduct productTable;

    public AddTransaction(@NotNull BottomPanel bottomPanel) {
        id = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransaction().getId();
        price = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransaction().getPrice();
        sold = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransaction().getSold();
        soldTotal = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransaction().getSoldTotal();
        reportId = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransaction().getReportId();
        discountPercentage = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransaction().getDiscountPercentage();
        discountTotal = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransaction().getDiscountTotal();
        add = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransaction().getAdd();
        clear = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransaction().getClear();
        delete = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransaction().getDelete();
        save = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransaction().getSave();
        generateId = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getAddTransaction().getGenerateId();

        table = bottomPanel.getBottomMainCard().getTransactionCard().getAddCard().getAddTransaction().getTable();
        totalAmount = bottomPanel.getBottomMainCard().getTransactionCard().getAddCard().getAddTransaction().getTotalAmount();
        productTable = bottomPanel.getBottomMainCard().getInventoryCard().getProduct().getTable();
    }
}
