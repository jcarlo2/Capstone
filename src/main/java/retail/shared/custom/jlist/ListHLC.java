package retail.shared.custom.jlist;

import org.jetbrains.annotations.NotNull;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.NullProductReport;
import retail.shared.entity.TransactionDetail;

import java.util.ArrayList;

public interface ListHLC {
    void populateTransactionList(@NotNull ArrayList<TransactionDetail> list);
    void populateDeliveryList(@NotNull ArrayList<DeliveryDetail> list );
    boolean isNotSameTransactionList(@NotNull ArrayList<TransactionDetail> reportList);
    boolean isNotSameDeliveryList(@NotNull ArrayList<DeliveryDetail> reportList);
    boolean isNotSameNullList(@NotNull ArrayList<NullProductReport> reportList);
    ArrayList<String> getAllElement();

    void populateNullList(ArrayList<NullProductReport> allNullReport);
}
