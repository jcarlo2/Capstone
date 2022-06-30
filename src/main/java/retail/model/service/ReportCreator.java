package retail.model.service;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import retail.shared.entity.NullProductReport;
import retail.shared.entity.NullReportItem;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import java.util.ArrayList;

public final class ReportCreator {

    @Contract("_ -> new")
    public @NotNull NullProductReport createNullReport(String @NotNull [] data) {
        return  new NullProductReport(data[0],data[1],data[2],data[3],"");
    }

    @Contract(pure = true)
    public @NotNull ArrayList<NullReportItem> createAllNullItem(String @NotNull[] @NotNull [] dataList, String reportId) {
        ArrayList<NullReportItem> itemList = new ArrayList<>();
        for(String[] data : dataList) {
            Double total = Double.parseDouble(data[1]) * Double.parseDouble(data[7]);
            itemList.add(new NullReportItem(data[0],data[1],data[2],String.valueOf(total),reportId,""));
        }
        return itemList;
    }

    public @NotNull TransactionItemDetail createReportItem(String @NotNull[] data) {
        return new TransactionItemDetail(data[0],data[1],data[2],data[3],data[4],data[5],data[6]);
    }

    public @NotNull ArrayList<TransactionItemDetail> createAllReportItem(String @NotNull[] @NotNull[] dataList) {
        ArrayList<TransactionItemDetail> itemList = new ArrayList<>();
        for(String[] data : dataList) {
            itemList.add(createReportItem(data));
        }
        return itemList;
    }

    @Contract("_ -> new")
    public @NotNull TransactionDetail createTransactionDetail(String @NotNull[] data) {
        return new TransactionDetail(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
    }
}
