package retail.model.service.transaction.add;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import retail.model.service.Service;
import retail.shared.entity.Merchandise;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class AddTransactionService implements Service {

    public ArrayList<Merchandise> getAllProduct() {
        return product.getAllProduct();
    }



    public boolean isReportIdExist(String id) {
        return transaction.isReportExist(id);
    }

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
        return "TR" + formatId + "-A0";
    }

    public void addReport(TransactionDetail report, ArrayList<TransactionItemDetail> itemList) {
        transaction.addReport(report, itemList);
    }

    public void reflectToInventory(@NotNull ArrayList<TransactionItemDetail> itemList) {
        for(TransactionItemDetail item : itemList) {
            product.updateByQuantity(item.getProductId(),Double.parseDouble(item.getSold()) * -1);
        }
    }

    @NonNull
    public String calculateReportAmount(String @NotNull[] @NotNull[] dataList) {
        BigDecimal total = new BigDecimal("0");
        for(String[] data : dataList) {
            BigDecimal price = new BigDecimal(product.findPriceById(data[0]));
            price = price.multiply(new BigDecimal(data[2]));
            total = total.add(price);
        }
        return total.setScale(2, RoundingMode.HALF_EVEN).toString();
    }
}
