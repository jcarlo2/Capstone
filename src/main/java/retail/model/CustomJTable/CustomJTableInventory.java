package retail.model.CustomJTable;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.dto.ProductObject;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

@Getter
public class CustomJTableInventory extends JTable {
    private final DefaultTableModel model;

    public CustomJTableInventory(DefaultTableModel model) {
        this.model = model;
        setModel(model);
        setUpJTable();
    }

    public void populateInventoryJTable(@NotNull ArrayList<ProductObject> list) {
        getModel().setRowCount(0);
        int count = 0;
            for (ProductObject productObject : list) {
                String[] listData = new String[7];
                listData[0] = String.valueOf(++count);
                listData[1] = productObject.getId();
                listData[2] = productObject.getDescription();
                listData[3] = String.valueOf(productObject.getPrice());
                listData[4] = String.valueOf(productObject.getQuantityPerPieces());
                listData[5] = String.valueOf(productObject.getQuantityPerBox());
                listData[6] = String.valueOf(productObject.getPiecesPerBox());
                getModel().addRow(listData);
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

    private void addColumnName() {
        String[] columnName = {"No.","ID","Description","Price","Quantity By Pieces","Pieces Per Box","Quantity By Box"};
        int COLUMN_NUMBER = 7;
        for(int i=0;i<COLUMN_NUMBER;i++) {
            getColumnModel().getColumn(i).setHeaderValue(columnName[i]);
        }
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
