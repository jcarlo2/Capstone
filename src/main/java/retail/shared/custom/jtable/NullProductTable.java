package retail.shared.custom.jtable;

import org.jetbrains.annotations.NotNull;
import retail.shared.custom.jtable.other.TableAbstract;
import retail.shared.entity.NullReportItem;

import javax.swing.table.TableModel;
import java.util.ArrayList;

public class NullProductTable extends TableAbstract {
    public NullProductTable(TableModel model) {
        super(model, new String[] {"No.",
                                   "Product Id",
                                   "Price",
                                   "Quantity Per Pieces",
                                   "Quantity Per Box",
                                   "Pieces Per Box",
                                   "Total Amount"});
    }

    public void addItem(String @NotNull [] data) {
        if(isDuplicate(data[0])) {
            String[] item = new String[7];
            item[0] = "";
            System.arraycopy(data, 0, item, 1, item.length - 1);
            model.addRow(item);
            fixNumberColumn();
        }
    }

    public void addAllItem(@NotNull ArrayList<NullReportItem> itemList) {
        String[] data = new String[7];
        model.setRowCount(0);
        for(NullReportItem item : itemList) {
            data[0] = "";
            data[1] = item.getId();
            data[2] = item.getPrice();
            data[3] = item.getQuantity();
            data[4] = item.getBox();
            data[5] = item.getPieces();
            data[6] = item.getTotal();
            model.addRow(data);
        }
        fixNumberColumn();
    }
}
