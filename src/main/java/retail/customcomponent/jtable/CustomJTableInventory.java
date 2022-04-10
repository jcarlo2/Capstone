package retail.customcomponent.jtable;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.model.ProductObject;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class CustomJTableInventory extends JTable {
    private final DefaultTableModel model = new DefaultTableModel(0,7);

    public CustomJTableInventory() {
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
                listData[2] = String.valueOf(productObject.getPrice());
                listData[3] = String.valueOf(productObject.getQuantityPerPieces());
                listData[4] = String.valueOf(productObject.getQuantityPerBox());
                listData[5] = String.valueOf(productObject.getPiecesPerBox());
                listData[6] = productObject.getDescription();
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
        setJTableColumnWidth();
        centerJTableText();
    }

    private void setJTableColumnWidth() {
        getColumnModel().getColumn(0).setMinWidth(35);
        getColumnModel().getColumn(0).setMaxWidth(35);

        getColumnModel().getColumn(1).setMinWidth(200);
        getColumnModel().getColumn(1).setMaxWidth(200);

        getColumnModel().getColumn(2).setMaxWidth(100);
        getColumnModel().getColumn(2).setMinWidth(100);

        getColumnModel().getColumn(3).setMaxWidth(120);
        getColumnModel().getColumn(3).setMinWidth(120);

        getColumnModel().getColumn(4).setMinWidth(100);
        getColumnModel().getColumn(4).setMaxWidth(100);

        getColumnModel().getColumn(5).setMinWidth(100);
        getColumnModel().getColumn(5).setMaxWidth(100);

        getColumnModel().getColumn(6).setMinWidth(200);
    }

    private void addColumnName() {
        String[] columnName = {"No.","ID","Price","Quantity By Pieces","Pieces Per Box","Quantity By Box","Description"};
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
