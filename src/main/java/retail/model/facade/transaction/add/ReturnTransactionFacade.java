package retail.model.facade.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.model.service.ReportCreator;
import retail.model.service.TableUtil;
import retail.model.service.transaction.ReturnTransactionService;
import retail.shared.custom.jtable.JTableTransaction;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class ReturnTransactionFacade {
    private final ReturnTransactionService service = new ReturnTransactionService();
    private final TableUtil tableUtil = new TableUtil();
    private final ReportCreator creator = new ReportCreator();

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
        return service.getReportItem(id);
    }

    public String[] getRowData(JTableTransaction topTable, int row) {
        return tableUtil.rowGrabber(topTable,row);
    }

    public TransactionItemDetail createItem(String[] data) {
        return creator.createReportItem(data);
    }

    public void fixNumberColumn(JTable table) {
        tableUtil.fixNumberColumn(table);
    }

    public ArrayList<TransactionItemDetail> getRowDataByNoneReason(JTable topTable) {
        String[][] dataList = tableUtil.tableGrabber(topTable);
        ArrayList<TransactionItemDetail> itemList = new ArrayList<>();
        for(String[] data : service.filterDataByReason(Objects.requireNonNull(dataList))) {
            if(data == null) break;
            itemList.add(creator.createReportItem(data));
        }
        return itemList;
    }

    public void removeRowWithReason(JTableTransaction topTable) {
        service.removeRowWithReason(topTable);
    }

    public TransactionItemDetail recoverItem(String @NotNull [] data,String reportId) {
        return service.recoverItem(data,reportId);
    }

    public String reverseConvertId(String id) {
        return service.reverseConvertId(id);
    }

    public String convertId(String id) {
        return service.convertId(id);
    }

    public String getReportTotalAmount(String id) {
        return service.getReportTotalAmount(id);
    }

    public String calculateNewTotal(JTableTransaction botTable) {
        return service.calculateNewTotal(tableUtil.tableGrabber(botTable));
    }
}
