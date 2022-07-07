package retail.model.service;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import retail.shared.entity.*;
import retail.shared.pojo.DeliveryAdd;
import retail.shared.pojo.ProductDisplay;
import retail.shared.pojo.ProductReturnedDetail;

import java.util.ArrayList;

public final class Creator {

    @Contract("_ -> new")
    public @NotNull NullProductReport createNullReport(String @NotNull [] data) {
        return  new NullProductReport(data[0],data[1],data[2],data[3],"");
    }

    @Contract(pure = true)
    public @NotNull ArrayList<NullReportItem> createAllNullProduct(String @NotNull[] @NotNull [] dataList, String reportId) {
        ArrayList<NullReportItem> itemList = new ArrayList<>();
        for (String[] data : dataList) {
            Double total = Double.parseDouble(data[1]) * Double.parseDouble(data[7]);
            itemList.add(new NullReportItem(data[0], data[1], data[2], String.valueOf(total), reportId));
        }
        return itemList;
    }

    @Contract(pure = true)
    public @NotNull ArrayList<NullReportItem> createAllNullItem(String @NotNull[] @NotNull [] dataList, String reportId) {
        ArrayList<NullReportItem> itemList = new ArrayList<>();
        for(String[] data : dataList) {
            Double total = Double.parseDouble(data[1]) * Double.parseDouble(data[2]);
            itemList.add(new NullReportItem(data[0],data[1],data[2],String.valueOf(total),reportId));
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

    @Contract("_ -> new")
    public @NotNull ProductDisplay convertToDisplay(String @NotNull [] data) {
        return new ProductDisplay(data[0],data[1],data[2],data[4],data[3]);
    }

    @Contract("_ -> new")
    public @NotNull ProductReturnedDetail createProductReturnDetail(String @NotNull [] data) {
        return new ProductReturnedDetail(data[0],data[1],data[2]);
    }

    @Contract("_ -> new")
    public @NotNull Merchandise createMerchandise(String @NotNull [] data) {
        return new Merchandise(data[0],data[1],data[2],data[3],data[4],data[5]);
    }

    @Contract("_, _ -> new")
    public @NotNull DeliveryAdd createItemDetail(String @NotNull [] data, String oldStock) {
        return new DeliveryAdd(data[0],data[1],data[2],data[3],data[4],oldStock);
    }

    public @NotNull ArrayList<DeliveryItemDetail> convertAllToDeliveryItem(String @NotNull[] @NotNull[] dataList) {
        ArrayList<DeliveryItemDetail> itemList = new ArrayList<>();
        for(String[] data : dataList) {
            itemList.add(new DeliveryItemDetail(data[0],data[1],data[2],data[3],data[4]));
        }
        return itemList;
    }

    @Contract("_ -> new")
    public @NotNull DeliveryDetail createDeliveryDetail(String @NotNull [] data) {
        return new DeliveryDetail(data[0],data[1], "");
    }
}
















