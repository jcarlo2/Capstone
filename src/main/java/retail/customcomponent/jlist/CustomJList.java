package retail.customcomponent.jlist;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.controller.database.SalesReportController;
import retail.model.ProductReport;
import retail.model.SalesReport;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class CustomJList extends JList<String> {
    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final DefaultListCellRenderer renderer = new DefaultListCellRenderer();

    public CustomJList() {
        setModel(model);
        setFont(new Font("SansSerif", Font.BOLD, 15));
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        setCellRenderer(renderer);
    }

    public void populateSalesList(@NotNull ArrayList<SalesReport> list) {
        getModel().removeAllElements();
        for(SalesReport report : list) {
            String str = report.getId() + " - " + report.getDate();
            getModel().addElement(str);
        }
    }

    public void populateInventoryList(@NotNull ArrayList<ProductReport> list ) {
        getModel().removeAllElements();
        for(ProductReport report : list) {
            String str = report.getId() + " - " + report.getDate();
            getModel().addElement(str);
        }
    }
}

