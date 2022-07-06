package retail.model.service.inventory.add;

import org.jetbrains.annotations.NotNull;
import retail.model.service.Service;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.DeliveryItemDetail;
import retail.shared.entity.NullProductReport;
import retail.shared.entity.NullReportItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class AddInventoryService implements Service {

    public boolean isDeliveryReportIdExist(String id) {
        return delivery.isReportExist(id);
    }

    @NotNull
    public String generateId(boolean check) {
        long id;
        String formatId = "";
        boolean flag = true;
        while(flag) {
            id = (long) (Math.random() * 1000000000000L);
            formatId = String.format("%013d",id);
            if(!transaction.isReportExist(formatId)) flag = false;
        }
        return (check ? "IR" : "NR") + formatId + "-A0";
    }

    public void saveDelivery(DeliveryDetail report, ArrayList<DeliveryItemDetail> itemList) {
        delivery.save(report, itemList);
        for(DeliveryItemDetail item : itemList) {
            product.updateProductQuantity(item.getProductId(), Double.parseDouble(item.getQuantityPerPieces()));
        }
    }

    public void saveNull(NullProductReport report, ArrayList<NullReportItem> itemList) {
        nullProduct.addNullReport(report,itemList);
        for(NullReportItem item : itemList) {
            product.updateProductQuantity(item.getId(), Double.parseDouble(item.getQuantity()) * -1);
        }
    }

    public String getNullTotalAmount(@NotNull ArrayList<NullReportItem> itemList) {
        BigDecimal total = new BigDecimal("0");
        for(NullReportItem item : itemList) {
            total = total.add(new BigDecimal(item.getTotal()));
        }
        return total.setScale(2, RoundingMode.HALF_EVEN).toString();
    }

    public boolean isNullReportExist(String id) {
        return nullProduct.isReportExist(id);
    }
}
