package retail.customcomponent.jlist;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.model.ProductReport;
import retail.model.TransactionReport;

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

    public void populateTransactionList(@NotNull ArrayList<TransactionReport> list) {
        getModel().removeAllElements();
        for(TransactionReport report : list) {
            String str = report.getDate() + " - " +  report.getId();
            getModel().addElement(str);
        }
    }

    public void populateInventoryList(@NotNull ArrayList<ProductReport> list ) {
        getModel().removeAllElements();
        for(ProductReport report : list) {
            String str = report.getDate() + " - " + report.getId();
            getModel().addElement(str);
        }
    }
}


