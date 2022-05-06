package retail.customcomponent.jtable;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.util.constant.ConstantDialog;
import retail.model.TransactionReportItem;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class CustomJTableTransaction extends JTable {
    private final DefaultTableModel model = new DefaultTableModel(0,8);

    public CustomJTableTransaction() {
        setModel(model);
        setUpJTable();
    }

    public void addRow(@NotNull TransactionReportItem item) {
        if(!isDuplicate(item.getProductId())) {
            String[] data = new String[8];
            data[0] = "";
            data[1] = item.getProductId();
            data[2] = item.getPrice().toString();
            data[3] = item.getSold().toString();
            data[4] = item.getSoldTotal().toString();
            data[5] = item.getDiscountPercentage().toString();
            data[6] = item.getDiscountAmount().toString();
            data[7] = item.getTotalAmount().toString();
            model.addRow(data);
        }
    }

    private boolean isDuplicate(String id) {
        int ROW = getRowCount();
        if(ROW == 0) return false;
        for(int i=0;i<ROW;i++) {
            if(id.equals(getValueAt(i,1))) {
                ConstantDialog.DUPLICATE_ID();
                return true;
            }
        }
        return false;
    }

    public void addAllItems(@NotNull ArrayList<TransactionReportItem> itemList) {
        model.setRowCount(0);
        int count = 0;
        for(TransactionReportItem item : itemList) {
            String[] data = new String[8];
            data[0] = String.valueOf(++count);
            data[1] = item.getProductId();
            data[2] = item.getPrice().toString();
            data[3] = item.getSold().toString();
            data[4] = item.getSoldTotal().toString();
            data[5] = item.getDiscountPercentage().toString();
            data[6] = item.getDiscountAmount().toString();
            data[7] = item.getTotalAmount().toString();
            model.addRow(data);
        }
    }

    private void setUpJTable() {
        setFont(new Font("SansSerif", Font.BOLD, 15));
        setShowHorizontalLines(true);
        setShowVerticalLines(true);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        setDefaultEditor(Object.class,null); // DISABLE EDIT TABLE LIKE setEditable()

        addColumnName();
        setJTableColumnWidth();
        centerJTableText();
    }

    private void centerJTableText() {
        int COLUMN_NUMBER = getColumnModel().getColumnCount();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<COLUMN_NUMBER;i++) {
            getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private void addColumnName() {
        String[] columnName = {"No.", "Product ID","Price","Sold Pieces"
                ,"Sold Total","Discount %%", "Discount Total", "Total Amount"};
        getColumnModel().getColumn(0).setHeaderValue(columnName[0]);
        getColumnModel().getColumn(1).setHeaderValue(columnName[1]);
        getColumnModel().getColumn(2).setHeaderValue(columnName[2]);
        getColumnModel().getColumn(3).setHeaderValue(columnName[3]);
        getColumnModel().getColumn(4).setHeaderValue(columnName[4]);
        getColumnModel().getColumn(5).setHeaderValue(columnName[5]);
        getColumnModel().getColumn(6).setHeaderValue(columnName[6]);
        getColumnModel().getColumn(7).setHeaderValue(columnName[7]);
    }

    private void setJTableColumnWidth() {
        getColumnModel().getColumn(0).setMinWidth(35);
        getColumnModel().getColumn(0).setMaxWidth(35);
    }
}
