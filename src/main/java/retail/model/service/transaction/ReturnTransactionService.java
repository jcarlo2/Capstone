package retail.model.service.transaction;

import org.jetbrains.annotations.NotNull;
import retail.model.service.Service;
import retail.shared.entity.NullProductReport;
import retail.shared.entity.NullReportItem;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;
import retail.shared.pojo.ProductReturnedDetail;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ReturnTransactionService implements Service {

    public ArrayList<TransactionDetail> getAllValidReport() {
        return transaction.getAllValidTransactionReport();
    }

    public ArrayList<TransactionDetail> getTransactionReportList() {
        return transaction.getAllTransactionReportList();
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
        return transaction.isReportExist(value);
    }

    public ArrayList<TransactionItemDetail> getAllReportItem(String id) {
        return transaction.getAllTransactionReportItem(id);
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
        return transaction.getTransactionReportById(id).getTotalAmount();
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

    public String subtraction(Double a, Double b) {
        return String.valueOf(a - b);
    }

    public String addition(Double a, Double b) {
        return String.valueOf(a + b);
    }

    public boolean verifyTableForSaving(String @NotNull[] @NotNull [] dataList) {
        for(String[] data : dataList) {
            String strData = data[data.length-1];
            if(Double.parseDouble(strData) > 0) return true;
        }
        return false;
    }

    public void invalidateReport(String id) {
        transaction.invalidateId(reverseConvertId(id));
    }

    public void save(TransactionDetail report, ArrayList<TransactionItemDetail> itemList) {
        transaction.addReport(report,itemList);
    }

    public void reflectItemToInventory(String[][] dataList, String id) {
        id = reverseConvertId(id);
        ArrayList<TransactionItemDetail> itemList = transaction.getAllTransactionReportItem(id);
        for(TransactionItemDetail item : itemList) {
            for (String[] strings : dataList) {
                if (strings[0].equals(item.getProductId())) {
                    double count = Double.parseDouble(item.getSold());
                    Double returned = Double.valueOf(strings[7]);
                    Double sold = Double.valueOf(strings[2]);
                    product.updateProductQuantity(item.getProductId(), count - (sold + returned));
                }
            }
        }
    }

    public boolean lessThanComparison(String a, String b) {
        return Double.parseDouble(a) < Double.parseDouble(b);
    }

    public String calculateNullTotal(@NotNull ArrayList<NullReportItem> itemList) {
        Double total = 0.0;
        for(NullReportItem item : itemList) {
            total += Double.parseDouble(item.getTotal());
        }
        return String.valueOf(total);
    }

    public void addTransactionNullReport(NullProductReport nullProductReport, ArrayList<NullReportItem> nullList) {
        nullProduct.addNullReport(nullProductReport,nullList);
    }
}
