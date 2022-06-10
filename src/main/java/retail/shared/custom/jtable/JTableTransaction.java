package retail.shared.custom.jtable;

import org.jetbrains.annotations.NotNull;
import retail.shared.constant.ConstantDialog;
import retail.shared.custom.jtable.other.TableButtonRenderer;
import retail.shared.custom.jtable.other.TableJComboBox;
import retail.shared.custom.jtable.other.TransactionTableHLC;
import retail.shared.entity.TransactionItemDetail;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class JTableTransaction extends JTable implements TransactionTableHLC {
    private final DefaultTableModel model = new DefaultTableModel(0,10);

    public JTableTransaction(boolean isReturned, boolean isComboBoxEditable, boolean isBotTable) {
        setModel(model);
        setTable();
        setJComboBox(isComboBoxEditable);
        hideColumn(isReturned,isBotTable);
    }

    @Override
    public void addReportItem(@NotNull TransactionItemDetail item, String count) {
        if(!isDuplicate(item.getProductId())) {
            String[] data = new String[10];
            data[0] = "";
            data[1] = item.getProductId();
            data[2] = item.getPrice();
            data[3] = item.getSold();
            data[4] = item.getSoldTotal();
            data[5] = item.getDiscountPercentage();
            data[6] = item.getDiscountAmount();
            data[7] = item.getTotalAmount();
            data[8] = "--";
            data[9] = count;
            model.addRow(data);
        }
    }

    @Override
    public void removeRow(int row) {
        model.removeRow(row);
    }

    @Override
    public void addAllReportItem(@NotNull ArrayList<TransactionItemDetail> itemList) {
        model.setRowCount(0);
        int count = 0;
        for(TransactionItemDetail item : itemList) {
            String[] data = new String[10];
            data[0] = String.valueOf(++count);
            data[1] = item.getProductId();
            data[2] = item.getPrice();
            data[3] = item.getSold();
            data[4] = item.getSoldTotal();
            data[5] = item.getDiscountPercentage();
            data[6] = item.getDiscountAmount();
            data[7] = item.getTotalAmount();
            data[8] = "--";
            model.addRow(data);
        }
    }

    @Override
    public boolean isDuplicate(String id) {
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

    private void setJComboBox(boolean isComboBoxEditable) {
        if(isComboBoxEditable) {
            getColumnModel().getColumn(8).setCellRenderer(new TableButtonRenderer());
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
        getColumnModel().getColumn(0).setHeaderValue(TransactionTableHLC.columnName[0]);
        getColumnModel().getColumn(1).setHeaderValue(TransactionTableHLC.columnName[1]);
        getColumnModel().getColumn(2).setHeaderValue(TransactionTableHLC.columnName[2]);
        getColumnModel().getColumn(3).setHeaderValue(TransactionTableHLC.columnName[3]);
        getColumnModel().getColumn(4).setHeaderValue(TransactionTableHLC.columnName[4]);
        getColumnModel().getColumn(5).setHeaderValue(TransactionTableHLC.columnName[5]);
        getColumnModel().getColumn(6).setHeaderValue(TransactionTableHLC.columnName[6]);
        getColumnModel().getColumn(7).setHeaderValue(TransactionTableHLC.columnName[7]);
        getColumnModel().getColumn(8).setHeaderValue(TransactionTableHLC.columnName[8]);
        getColumnModel().getColumn(9).setHeaderValue(TransactionTableHLC.columnName[9]);
    }

    private void setColumnWidth() {
        getColumnModel().getColumn(0).setMinWidth(35);
        getColumnModel().getColumn(0).setMaxWidth(35);
    }
}
