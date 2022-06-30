package retail.shared.custom.jtable.other;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retail.shared.constant.ConstantDialog;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.Font;

public abstract class TableAbstract extends JTable implements TableHLC{
    protected final DefaultTableModel model;

    public TableAbstract(TableModel model, String[] columnName) {
        super(model);
        this.model = (DefaultTableModel) model;
        addColumnName(columnName);
        setTable();
    }

    @Override
    public boolean isDuplicate(String id) {
        int ROW = getRowCount();
        if(ROW == 0) return true;
        for(int i=0;i<ROW;i++) {
            if(id.equals(getValueAt(i,1))) {
                ConstantDialog.DUPLICATE_ID();
                return false;
            }
        }
        return true;
    }

    @Override
    public void removeRow(int row) {
        ((DefaultTableModel)getModel()).removeRow(row);
    }

    @Override
    public String @Nullable [] rowGrabber() {
        int row = getSelectedRow();
        if(row == -1) return null;
        String[] data = new String[getColumnCount() - 1];
        for(int i=0;i< data.length;i++) {
            data[i] = getValueAt(row, i + 1).toString();
        }
        return data;
    }

    @Override
    public String[][] tableGrabber() {
        final int row = getRowCount();
        final int column = getColumnCount() - 1;
        String[][] dataList = new String[row][column];
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                dataList[i][j] = getValueAt(i, j + 1).toString();
            }
        }
        return dataList;
    }

    @Override
    public void fixNumberColumn() {
        int row = getRowCount();
        for(int i=0;i<row;i++) {
            setValueAt(i + 1, i, 0);
        }
    }

    @Override
    public void removeSelectedRow() {
        int row = getSelectedRow();
        if(row == -1) return;
        removeRow(row);
        fixNumberColumn();
    }

    private void addColumnName(String @NotNull [] columnName) {
        int i = 0;
        for(String name : columnName) {
            getColumnModel().getColumn(i++).setHeaderValue(name);
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
        centerJTableText();
        setColumnWidth();
    }

    private void centerJTableText() {
        int COLUMN_NUMBER = getColumnModel().getColumnCount();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<COLUMN_NUMBER;i++) {
            getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private void setColumnWidth() {
        getColumnModel().getColumn(0).setMinWidth(35);
        getColumnModel().getColumn(0).setMaxWidth(35);
    }
}
