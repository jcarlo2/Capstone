package retail.model.service.transaction;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import retail.model.repository.implementation.Product;
import retail.model.repository.implementation.TransactionReport;
import retail.model.repository.implementer.ProductRepository;
import retail.model.repository.implementer.TransactionRepository;
import retail.shared.entity.Merchandise;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class AddTransactionService {
    private final Product product = new ProductRepository();
    private final TransactionReport report = new TransactionRepository();

    public ArrayList<Merchandise> getAllProduct() {
        return product.getAllProduct();
    }

    public String findPriceById(String productId) {
        return product.findPriceById(productId);
    }

    @NotNull
    public String generateId() {
        long id;
        String formatId = "";
        boolean flag = true;
        while(flag) {
            id = (long) (Math.random() * 1000000000000L);
            formatId = String.format("%013d",id);
            if(!report.isReportExist(formatId)) flag = false;
        }
        return "TR" + formatId + "-A0";
    }

    public void addReport(TransactionDetail report, ArrayList<TransactionItemDetail> itemList) {
        this.report.addReport(report, itemList);
    }

    public void reflectToInventory(@NotNull ArrayList<TransactionItemDetail> itemList) {
        for(TransactionItemDetail item : itemList) {
            product.updateProductQuantity(item.getProductId(),Double.parseDouble(item.getSold()) * -1);
        }
    }

    @NonNull
    public String calculateReportAmount(String[][] dataList) {
        BigDecimal total = new BigDecimal("0");
        for(String[] data : dataList) {
            total = total.add(new BigDecimal(data[6]));
        }
        return total.setScale(2, RoundingMode.HALF_EVEN).toString();
    }
}
