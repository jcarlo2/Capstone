package retail.view.main.panel.rightpanel.sales;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CompareSalesReport extends JPanel {
    private final JPanel totalSalesReport = new JPanel();

    private final DefaultTableModel firstModel = new DefaultTableModel(0,6);
    private final JTable firstTable = new JTable(firstModel);
    private final JScrollPane firstScroll = new JScrollPane(firstTable);

    private final DefaultTableModel secondModel = new DefaultTableModel(0,6);
    private final JTable secondTable = new JTable(secondModel);
    private final JScrollPane secondScroll = new JScrollPane(secondTable);

    private final JSplitPane splitPane = new JSplitPane
            (JSplitPane.HORIZONTAL_SPLIT,firstScroll,secondScroll);

    public CompareSalesReport() {
        setLayout(new BorderLayout());

        setFirstAndSecondTableColumnName();
        setFirstTable();
        setSecondTable();

        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.5);
        splitPane.setEnabled(false);

        add(splitPane,BorderLayout.CENTER);
        setVisible(true);
    }

    private void setFirstTable() {
        firstTable.getTableHeader().setResizingAllowed(false);
        firstTable.getTableHeader().setReorderingAllowed(false);

        firstTable.getColumnModel().getColumn(0).setMinWidth(35);
        firstTable.getColumnModel().getColumn(0).setMaxWidth(35);

        firstTable.getColumnModel().getColumn(2).setMinWidth(70);
        firstTable.getColumnModel().getColumn(2).setMaxWidth(70);

        firstTable.getColumnModel().getColumn(3).setMinWidth(50);
        firstTable.getColumnModel().getColumn(3).setMaxWidth(50);

        firstTable.getColumnModel().getColumn(4).setMinWidth(130);
        firstTable.getColumnModel().getColumn(4).setMaxWidth(130);

        firstTable.getColumnModel().getColumn(5).setMinWidth(120);
        firstTable.getColumnModel().getColumn(5).setMaxWidth(120);
    }

    private void setSecondTable() {
        secondTable.getTableHeader().setResizingAllowed(false);
        secondTable.getTableHeader().setReorderingAllowed(false);

        secondTable.getColumnModel().getColumn(0).setMinWidth(35);
        secondTable.getColumnModel().getColumn(0).setMaxWidth(35);

        secondTable.getColumnModel().getColumn(2).setMinWidth(70);
        secondTable.getColumnModel().getColumn(2).setMaxWidth(70);

        secondTable.getColumnModel().getColumn(3).setMinWidth(50);
        secondTable.getColumnModel().getColumn(3).setMaxWidth(50);

        secondTable.getColumnModel().getColumn(4).setMinWidth(130);
        secondTable.getColumnModel().getColumn(4).setMaxWidth(130);

        secondTable.getColumnModel().getColumn(5).setMinWidth(120);
        secondTable.getColumnModel().getColumn(5).setMaxWidth(120);
    }

    private void setFirstAndSecondTableColumnName() {
        String[] columnName = {"No.", "Product ID", "Price", "Sold" , "Damaged/Expired","Total"};
        int COLUMN_NUMBER = 6;
            for(int i=0;i<COLUMN_NUMBER;i++) {
                firstTable.getColumnModel().getColumn(i).setHeaderValue(columnName[i]);
                secondTable.getColumnModel().getColumn(i).setHeaderValue(columnName[i]);
            }
    }
}
