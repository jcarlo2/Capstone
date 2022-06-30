package retail.model.facade.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.model.service.Calculate;
import retail.model.service.ReportCreator;
import retail.model.service.transaction.ReturnTransactionService;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;
import retail.shared.pojo.ProductReturnedDetail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class ReturnTransactionFacade {
    private final ReturnTransactionService service = new ReturnTransactionService();
    private final ReportCreator creator = new ReportCreator();
    private final Calculate calculate = new Calculate();

    public @NotNull ArrayList<TransactionDetail> getAllValidReport() {
        return service.getAllValidReport();
    }

    public ArrayList<TransactionDetail> getTransactionReportList() {
        return service.getTransactionReportList();
    }

    public ArrayList<TransactionDetail> findAllReportByString(String str) {
        ArrayList<TransactionDetail> reportList = getTransactionReportList();
        return service.findAllReportByString(str,reportList);
    }

    public boolean isReportExist(String value) {
        return service.isReportExist(value);
    }

    public ArrayList<TransactionItemDetail> getAllReportItem(String id) {
        return service.getAllReportItem(id);
    }

    public TransactionItemDetail createItem(String[] data) {
        return creator.createReportItem(data);
    }

    public String convertId(String id) {
        return service.convertId(id);
    }

    public String getReportTotalAmount(String id) {
        return service.getReportTotalAmount(id);
    }

    public String calculateNewTotal(String[][] data) {
        return service.calculateNewTotal(data);
    }

    public String calculateNewCredit(String oldCredit, String newCredit) {
        return service.calculateNewCredit(oldCredit,newCredit);
    }

    public ArrayList<TransactionItemDetail> getRowDataByNoneReason(String[][] dataList) {
        ArrayList<TransactionItemDetail> itemList = new ArrayList<>();
        for(String[] data : service.filterDataByReason(Objects.requireNonNull(dataList))) {
            if(data == null) break;
            itemList.add(creator.createReportItem(data));
        }
        return itemList;
    }

    public ArrayList<TransactionItemDetail> removeRowWithNoneReason(String[][] dataList) {
        ArrayList<TransactionItemDetail> itemList = new ArrayList<>();
        for(String[] data : service.removeRowWithReason(Objects.requireNonNull(dataList))) {
            if(data == null) break;
            itemList.add(creator.createReportItem(data));
        }
        return itemList;
    }

    public boolean isValidNumber(String ... str) {
        return calculate.isValidNumber(str);
    }

    public String calculateSoldTotal(BigDecimal price, BigDecimal sold) {
        return calculate.calculateSoldTotal(price,sold);
    }

    public String calculateDiscountAmount(BigDecimal soldTotal, BigDecimal percentage) {
        return calculate.calculateDiscountAmount(soldTotal,percentage);
    }

    public String calculateTotalAmount(String soldTotal, String discountTotal) {
        return calculate.calculateItemAmount(soldTotal,discountTotal);
    }

    public String negation(String num) {
        return service.negation(num);
    }

    public String reverseConvertId(String id) {
        return service.reverseConvertId(id);
    }

    public TransactionItemDetail recoverItem(String[] data, String reportId) {
        return service.recoverItem(data,reportId);
    }

    public boolean verifyReturnedItemDetails(ProductReturnedDetail product) {
        return service.verifyReturnedItemDetails(product);
    }

    public String subtraction(Double a, Double b) {
        return service.subtraction(a,b);
    }
}
