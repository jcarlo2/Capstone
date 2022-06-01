package retail.getter.transaction.add;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.shared.customcomponent.jlist.CustomJList;
import retail.shared.customcomponent.jtable.JTableTransaction;
import retail.shared.customcomponent.jtextfield.CustomJTextField;
import retail.view.main.tab.bot.BottomPanel;

import javax.swing.*;

@Getter
public class ReturnTransaction {
    // MANIPULATOR
    private final CustomJList list;
    private final CustomJTextField reportId;
    private final JButton addAll;
    private final JButton add;
    private final JButton delete;
    private final JButton deleteAll;
    private final JButton save;
    private final CustomJTextField newReportId;

    // CENTER
    private final JTableTransaction topTable;
    private final CustomJTextField topTotal;
    private final JTableTransaction botTable;
    private final CustomJTextField credit;
    private final CustomJTextField newTotal;

    public ReturnTransaction(@NotNull BottomPanel bottomPanel) {
        list = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransaction().getList();
        reportId = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransaction().getReportId();
        addAll = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransaction().getAddAll();
        add = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransaction().getAdd();
        delete = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransaction().getDelete();
        deleteAll = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransaction().getDeleteAll();
        save = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransaction().getSave();
        newReportId = bottomPanel.getManipulatorCard().getTransactionManipulator().getTransactionManipulatorCard().getAdd().getAddCard().getReturnedTransaction().getNewReportId();

        topTable = bottomPanel.getBottomMainCard().getTransactionCard().getAddCard().getReturnedTransaction().getTopTable();
        topTotal = bottomPanel.getBottomMainCard().getTransactionCard().getAddCard().getReturnedTransaction().getTopTotal();
        botTable = bottomPanel.getBottomMainCard().getTransactionCard().getAddCard().getReturnedTransaction().getBotTable();
        credit = bottomPanel.getBottomMainCard().getTransactionCard().getAddCard().getReturnedTransaction().getCredit();
        newTotal = bottomPanel.getBottomMainCard().getTransactionCard().getAddCard().getReturnedTransaction().getNewTotal();
    }
}
