package retail.shared.custom.jtable;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.shared.custom.jtable.other.TableAbstract;
import retail.shared.pojo.InventoryItem;
import retail.shared.pojo.DeliveryAdd;

import javax.swing.table.TableModel;
import java.util.ArrayList;

@Getter
public class AddDeliveryTable extends TableAbstract {
    public AddDeliveryTable(TableModel model) {
        super(model, new String[] {"No.",
                                   "Product ID","" +
                                   "Quantity Per Pieces",
                                   "Quantity Per Box",
                                   "Total Price",
                                   "Discount",
                                   "Total Amount",
                                   "Old Stock Count",
                                   "New Stock Count"});
    }

    public void addNullItemList(@NotNull ArrayList<InventoryItem> itemList) {
        model.setRowCount(0);
        int count = 0;
        for(InventoryItem item : itemList) {
            String[] data = new String[6];
            data[0] = String.valueOf(++count);
            data[1] = item.getId();
            data[2] = String.valueOf(item.getPrice());
            data[3] = String.valueOf(item.getQuantity());
            model.addRow(data);
        }
    }

    public void addItem(@NotNull DeliveryAdd item) {
        if(isDuplicate(item.getProductId())) {
            String[] data = new String[8];
            data[0] = "";
            data[1] = item.getProductId();
            data[2] = item.getPrice();
            data[3] = item.getQuantityPerPieces();
            data[4] = item.getQuantityPerBox();
            data[5] = item.getPiecesPerBox();
            data[6] = item.getOldStock();
            data[7] = String.valueOf((Double.parseDouble(item.getQuantityPerPieces()) + Double.parseDouble(item.getOldStock())));
            model.addRow(data);
            fixNumberColumn();
        }
    }
}
