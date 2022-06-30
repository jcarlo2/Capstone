package retail.model.repository.implementation;

import org.jetbrains.annotations.NotNull;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import java.util.ArrayList;

public interface TransactionReport {

    default void addReport(@NotNull TransactionDetail transactionDetail, ArrayList<TransactionItemDetail> transactionItemDetail) {
        saveTransactionReport(transactionDetail);
        saveTransactionReportItem(transactionDetail, transactionItemDetail);
    }

    boolean isReportExist(String id);

    ArrayList<TransactionItemDetail> getAllTransactionReportItem(String id);

    void deleteReportItem(String id);

    void deleteReport(String id);

    void saveTransactionReport(TransactionDetail transactionDetail);

    void saveTransactionReportItem(@NotNull TransactionDetail transactionDetail, ArrayList<TransactionItemDetail> transactionItemDetail);

    ArrayList<TransactionDetail> getAllTransactionReportList();

    ArrayList<TransactionDetail> getAllValidTransactionReport();

    TransactionDetail getTransactionReportById(String id);

    void invalidateId(String id);
}
