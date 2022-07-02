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

    default void deleteReport(String id) {
        deleteTransactionReport(id);
        deleteReportItem(id);
    }

    boolean isReportExist(String id);

    ArrayList<TransactionItemDetail> getAllTransactionReportItem(String id);

    void deleteReportItem(String id);

    void deleteTransactionReport(String id);

    void saveTransactionReport(TransactionDetail transactionDetail);

    void saveTransactionReportItem(@NotNull TransactionDetail transactionDetail, ArrayList<TransactionItemDetail> transactionItemDetail);

    ArrayList<TransactionDetail> getAllTransactionReportList();

    ArrayList<TransactionDetail> getAllValidTransactionReport();

    TransactionDetail getTransactionReportById(String id);

    void invalidateId(String id);

    ArrayList<TransactionDetail> getAllTransactionReportByDate(String start, String end);

    ArrayList<TransactionDetail> getAllReportByDateAndValidity(String start, String end);

    boolean isValid(String id);

    void revalidate(String id);
}
