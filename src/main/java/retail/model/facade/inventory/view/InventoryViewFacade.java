package retail.model.facade.inventory.view;

import retail.model.facade.Facade;
import retail.model.service.inventory.view.InventoryViewService;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.NullProductReport;

import java.util.ArrayList;

public class InventoryViewFacade implements Facade {
    private final InventoryViewService service = new InventoryViewService();

    public ArrayList<DeliveryDetail> findAllDeliveryReport() {
        return service.findAllDeliveryReport();
    }

    public ArrayList<NullProductReport> findAllNullReport() {
        return service.findAllNullReport();
    }

    public ArrayList<NullProductReport> findAllNullReportByDate(String start, String end) {
        return service.findAllNullReportByDate(start,end);
    }

    public ArrayList<DeliveryDetail> findAllDeliveryReportByDate(String start, String end) {
        return service.findAllDeliveryReportByDate(start,end);
    }

    public ArrayList<DeliveryDetail> findAllDeliveryReportById(String search) {
        ArrayList<DeliveryDetail> reportList = service.findAllDeliveryReport();
        return finder.findMatchingDeliveryReport(search,reportList);
    }

    public ArrayList<NullProductReport> findAllNullReportById(String search) {
        ArrayList<NullProductReport> reportList = service.findAllNullReport();
        return finder.findMatchingNullReport(search,reportList);
    }

    public ArrayList<NullProductReport> findNullReportByLink(String search) {
        return service.findNullReportByLink(search);
    }
}
