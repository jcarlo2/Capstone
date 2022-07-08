package retail.shared.custom.jtable;

import org.jetbrains.annotations.NotNull;
import retail.shared.custom.jtable.other.TableAbstract;

import javax.swing.table.TableModel;

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
}
