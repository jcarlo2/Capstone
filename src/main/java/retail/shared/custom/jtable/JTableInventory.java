package retail.shared.custom.jtable;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.shared.entity.Merchandise;
import retail.shared.entity.DeliveryItemDetail;
import retail.shared.constant.ConstantDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class JTableInventory extends JTable {
    private final DefaultTableModel model = new DefaultTableModel(0,8);

    public JTableInventory() {
        setModel(model);
        setUpJTable();
        getColumnModel().getColumn(0).setMinWidth(35);
        getColumnModel().getColumn(0).setMaxWidth(35);
    }

    public void addItemList(@NotNull ArrayList<DeliveryItemDetail> itemList) {
        model.setRowCount(0);
        int count = 0;
        for(DeliveryItemDetail item : itemList) {
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

    public void addItem(@NotNull Merchandise merchandise, Double oldCount) {
        if(!isDuplicate(merchandise.getId())) {
            String[] data = new String[8];
            data[0] = "";
            data[1] = merchandise.getId();
            data[2] = String.valueOf(merchandise.getPrice());
            data[3] = String.valueOf(merchandise.getQuantityPerPieces());
            data[4] = String.valueOf(merchandise.getQuantityPerBox());
            data[5] = String.valueOf(merchandise.getPiecesPerBox());
            data[6] = String.valueOf(oldCount);
            data[7] = merchandise.getQuantityPerPieces() + oldCount;
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
        centerJTableText();
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
