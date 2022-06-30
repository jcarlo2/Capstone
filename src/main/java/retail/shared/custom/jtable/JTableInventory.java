package retail.shared.custom.jtable;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.shared.custom.jtable.other.TableAbstract;
import retail.shared.entity.DeliveryItemDetail;
import retail.shared.entity.Merchandise;

import javax.swing.table.TableModel;
import java.util.ArrayList;

@Getter
public class JTableInventory extends TableAbstract {
    public JTableInventory(TableModel model) {
        super(model, new String[] {"No.",
                                   "Product ID","" +
                                   "Price",
                                   "Quantity By Pieces",
                                   "Quantity By Box",
                                   "Pieces Per Box",
                                   "Old Stock Count",
                                   "New Stock Count"});
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
        if(isDuplicate(merchandise.getId())) {
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
}
