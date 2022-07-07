package retail.shared.custom.jtable;

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
}
