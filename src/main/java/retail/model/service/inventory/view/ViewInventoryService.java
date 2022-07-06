package retail.model.service.inventory.view;

import retail.model.service.Service;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.NullProductReport;
import retail.shared.pojo.InventoryItem;

import java.util.ArrayList;

public class ViewInventoryService implements Service {
    public ArrayList<DeliveryDetail> getAllDeliveryReport() {
        return delivery.getAllReport();
    }

    public ArrayList<NullProductReport> getAllNullReport() {
        return nullProduct.getAllReport();
    }

    public ArrayList<NullProductReport> getAllNullReportByDate(String start, String end) {
        return nullProduct.getAllReportByDate(start,end);
    }

    public ArrayList<DeliveryDetail> getAllDeliveryReportByDate(String start, String end) {
        return delivery.getAllReportByDate(start,end);
    }

    public ArrayList<InventoryItem> findDeliveryReportById(String id) {
        return delivery.findDeliveryReportById(id);
    }

    public ArrayList<InventoryItem> findAllNullReportById(String id) {
        return nullProduct.findAllNullReportItemById(id);
    }
}
