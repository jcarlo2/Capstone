package retail.model.repository.implementation;

import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.DeliveryItemDetail;

import java.util.ArrayList;

public interface Delivery {
    default void save(DeliveryDetail report,ArrayList<DeliveryItemDetail> itemList) {
        addReport(report);
        addReportItem(itemList,report.getId());
    }

    void addReport(DeliveryDetail report);
    void addReportItem(ArrayList<DeliveryItemDetail> itemList, String id);
    boolean isReportExist(String id);
}
