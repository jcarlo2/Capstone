package retail.model.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TableUtil {
    public String @Nullable [] rowGrabber(@NotNull JTable table, int row) {
        String[] data = new String[table.getColumnCount() - 1];
        if(row == -1) return null;
        for(int i=0;i< data.length;i++) {
            data[i] = table.getValueAt(row, i + 1).toString();
        }
        return data;
    }

    public String[][] tableGrabber(@NotNull JTable table) {
        final int row = table.getRowCount();
        final int column = table.getColumnCount() - 1;
        String[][] dataList = new String[row][column];
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                dataList[i][j] = table.getValueAt(i, j + 1).toString();
            }
        }
        return dataList;
    }

    public void fixNumberColumn(@NotNull JTable table) {
        int row = table.getRowCount();
        for(int i=0;i<row;i++) {
            table.setValueAt(i + 1, i, 0);
        }
    }
}
