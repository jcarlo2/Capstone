package retail.shared.customcomponent.jlist;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.shared.pojo.ProductReport;
import retail.shared.pojo.TransactionReport;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class CustomJList extends JList<String> {
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final DefaultListCellRenderer renderer = new DefaultListCellRenderer();

    public CustomJList() {
        setModel(listModel);
        setFont(new Font("SansSerif", Font.BOLD, 15));
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        setCellRenderer(renderer);
    }

    public void populateTransactionList(@NotNull ArrayList<TransactionReport> list) {
        getListModel().removeAllElements();
        for(TransactionReport report : list) {
            String dateTime = report.getTimestamp().toString().substring(0,16) ;
            String str =  dateTime + " - " +  report.getId();
            getListModel().addElement(str);
        }
    }

    public void populateInventoryList(@NotNull ArrayList<ProductReport> list ) {
        getListModel().removeAllElements();
        for(ProductReport report : list) {
            String str = report.getDate() + " - " + report.getId();
            getListModel().addElement(str);
        }
    }

    public ArrayList<String> getAllElement() {
        int count = getVisibleRowCount() - 1;
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<count;i++) {
            list.add(getModel().getElementAt(i));
        }
        return list;
    }
}


