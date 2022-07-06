package retail.model.facade.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.model.service.Calculate;
import retail.model.service.Creator;
import retail.model.service.inventory.add.NullProduct;
import retail.model.service.transaction.add.ReturnTransactionService;
import retail.shared.entity.NullProductReport;
import retail.shared.entity.NullReportItem;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class ReturnTransactionFacade {
    private final ReturnTransactionService service = new ReturnTransactionService();
    private final NullProduct nullService = new NullProduct();
    private final Creator creator = new Creator();
    private final Calculate calculate = new Calculate();

    public @NotNull ArrayList<TransactionDetail> getAllValidReport() {
        return service.getAllValidReport();
    }

    public ArrayList<TransactionDetail> findAllReportByString(String str) {
        ArrayList<TransactionDetail> reportList = getAllValidReport();
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

    public boolean verifyReturnedItemDetails(String[] data) {
        return service.verifyReturnedItemDetails(creator.createProductReturnDetail(data));
    }

    public String subtraction(Double a, Double b) {
        return service.subtraction(a,b);
    }

    public boolean verifyTableForSaving(String[][] dataList) {
        return service.verifyTableForSaving(dataList);
    }

    public void saveReport(String[][] dataList, String id, String user, String total, String credit) {
        TransactionDetail report = creator.createTransactionDetail(new String[] {id,"","", user, total, service.reverseConvertId(id), credit,""});
        ArrayList<TransactionItemDetail> itemList = creator.createAllReportItem(dataList);
        service.invalidateReport(id);
        service.save(report,itemList);
        service.reflectItemToInventory(dataList, id);

        String nullId = nullService.generateId();
        ArrayList<NullReportItem> nullList = creator.createAllNullProduct(dataList,nullId);
        String[] nullReport = {nullId,user,service.calculateNullTotal(nullList),service.reverseConvertId(id)};
        NullProductReport nullProductReport = creator.createNullReport(nullReport);
        service.addTransactionNullReport(nullProductReport,nullList);
    }

    public String addition(String a, String b) {
        return service.addition(Double.parseDouble(a),Double.parseDouble(b));
    }

    public boolean lessThanComparison(String a, String b) {
        return service.lessThanComparison(a,b);
    }
}
