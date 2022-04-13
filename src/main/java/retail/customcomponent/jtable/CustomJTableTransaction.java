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

    public void addRow(@NotNull TransactionReportItem report) {
        if(!isDuplicate(report.getProductId())) {
            String[] data = new String[8];
            data[0] = "";
            data[1] = report.getProductId();
            data[2] = String.valueOf(report.getPrice());
            data[3] = String.valueOf(report.getSold());
            data[4] = String.valueOf(report.getSoldTotal());
            data[5] = String.valueOf(report.getExpDamaged());
            data[6] = String.valueOf(report.getExpDamagedTotal());
            data[7] = String.valueOf(report.getTotalAmount());
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

    public void addItem(@NotNull ArrayList<TransactionReportItem> itemList) {
        model.setRowCount(0);
        int count = 0;
        for(TransactionReportItem item : itemList) {
            String[] data = new String[8];
            data[0] = String.valueOf(++count);
            data[1] = item.getProductId();
            data[2] = item.getPrice().toString();
            data[3] = item.getSold().toString();
            data[4] = item.getSoldTotal().toString();
            data[5] = item.getExpDamaged().toString();
            data[6] = item.getExpDamagedTotal().toString();
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
        String[] columnName = {"No.", "Product ID","Price","Sold Pieces","Sold Total"
                              ,"Damaged/Expired Pieces","Damaged/Expired Total","Total Amount"};
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

        getColumnModel().getColumn(1).setMinWidth(100);

        getColumnModel().getColumn(2).setMinWidth(100);
        getColumnModel().getColumn(2).setMaxWidth(100);

        getColumnModel().getColumn(3).setMaxWidth(120);
        getColumnModel().getColumn(3).setMinWidth(120);

        getColumnModel().getColumn(4).setMinWidth(120);
        getColumnModel().getColumn(4).setMaxWidth(120);

        getColumnModel().getColumn(5).setMinWidth(150);
        getColumnModel().getColumn(5).setMaxWidth(150);

        getColumnModel().getColumn(6).setMinWidth(135);
        getColumnModel().getColumn(6).setMaxWidth(135);

        getColumnModel().getColumn(7).setMinWidth(100);
        getColumnModel().getColumn(7).setMaxWidth(100);
    }
}
