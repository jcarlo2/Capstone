package retail.model.facade.transaction.view;

import retail.model.facade.Facade;
import retail.model.service.transaction.view.ViewTransactionService;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import java.util.ArrayList;

public class ViewTransactionFacade implements Facade {
    private final ViewTransactionService service = new ViewTransactionService();

    public ArrayList<TransactionDetail> getAllReport() {
        return service.getAllReport();
    }

    public ArrayList<TransactionDetail> getAllReportByDate(String start, String end) {
        return service.getAllReportByDate(start,end);
    }

    public ArrayList<TransactionDetail> getAllValidReport() {
        return service.getAllValidReport();
    }

    public ArrayList<TransactionDetail> getAllValidReportByDate(String start, String end) {
        return service.getAllValidReportByDate(start,end);
    }

    public ArrayList<TransactionDetail> findAllValidReportByString(String str) {
        ArrayList<TransactionDetail> reportList = service.getAllValidReport();
        return finder.findMatchingTransactionReport(str,reportList);
    }

    public ArrayList<TransactionDetail> findAllReportByString(String str) {
        ArrayList<TransactionDetail> reportList = service.getAllReport();
        return finder.findMatchingTransactionReport(str,reportList);
    }

    public String[] getIdList(String id) {
        return service.getIdList(id);
    }

    public void delete(String...id) {
        service.delete(id);
    }

    public boolean isValid(String id) {
        return service.isValid(id);
    }

    public String convertListSelectedItem(String id) {
        return service.convertListSelectedItem(id);
    }

    public ArrayList<TransactionItemDetail> getAllReportItem(String id) {
        return service.getAllReportItem(id);
    }

    public String getReportPrice(String id) {
        return service.getReportPrice(id);
    }

    public void revalidate(String id) {
        service.revalidate(id);
    }
}
