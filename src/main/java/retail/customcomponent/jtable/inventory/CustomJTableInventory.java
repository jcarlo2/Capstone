package retail.customcomponent.jtable.inventory;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.model.Product;
import retail.model.ProductReportItem;
import retail.util.constant.ConstantDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
public class CustomJTableInventory extends JTable {
    private final DefaultTableModel model = new DefaultTableModel(0,8);
    boolean check;

    public CustomJTableInventory(boolean check) {
        this.check = check;
        setModel(model);
        setUpJTable();
    }

    public void addItemList(@NotNull ArrayList<ProductReportItem> itemList) {
        model.setRowCount(0);
        int count = 0;
        for(ProductReportItem item : itemList) {
            String[] data = new String[6];
            data[0] = String.valueOf(++count);
            data[1] = item.getProductId();
            data[2] = String.valueOf(item.getPrice());
            data[3] = String.valueOf(item.getQuantityByPieces());
            data[4] = String.valueOf(item.getQuantityByBox());
            data[5] = String.valueOf(item.getPiecesPerBox());
            model.addRow(data);
        }
    }

    public void addItem(@NotNull Product product, BigDecimal oldCount) {
        if(!isDuplicate(product.getId())) {
            String[] data = new String[8];
            data[0] = "";
            data[1] = product.getId();
            data[2] = String.valueOf(product.getPrice());
            data[3] = String.valueOf(product.getQuantityPerPieces());
            data[4] = String.valueOf(product.getQuantityPerBox());
            data[5] = String.valueOf(product.getPiecesPerBox());
            data[6] = String.valueOf(oldCount);
            data[7] = String.valueOf(product.getQuantityPerPieces().add(oldCount));
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
        if(check) {
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
            getColumnModel().getColumn(7).setMinWidth(200);
        }
    }

    private void addColumnName() {
        String[] columnName = {"No.","Product ID","Price","Quantity By Pieces","Quantity By Box"
                                ,"Pieces Per Box","Old Stock Count","New Stock Count"};
        int COLUMN_NUMBER = 8;
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
