package retail.model.facade.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.model.service.Calculate;
import retail.model.service.Creator;
import retail.model.service.inventory.product.ProductViewService;
import retail.model.service.transaction.add.AddTransactionService;
import retail.shared.entity.Merchandise;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import java.math.BigDecimal;
import java.util.ArrayList;

import static retail.shared.constant.ConstantDialog.INVALID_INPUT;

public class AddTransactionFacade {
    private final AddTransactionService service = new AddTransactionService();
    private final ProductViewService productService = new ProductViewService();
    private final Calculate calculate = new Calculate();
    private final Creator creator = new Creator();

    public ArrayList<Merchandise> getAllProduct() {
        return service.getAllProduct();
    }

    public String findPriceById(String productId) {
        return productService.findPriceById(productId);
    }

    public String generateId() {
       return service.generateId();
    }

    public boolean isReportIdExist(String id) {
        return service.isReportIdExist(id);
    }

    public TransactionDetail createTransactionDetail(String[] data) {
        return creator.createTransactionDetail(data);
    }

    public String calculateReportAmount(String[][] dataList) {
        return service.calculateReportAmount(dataList);
    }

    public TransactionItemDetail addEvent(String @NotNull [] data) {
        if(data[2].equals("0") || data[2].equals("")) return null;
        if(!calculate.isValidNumber(data[2],data[3],data[4],data[5])) {
            INVALID_INPUT();
            return null;
        }
        data[6] = calculate.calculateItemAmount(data[3],data[5]);
        return creator.createReportItem(data);
    }

    public void saveEvent(String[][] dataList,String[] reportData) {
        ArrayList<TransactionItemDetail> itemList = creator.createAllReportItem(dataList);
        TransactionDetail report = createTransactionDetail(reportData);
        service.addReport(report,itemList);
        service.reflectToInventory(itemList);
    }

    public String soldEvent(String @NotNull ... input) {
        if(!calculate.isValidNumber(input[2],input[3],input[4],input[5])) {
            return "0";
        }
        return calculate.calculateSoldTotal(new BigDecimal(input[1]), new BigDecimal(input[2]));
    }

    public String discountPercentageEvent(String @NotNull ... input) {
        if(!calculate.isValidNumber(input[2],input[3],input[4],input[5])) {
            return "0";
        }
        return calculate.calculateDiscountAmount(new BigDecimal(input[3]),new BigDecimal(input[4]));
    }

    public String discountAmountEvent(String @NotNull [] allData) {
        String discount = "";
        if(calculate.isValidNumber(allData[3],allData[5])) {
            discount = calculate.calculateDiscountPercentage(new BigDecimal(allData[3]), new BigDecimal(allData[5]));
        }
        return discount;
    }
}


















