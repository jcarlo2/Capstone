package retail.model.service;

import org.jetbrains.annotations.NotNull;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

public class ReportCreator {

    public @NotNull TransactionItemDetail createReportItem(String @NotNull [] data) {
        return new TransactionItemDetail(data[0],data[1],data[2],data[3],data[4],data[5],data[6]);
    }

    public TransactionDetail createTransactionDetail(String @NotNull [] data) {
        return new TransactionDetail(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
    }
}
