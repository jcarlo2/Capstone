package retail.model;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@Getter
public class InventoryTable extends JTable {
    private final DefaultTableModel model;
    public InventoryTable(DefaultTableModel model) {
        this.model = model;
        setModel(model);
        model.setColumnCount(7);
        setUpJTable();
    }

    public void addRow(@NotNull ProductModel productModel) {
        String[] data = new String[6];
        data[0] = productModel.getId();
        data[1] = productModel.getDescription();
        data[2] = productModel.getPrice().toString();
        data[3] = productModel.getQuantityPerPieces().toString();
        data[4] = productModel.getPiecesPerBox().toString();
        data[5] = productModel.getQuantityPerBox().toString();
        model.addRow(data);
    }

    private void setUpJTable() {
        setFont(new Font("SansSerif", Font.BOLD, 15));
        setShowHorizontalLines(true);
        setShowVerticalLines(true);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        setDefaultEditor(Object.class,null); // DISABLE EDIT TABLE LIKE setEditable()

        addColumnName();
        autoResizeJTable();
        setJTableColumnWidth();
        centerJTableText();
    }

    private void autoResizeJTable() {
        // AUTO SET JTable.setAutoResizeMode Whenever InventoryPanel resize
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                if (getPreferredSize().width < getParent().getWidth()) {
                    setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                } else {
                    setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                }
            }
        });
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
