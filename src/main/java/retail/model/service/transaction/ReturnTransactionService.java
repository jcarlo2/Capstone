package retail.model.service.transaction;

import org.jetbrains.annotations.NotNull;
import retail.model.repository.implementer.TransactionRepository;
import retail.shared.custom.jtable.JTableTransaction;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import javax.swing.table.DefaultTableModel;
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

    public ArrayList<TransactionItemDetail> getReportItem(String id) {
        return transactionRepository.getAllTransactionReportItem(id);
    }

    public String[][] filterDataByReason(String[][] dataList) {
        String[][] newDataList = new String[dataList.length][];
        int flag = 0;
        for (String[] strings : dataList) {
            if (strings[7].equals("--")) {
                newDataList[flag++] = strings;
            }
        }
        return newDataList;
    }

    public void removeRowWithReason(@NotNull JTableTransaction topTable) {
        int row = topTable.getRowCount();
        for(int i=0;i<row;i++) {
            if(topTable.getValueAt(i, 8).equals("--")) {
                ((DefaultTableModel)topTable.getModel()).removeRow(i);
                i = -1;
                row = topTable.getRowCount();
            }
        }
    }

    public TransactionItemDetail recoverItem(String @NotNull [] data, String id) {
        ArrayList<TransactionItemDetail> itemList = getReportItem(id);
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
}
