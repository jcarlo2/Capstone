package retail.model.service.inventory.view;

import retail.model.service.Service;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.NullProductReport;

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
}
