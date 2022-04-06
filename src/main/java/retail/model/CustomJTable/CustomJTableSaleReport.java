package retail.model.CustomJTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomJTableSaleReport extends JTable {

    public CustomJTableSaleReport(DefaultTableModel model) {
        setModel(model);
        setUpJTable();
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
        String[] columnName = {"No.", "Product ID","Price","Sold","Sold Total"
                              ,"Damaged/Expired","Damaged/Expired Total","Total Amount"};
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
        // DID NOT SET MIN_MAX_WIDTH OF COLUMN(1) ProductID TO FORCE TO TAKE THE EXTRA SPACE
        getColumnModel().getColumn(0).setMinWidth(35);
        getColumnModel().getColumn(0).setMaxWidth(35);

        getColumnModel().getColumn(2).setMinWidth(150);
        getColumnModel().getColumn(2).setMaxWidth(150);

        getColumnModel().getColumn(3).setMaxWidth(150);
        getColumnModel().getColumn(3).setMinWidth(150);

        getColumnModel().getColumn(4).setMinWidth(150);
        getColumnModel().getColumn(4).setMaxWidth(150);

        getColumnModel().getColumn(5).setMinWidth(150);
        getColumnModel().getColumn(5).setMaxWidth(150);

        getColumnModel().getColumn(6).setMinWidth(150);
        getColumnModel().getColumn(6).setMaxWidth(150);

        getColumnModel().getColumn(7).setMinWidth(150);
        getColumnModel().getColumn(7).setMaxWidth(150);
    }
}
