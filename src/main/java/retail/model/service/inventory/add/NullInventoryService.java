package retail.model.service.inventory.add;

import org.jetbrains.annotations.NotNull;
import retail.model.service.Service;
import retail.shared.entity.Merchandise;
import retail.shared.entity.NullProductReport;
import retail.shared.entity.NullReportItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class NullInventoryService implements Service {
    @NotNull
    public String generateId() {
        long id;
        String formatId = "";
        boolean flag = true;
        while(flag) {
            id = (long) (Math.random() * 1000000000000L);
            formatId = String.format("%013d",id);
            if(!nullProduct.isReportExist(formatId)) flag = false;
        }
        return "NP" + formatId + "-A0";
    }

    public boolean isReportIdExist(String id) {
       return nullProduct.isReportExist(id);
    }

    public void save(NullProductReport report, ArrayList<NullReportItem> itemList) {
        nullProduct.addNullReport(report,itemList);
        for(NullReportItem item : itemList) {
            product.updateByQuantity(item.getId(),Double.parseDouble(item.getQuantity()) * -1);
        }
    }
}
