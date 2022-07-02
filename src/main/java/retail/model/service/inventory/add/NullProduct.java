package retail.model.service.inventory.add;

import org.jetbrains.annotations.NotNull;
import retail.model.service.Service;

public class NullProduct implements Service {
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

}
