package retail.model.repository.implementation;

import retail.shared.entity.NullProductReport;
import retail.shared.entity.NullReportItem;
import retail.shared.pojo.InventoryItem;

import java.util.ArrayList;

public interface NullProduct {
    void addNullProductReport(NullProductReport report);

    void addNullReportItem(ArrayList<NullReportItem> itemList);

    default void addNullReport(NullProductReport report,ArrayList<NullReportItem> itemList) {
        addNullProductReport(report);
        addNullReportItem(itemList);
    }

    boolean isReportExist(String id);

    ArrayList<NullProductReport> findAllReport();

    ArrayList<NullProductReport> findAllReportByDate(String start, String end);

    ArrayList<InventoryItem> findAllNullReportItemById(String id);

    ArrayList<NullProductReport> findNullReportByLink(String search);
}
