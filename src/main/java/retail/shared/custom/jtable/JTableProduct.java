package retail.shared.custom.jtable;

import org.jetbrains.annotations.NotNull;
import retail.shared.entity.Merchandise;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class JTableProduct extends JTable {
    private final DefaultTableModel model = new DefaultTableModel(0,7);

    public JTableProduct() {
        setModel(model);
        setUpJTable();
    }

    public void populateProductTable(@NotNull ArrayList<Merchandise> merchandiseList) {
        model.setRowCount(0);
        int count = 0;
        for (Merchandise merchandise : merchandiseList) {
            String[] listData = new String[7];
            listData[0] = String.valueOf(++count);
            listData[1] = merchandise.getId();
            listData[2] = merchandise.getDescription();
            listData[3] = String.valueOf(merchandise.getPrice());
            listData[4] = String.valueOf(merchandise.getQuantityPerPieces());
            listData[5] = String.valueOf(merchandise.getPiecesPerBox());
            listData[6] = String.valueOf(merchandise.getQuantityPerBox());
            model.addRow(listData);
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
        centerJTableText();
        setJTableColumnWidth();
    }

    private void addColumnName() {
        String[] columnName = {"No.","ID","Description","Price","Quantity By Pieces","Pieces Per Box","Quantity By Box"};
        for(int i=0;i<columnName.length;i++) {
            getColumnModel().getColumn(i).setHeaderValue(columnName[i]);
        }
    }

    private void setJTableColumnWidth() {
        // DID NOT SET MIN_MAX_WIDTH OF COLUMN(2) DESCRIPTION TO FORCE TO TAKE THE EXTRA SPACE

        getColumnModel().getColumn(0).setMinWidth(35);
        getColumnModel().getColumn(0).setMaxWidth(35);

        getColumnModel().getColumn(1).setMinWidth(200);
        getColumnModel().getColumn(1).setMaxWidth(200);

        getColumnModel().getColumn(3).setMaxWidth(100);
        getColumnModel().getColumn(3).setMinWidth(100);

        getColumnModel().getColumn(4).setMinWidth(120);
        getColumnModel().getColumn(4).setMaxWidth(120);

        getColumnModel().getColumn(5).setMinWidth(100);
        getColumnModel().getColumn(5).setMaxWidth(100);

        getColumnModel().getColumn(6).setMinWidth(100);
        getColumnModel().getColumn(6).setMaxWidth(100);
    }

    private void centerJTableText() {
        int COLUMN_NUMBER = getColumnModel().getColumnCount();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<COLUMN_NUMBER;i++) {
            getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
}