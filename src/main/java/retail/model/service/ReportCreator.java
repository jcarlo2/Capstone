package retail.model.service;

import org.jetbrains.annotations.NotNull;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import java.util.ArrayList;

public class ReportCreator {

    public @NotNull TransactionItemDetail createReportItem(String @NotNull[] data) {
        return new TransactionItemDetail(data[0],data[1],data[2],data[3],data[4],data[5],data[6]);
    }

    public ArrayList<TransactionItemDetail> createAllReportItem(String @NotNull[] @NotNull[] dataList) {
        ArrayList<TransactionItemDetail> itemList = new ArrayList<>();
        for(String[] data : dataList) {
            itemList.add(createReportItem(data));
        }
        return itemList;
    }

    public TransactionDetail createTransactionDetail(String @NotNull[] data) {
        return new TransactionDetail(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
    }
}
