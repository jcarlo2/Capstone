package retail.shared.custom.jtable;

import org.jetbrains.annotations.NotNull;
import retail.shared.custom.jtable.other.TableAbstract;
import retail.shared.custom.jtable.other.TableJComboBox;
import retail.shared.entity.TransactionItemDetail;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class JTableTransaction extends TableAbstract {
    public JTableTransaction(TableModel model, boolean isReturned, boolean isComboBoxEditable, boolean isBotTable) {
        super(model, new String[] {"No.",
                              "Product ID",
                              "Price",
                              "Sold Pieces",
                              "Sold Total",
                              "Discount %%",
                              "Discount Total",
                              "Total Amount",
                              "Reason",
                              "Return"});
        setJComboBox(isComboBoxEditable);
        hideColumn(isReturned,isBotTable);
        centerTableText();
    }

    public void addItemWithCount(@NotNull TransactionItemDetail item, String count) {
        if(isDuplicate(item.getProductId())) {
            String[] data = new String[10];
            transactionItemToArray(item, data);
            data[9] = count;
            model.addRow(data);
        }
    }

    public void addItemWithReason(@NotNull TransactionItemDetail item, String reason) {
        if(isDuplicate(item.getProductId())) {
            String[] data = new String[10];
            transactionItemToArray(item, data);
            data[8] = reason;
            model.addRow(data);
        }
    }

    public void addAllItem(@NotNull ArrayList<TransactionItemDetail> itemList) {
        model.setRowCount(0);
        for(TransactionItemDetail item : itemList) {
            String[] data = new String[10];
            transactionItemToArray(item, data);
            model.addRow(data);
        }
    }

    private void transactionItemToArray(@NotNull TransactionItemDetail item, String @NotNull [] data) {
        data[0] = "";
        data[1] = item.getProductId();
        data[2] = item.getPrice();
        data[3] = item.getSold();
        data[4] = item.getSoldTotal();
        data[5] = item.getDiscountPercentage();
        data[6] = item.getDiscountAmount();
        data[7] = item.getTotalAmount();
        data[8] = "--";
        data[9] = "0";
    }

    private void setJComboBox(boolean isComboBoxEditable) {
        if(isComboBoxEditable) {
//            getColumnModel().getColumn(8).setCellRenderer(new ComboBoxRenderer());
            getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new TableJComboBox()));
        }
    }

    private void hideColumn(boolean isReturned, Boolean isBotTable) {
        if(!isBotTable) {
            getColumnModel().removeColumn(getColumnModel().getColumn(9));
        }
        if(!isReturned) {
            getColumnModel().removeColumn(getColumnModel().getColumn(8));
        }
    }



    private void centerTableText() {
        int COLUMN_NUMBER = getColumnModel().getColumnCount();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<COLUMN_NUMBER;i++) {
            getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
}
