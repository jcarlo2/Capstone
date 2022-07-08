package retail.model.service;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import retail.shared.entity.*;
import retail.shared.pojo.ProductDisplay;
import retail.shared.pojo.ProductReturnedDetail;

import java.util.ArrayList;

public final class Creator {

    public @NotNull NullProductReport createNullReport(String id, String user,String total,String link) {
        return  new NullProductReport(id,user,total,link,"");
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

    public @NotNull ArrayList<DeliveryItemDetail> deliveryItem(String@NotNull[] @NotNull[] dataList) {
        ArrayList<DeliveryItemDetail> itemList = new ArrayList<>();
        for(String[] data : dataList) {
            String quantity = data[1].substring(0,data[1].indexOf(" "));
            String box = data[1].substring(data[1].lastIndexOf(" "));
            itemList.add(new DeliveryItemDetail(data[0],quantity,box,data[2],data[3],data[4],data[5],data[6]));
        }
        return itemList;
    }

    @Contract("_, _, _ -> new")
    public @NotNull DeliveryDetail createDeliveryReport(String id, String lastName, String total) {
        return new DeliveryDetail(id,lastName,total,"");
    }

    public @NotNull ArrayList<NullReportItem> createAllNullItem(String @NotNull[] @NotNull [] dataList, String id) {
        ArrayList<NullReportItem> itemList = new ArrayList<>();
        for(String[] data : dataList) {
            itemList.add(new NullReportItem(data[0],data[1],data[2],data[3],data[4],data[5],id));
        }
        return itemList;
    }
}
















