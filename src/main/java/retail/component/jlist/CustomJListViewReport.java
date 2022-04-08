package retail.component.jlist;

import lombok.Getter;
import retail.controller.database.SalesReportController;
import retail.model.SalesReportObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class CustomJListViewReport extends JList<String> {
    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final DefaultListCellRenderer renderer = new DefaultListCellRenderer();
    private final SalesReportController controller = new SalesReportController();

    public CustomJListViewReport() {
        setModel(model);
        setFont(new Font("SansSerif", Font.BOLD, 15));
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        setCellRenderer(renderer);
        populateSalesReportList();
    }

    public void populateSalesReportList() {
        getModel().removeAllElements();
        ArrayList<SalesReportObject> list = controller.getSalesReportList();
        for(SalesReportObject report : list) {
            String str = report.getId() + " - " + report.getDate() + " - " + report.getUser();
            getModel().addElement(str);
        }
    }
}


