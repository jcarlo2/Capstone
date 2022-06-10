package retail.shared.custom.jlist;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.TransactionDetail;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class CustomJList extends JList<String> implements ListHLC {
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final DefaultListCellRenderer renderer = new DefaultListCellRenderer();

    public CustomJList() {
        setModel(listModel);
        setFont(new Font("SansSerif", Font.BOLD, 15));
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        setCellRenderer(renderer);
    }

    @Override
    public void populateTransactionList(@NotNull ArrayList<TransactionDetail> list) {
        getListModel().removeAllElements();
        for(TransactionDetail report : list) {
            String dateTime = report.getTimestamp().substring(0,16);
            String str =  dateTime + " - " +  report.getId();
            getListModel().addElement(str);
        }
    }

    @Override
    public void populateDeliveryList(@NotNull ArrayList<DeliveryDetail> list ) {
        getListModel().removeAllElements();
        for(DeliveryDetail report : list) {
            String str = report.getDate() + " - " + report.getId();
            getListModel().addElement(str);
        }
    }

    @Override
    public ArrayList<String> getAllElement() {
        int count = listModel.size();
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<count;i++) {
            list.add(getModel().getElementAt(i));
        }
        return list;
    }

    @Override
    public boolean isNotSameTransactionList(@NotNull ArrayList<TransactionDetail> reportList) {
        int count = listModel.getSize();
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<count;i++) {
            list.add(listModel.getElementAt(i).substring(19, 37));
        }
        for(TransactionDetail report : reportList) {
            if(!list.contains(report.getId())) return true;
        }
        return list.size() != reportList.size();
    }

    @Override
    public boolean isNotSameDeliveryList(@NotNull ArrayList<DeliveryDetail> reportList) {
        int count = listModel.getSize();
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<count;i++) {
            list.add(listModel.getElementAt(i));
        }
        for(DeliveryDetail report : reportList) {
            if(!list.contains(report.getId())) return true;
        }
        return list.size() != reportList.size();
    }
}


