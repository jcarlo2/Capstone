package retail.model.service;

import org.jetbrains.annotations.NotNull;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.NullProductReport;
import retail.shared.entity.TransactionDetail;

import java.util.ArrayList;

public class Finder {

    public ArrayList<TransactionDetail> findMatchingTransactionReport(String str, @NotNull ArrayList<TransactionDetail> reportList) {
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

    public ArrayList<DeliveryDetail> findMatchingDeliveryReport(String str, @NotNull ArrayList<DeliveryDetail> reportList) {
        ArrayList<DeliveryDetail> reports = new ArrayList<>();
        for (DeliveryDetail report : reportList) {
            String id = report.getId().toLowerCase();
            String date = report.getDate().toLowerCase();
            String combine = date + " - " + id;
            if (combine.contains(str.toLowerCase())) {
                reports.add(report);
            }
        }
        return reports;
    }

    public ArrayList<NullProductReport> findMatchingNullReport(String str, @NotNull ArrayList<NullProductReport> reportList) {
        ArrayList<NullProductReport> reports = new ArrayList<>();
        for (NullProductReport report : reportList) {
            String id = report.getId().toLowerCase();
            String date = report.getDate().toLowerCase();
            String combine = date + " - " + id;
            if (combine.contains(str.toLowerCase())) {
                reports.add(report);
            }
        }
        return reports;
    }
}
