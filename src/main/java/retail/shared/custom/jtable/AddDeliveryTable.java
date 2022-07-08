package retail.shared.custom.jtable;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.shared.custom.jtable.other.TableAbstract;
import retail.shared.entity.DeliveryItemDetail;

import javax.swing.table.TableModel;
import java.util.ArrayList;

@Getter
public class AddDeliveryTable extends TableAbstract {
    public AddDeliveryTable(TableModel model) {
        super(model, new String[] {"No.",
                                   "Product ID",
                                   "Quantity",
                                   "Pieces Per Box",
                                   "Total Price",
                                   "Discount%%",
                                   "Discount Total",
                                   "Total Amount"});
    }

    public void addItem(String @NotNull [] data, String total) {
        if(isDuplicate(data[0])) {
            String[] item = new String[8];
            item[0] = "";
            item[1] = data[0];
            item[2] = data[1] + " / " + data[2];
            item[3] = data[3];
            item[4] = data[4];
            item[5] = data[5];
            item[6] = data[6];
            item[7] = total;
            model.addRow(item);
            fixNumberColumn();
        }
    }

    public void addAllItem(@NotNull ArrayList<DeliveryItemDetail> itemList) {
        String[] data = new String[8];
        model.setRowCount(0);
        for(DeliveryItemDetail item : itemList) {
            data[0] = "";
            data[1] = item.getProductId();
            data[2] = item.getQuantityPerPieces() + " / " + item.getQuantityPerBox();
            data[3] = item.getPiecesPerBox();
            data[4] = item.getTotalPrice();
            data[5] = item.getDiscountPercentage();
            data[6] = item.getDiscountTotal();
            data[7] = item.getTotalAmount();
            model.addRow(data);
        }
        fixNumberColumn();
    }
}
