package retail.shared.customcomponent.jtable;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.model.TransactionReportItem;
import retail.shared.constant.ConstantDialog;
import retail.shared.customcomponent.jtable.other.TableButtonRenderer;
import retail.shared.customcomponent.jtable.other.TableJComboBox;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class JTableTransaction extends JTable {
    private final DefaultTableModel model = new DefaultTableModel(0,10);

    public JTableTransaction(boolean isReturned, boolean isComboBoxEditable, boolean isTopTable) {
        setModel(model);
        setTable();
        setJComboBox(isComboBoxEditable);
        hideColumn(isReturned);
        setSpecificCellEditable(isTopTable);
    }

    private void hideColumn(boolean isReturned) {
        if(!isReturned) {
            getColumnModel().removeColumn(getColumnModel().getColumn(9));
            getColumnModel().removeColumn(getColumnModel().getColumn(8));
        }
    }

    private void setJComboBox(boolean isComboBoxEditable) {
       if(isComboBoxEditable) {
           getColumnModel().getColumn(8).setCellRenderer(new TableButtonRenderer());
           getColumnModel().getColumn(9).setCellRenderer(new TableButtonRenderer());
           getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new TableJComboBox(false)));
           getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(new TableJComboBox(true)));
       }
    }

    public void addReportItem(@NotNull TransactionReportItem item, String reason, String action) {
        if(!isDuplicate(item.getProductId())) {
            String[] data = new String[10];
            data[0] = "";
            data[1] = item.getProductId();
            data[2] = item.getPrice().toString();
            data[3] = item.getSold().toString();
            data[4] = item.getSoldTotal().toString();
            data[5] = item.getDiscountPercentage().toString();
            data[6] = item.getDiscountAmount().toString();
            data[7] = item.getTotalAmount().toString();
            data[8] = reason;
            data[9] = action;

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

    public void addAllReportItem(@NotNull ArrayList<TransactionReportItem> itemList) {
        model.setRowCount(0);
        int count = 0;
        for(TransactionReportItem item : itemList) {
            String[] data = new String[10];
            data[0] = String.valueOf(++count);
            data[1] = item.getProductId();
            data[2] = item.getPrice().toString();
            data[3] = item.getSold().toString();
            data[4] = item.getSoldTotal().toString();
            data[5] = item.getDiscountPercentage().toString();
            data[6] = item.getDiscountAmount().toString();
            data[7] = item.getTotalAmount().toString();
            data[8] = "--";
            data[9] = "--";
            model.addRow(data);
        }
    }

    private void setTable() {
        setFont(new Font("SansSerif", Font.BOLD, 15));
        setShowHorizontalLines(true);
        setShowVerticalLines(false);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        setDefaultEditor(Object.class,null); // DISABLE EDIT TABLE LIKE setEditable()
        getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addColumnName();
        setColumnWidth();
        centerTableText();
    }

    private void centerTableText() {
        int COLUMN_NUMBER = getColumnModel().getColumnCount();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<COLUMN_NUMBER;i++) {
            getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private void addColumnName() {
        String[] columnName = {"No.", "Product ID","Price","Sold Pieces",
                               "Sold Total","Discount %%", "Discount Total",
                               "Total Amount", "Reason", "Action"};
        getColumnModel().getColumn(0).setHeaderValue(columnName[0]);
        getColumnModel().getColumn(1).setHeaderValue(columnName[1]);
        getColumnModel().getColumn(2).setHeaderValue(columnName[2]);
        getColumnModel().getColumn(3).setHeaderValue(columnName[3]);
        getColumnModel().getColumn(4).setHeaderValue(columnName[4]);
        getColumnModel().getColumn(5).setHeaderValue(columnName[5]);
        getColumnModel().getColumn(6).setHeaderValue(columnName[6]);
        getColumnModel().getColumn(7).setHeaderValue(columnName[7]);
        getColumnModel().getColumn(8).setHeaderValue(columnName[8]);
        getColumnModel().getColumn(9).setHeaderValue(columnName[9]);
    }

    private void setColumnWidth() {
        getColumnModel().getColumn(0).setMinWidth(35);
        getColumnModel().getColumn(0).setMaxWidth(35);
    }

    private void setSpecificCellEditable(boolean isTopTable) {
        if(isTopTable) {
            getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JTextField()));
            getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JTextField()));
            getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new JTextField()));
        }
    }
}
