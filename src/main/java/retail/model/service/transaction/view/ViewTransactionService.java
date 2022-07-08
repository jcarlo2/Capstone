package retail.model.service.transaction.view;

import org.jetbrains.annotations.NotNull;
import retail.model.service.Service;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import java.util.ArrayList;

public class ViewTransactionService implements Service {

    public ArrayList<TransactionDetail> getAllReport() {
        return transaction.getAllTransactionReportList();
    }

    public ArrayList<TransactionDetail> getAllReportByDate(String start, String end) {
        return transaction.getAllTransactionReportByDate(start,end);
    }

    public ArrayList<TransactionDetail> getAllValidReport() {
        return transaction.getAllValidTransactionReport();
    }

    public ArrayList<TransactionDetail> getAllValidReportByDate(String start, String end) {
        return transaction.getAllReportByDateAndValidity(start,end);
    }

    public String[] getIdList(@NotNull String id) {
        int num = Character.getNumericValue(id.charAt(id.length() - 1)) + 1;
        String[] idList = new String[num];
        for(int i=0;i< idList.length;i++) {
            if(num == 0) break;
            idList[i] = id.substring(0,id.length() - 1) + --num;
        }
        return idList;
    }

    public boolean isValid(@NotNull String id) {
        return transaction.isValid(id);
    }

    public void delete(String @NotNull ...id) {
        for(String str : id) {
            transaction.deleteReport(str);
        }
    }

    public String convertListSelectedItem(@NotNull String id) {
        return id.substring(19);
    }

    public ArrayList<TransactionItemDetail> getAllReportItem(String id) {
        return transaction.getAllTransactionReportItem(id);
    }

    public String getReportPrice(String id) {
        return transaction.getTransactionReportById(id).getTotalAmount();
    }

    public void revalidate(String id) {
        transaction.revalidate(id);
    }
}
