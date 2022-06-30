package retail.model.service.transaction;

import org.jetbrains.annotations.NotNull;
import retail.model.repository.implementer.TransactionRepository;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;
import retail.shared.pojo.ProductReturnedDetail;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ReturnTransactionService {
    private final TransactionRepository transactionRepository = new TransactionRepository();

    public ArrayList<TransactionDetail> getAllValidReport() {
        return transactionRepository.getReportListIfValid();
    }

    public ArrayList<TransactionDetail> getTransactionReportList() {
        return transactionRepository.getTransactionReportList();
    }

    public ArrayList<TransactionDetail> findAllReportByString(String str, @NotNull ArrayList<TransactionDetail> reportList) {
        ArrayList<TransactionDetail> reports = new ArrayList<>();
        for (TransactionDetail report : reportList) {
            String id = report.getId().toLowerCase();
            String date = report.getDate().toLowerCase();
            String combine = date + " - " + id;
            if (combine.contains(str.toLowerCase())) {
                reports.add(report);
            }
        }
        return reports;
    }

    public boolean isReportExist(String value) {
        return transactionRepository.isReportExist(value);
    }

    public ArrayList<TransactionItemDetail> getAllReportItem(String id) {
        return transactionRepository.getAllTransactionReportItem(id);
    }

    public String[][] filterDataByReason(String @NotNull[] @NotNull [] dataList) {
        String[][] newDataList = new String[dataList.length][];
        int i = 0;
        for (String[] data : dataList) {
            if (data[7].equals("--")) {
                newDataList[i++] = data;
            }
        }
        return newDataList;
    }

    public String[][] removeRowWithReason(String @NotNull[] @NotNull[] dataList) {
        String[][] newDataList = new String[dataList.length][];
        int i = 0;
        for(String[] data : dataList) {
            if(!data[data.length - 1].equals("--")) {
                newDataList[i++] = data;
            }
        }
        return newDataList;
    }

    public TransactionItemDetail recoverItem(String @NotNull [] data, String id) {
        ArrayList<TransactionItemDetail> itemList = getAllReportItem(id);
        for(TransactionItemDetail item : itemList) {
            if(item.getProductId().equals(data[0])) {
                return item;
            }
        }
        return null;
    }

    public String reverseConvertId(@NotNull String id) {
        int num = Character.getNumericValue(id.charAt(id.length() - 1));
        return id.substring(0, id.length()-1) + --num;
    }

    public String convertId(@NotNull String id) {
        int num = Character.getNumericValue(id.charAt(id.length() - 1));
        return id.substring(0, id.length()-1) + ++num;
    }

    public String getReportTotalAmount(String id) {
        id = reverseConvertId(id);
        return transactionRepository.getTransactionReport(id).getTotalAmount();
    }

    public String calculateNewTotal(String @NotNull[] @NotNull[] dataList) {
        BigDecimal total = new BigDecimal("0");
        for(String[] data : dataList) {
            total = total.add(new BigDecimal(data[6]));
        }
        return total.toString();
    }

    public boolean verifyReturnedItemDetails(@NotNull ProductReturnedDetail product) {
        if(product.getSold().equals("") || product.getProductCount().equals("")) {
            Toolkit.getDefaultToolkit().beep();
            return false;
        }
        double prodCount = Double.parseDouble(product.getProductCount());
        double count = Double.parseDouble(product.getCount());
        double sold = Double.parseDouble(product.getSold());
        if(prodCount > count || prodCount < 1 || sold < 0) {
            Toolkit.getDefaultToolkit().beep();
            return false;
        }
        return true;
    }

    public String calculateNewCredit(String oldCredit, String newCredit) {
        BigDecimal credit = new BigDecimal(oldCredit);
        credit = credit.multiply(BigDecimal.valueOf(-1));
        BigDecimal newTotal = new BigDecimal(newCredit);
        return credit.add(newTotal).toString();
    }

    public String negation(String num) {
       return new BigDecimal(num).multiply(BigDecimal.valueOf(-1)).toString();
    }

    @NotNull
    public String subtraction(Double a, Double b) {
        return String.valueOf(a - b);
    }
}
