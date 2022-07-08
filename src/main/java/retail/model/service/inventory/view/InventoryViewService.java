package retail.model.service.inventory.view;

import retail.model.service.Service;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.DeliveryItemDetail;
import retail.shared.entity.NullProductReport;
import retail.shared.entity.NullReportItem;

import java.util.ArrayList;

public class InventoryViewService implements Service {
    public ArrayList<DeliveryDetail> findAllDeliveryReport() {
        return delivery.findAllReport();
    }

    public ArrayList<NullProductReport> findAllNullReport() {
        return nullProduct.findAllReport();
    }

    public ArrayList<NullProductReport> findAllNullReportByDate(String start, String end) {
        return nullProduct.findAllReportByDate(start,end);
    }

    public ArrayList<DeliveryDetail> findAllDeliveryReportByDate(String start, String end) {
        return delivery.findAllReportByDate(start,end);
    }

    public ArrayList<NullProductReport> findNullReportByLink(String search) {
        return nullProduct.findNullReportByLink(search);
    }

    public String substringReportId(String id) {
        return id.substring(19,37);
    }

    public ArrayList<NullReportItem> findAllNullReportItemById(String id) {
        return nullProduct.findAllNullReportItemById(id);
    }

    public ArrayList<DeliveryItemDetail> findAllDeliveryItemById(String id) {
        return delivery.findAllDeliveryItemById(id);
    }

    public String findNullReportTotal(String id) {
        return nullProduct.findNullReportById(id).getTotal();
    }

    public String findDeliveryReportTotal(String id) {
        return delivery.findDeliveryReportById(id).getTotal();
    }
}
