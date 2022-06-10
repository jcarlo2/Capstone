package retail.shared.custom.jtable.other;

import org.jetbrains.annotations.NotNull;
import retail.shared.entity.TransactionItemDetail;

import java.util.ArrayList;

public interface TransactionTableHLC {
    String[] columnName = {"No.", "Product ID","Price","Sold Pieces",
            "Sold Total","Discount %%", "Discount Total",
            "Total Amount", "Reason", "Return"};

    void addReportItem(@NotNull TransactionItemDetail item, String count);

    void addAllReportItem(@NotNull ArrayList<TransactionItemDetail> itemList);

    boolean isDuplicate(String id);

    void removeRow(int row);
}
