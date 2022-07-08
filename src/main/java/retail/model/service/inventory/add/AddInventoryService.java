package retail.model.service.inventory.add;

import org.jetbrains.annotations.NotNull;
import retail.model.service.Service;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.DeliveryItemDetail;

import java.util.ArrayList;

public class AddInventoryService implements Service {

    @NotNull
    public String generateId() {
        long id;
        String formatId = "";
        boolean flag = true;
        while(flag) {
            id = (long) (Math.random() * 1000000000000L);
            formatId = String.format("%013d",id);
            if(!transaction.isReportExist(formatId)) flag = false;
        }
        return "IR" + formatId + "-A0";
    }

    public boolean isReportIdExist(String id) {
        return delivery.isReportExist(id);
    }

    public void save(DeliveryDetail report, ArrayList<DeliveryItemDetail> itemList) {
        delivery.save(report,itemList);
        for(DeliveryItemDetail item : itemList) {
            product.updateByQuantity(item.getProductId(),Double.parseDouble(item.getQuantityPerPieces()));
        }
    }
}
