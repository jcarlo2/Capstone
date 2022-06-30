package retail.model.repository.implementation;

import retail.shared.entity.NullProductReport;
import retail.shared.entity.NullReportItem;

import java.util.ArrayList;

public interface NullProduct {
    void addNullProductReport(NullProductReport report);

    void addNullReportItem(ArrayList<NullReportItem> itemList);

    default void addNullReport(NullProductReport report,ArrayList<NullReportItem> itemList) {
        addNullProductReport(report);
        addNullReportItem(itemList);
    }

    boolean isReportExist(String formatId);
}
