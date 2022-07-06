package retail.model.facade.inventory.view;

import retail.model.service.inventory.view.ViewInventoryService;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.NullProductReport;
import retail.shared.pojo.InventoryItem;

import java.util.ArrayList;

public class ViewInventoryFacade {
    private final ViewInventoryService service = new ViewInventoryService();

    public ArrayList<NullProductReport> getAllNullReport() {
        return service.getAllNullReport();
    }

    public ArrayList<DeliveryDetail> getAllDeliveryReport() {
        return service.getAllDeliveryReport();
    }

    public ArrayList<NullProductReport> getAllNullReportByDate(String start, String end) {
        return service.getAllNullReportByDate(start,end);
    }

    public ArrayList<DeliveryDetail> getAllDeliveryReportByDate(String start, String end) {
        return service.getAllDeliveryReportByDate(start,end);
    }

    public ArrayList<InventoryItem> findDeliveryReportById(String id) {
        return service.findDeliveryReportById(id);
    }

    public ArrayList<InventoryItem> findNullReportById(String id) {
        return service.findAllNullReportById(id);
    }
}
