package retail.controller.logic;

import org.jetbrains.annotations.NotNull;
import retail.dao.AddTransactionDAO;
import retail.dao.ProductDAO;
import retail.model.User;
import retail.shared.customcomponent.jcombobox.JComboBoxProduct;
import retail.shared.customcomponent.jtable.JTableTransaction;
import retail.shared.customcomponent.jtextfield.CustomJTextField;
import retail.shared.pojo.Product;
import retail.shared.pojo.TransactionReport;
import retail.shared.pojo.TransactionReportItem;
import retail.shared.util.calculate.ValidNumberChecker;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ITransactionAdd extends ValidNumberChecker {

    default void subtractItemsToInventory(JTableTransaction transactionTable, ProductDAO database) {
        for(TransactionReportItem item : getAllItemReport(transactionTable)) {
            Product product = database.get(item.getProductId());
            database.updateProductQuantity(product.getId(),item.getSold() * -1);
        }
    }

    default TransactionReportItem createTransactionReportItem(String @NotNull [] data) {
        return new TransactionReportItem(data[0],Double.parseDouble(data[1]),
                Double.parseDouble(data[2]),Double.parseDouble(data[3]),
                Double.parseDouble(data[4]),Double.parseDouble(data[5]),
                new BigDecimal(data[6]));
    }

    default boolean isAllFieldValid(@NotNull CustomJTextField discountPercentage,
                                    CustomJTextField discountTotal, CustomJTextField sold, CustomJTextField soldTotal) {
        return isValidNumber(discountPercentage.getText()) && isValidNumber(discountTotal.getText())
                && isValidNumber(sold.getText()) && isValidNumber(soldTotal.getText());
    }

    default @NotNull ArrayList<TransactionReportItem> getAllItemReport(@NotNull JTableTransaction transactionTable) {
        ArrayList<TransactionReportItem> itemReport = new ArrayList<>();
        int ROW = transactionTable.getRowCount();
        int COLUMN = transactionTable.getColumnCount();
        for(int i=0;i<ROW;i++) {
            String[] data = new String[8];
            for(int j=0;j<COLUMN;j++) {
                data[j] = transactionTable.getModel().getValueAt(i,j).toString();
            }
            itemReport.add(new TransactionReportItem(data[1],Double.parseDouble(data[2]),Double.parseDouble(data[3])
                    ,Double.parseDouble(data[4]),Double.parseDouble(data[5]),
                    Double.parseDouble(data[6]),new BigDecimal(data[7])));
        }
        return itemReport;
    }

    default @NotNull TransactionReport createTransactionReport(@NotNull CustomJTextField reportId,
                                                                @NotNull User user,
                                                                @NotNull CustomJTextField totalAmount) {
        String id = reportId.getText();
        String lastName = user.getLastName();
        Date date = Date.valueOf(LocalDate.now());
        BigDecimal total = new BigDecimal(totalAmount.getText());

        return new TransactionReport(id, date,null,lastName,total);
    }

    default void autoCalculateTotalAmount(@NotNull JTableTransaction table, CustomJTextField totalAmount) {
        if(table.getRowCount() < 1) return;
        BigDecimal num = new BigDecimal("0");
        for(int i = 0; i< table.getRowCount(); i++) {
            String value = (String) table.getValueAt(i,7);
            num = num.add(new BigDecimal(value));
        }
        totalAmount.setText(num.toString());
    }



    default void fixNumberColumn(@NotNull JTableTransaction table) {
        for(int i = 0; i< table.getRowCount(); i++) {
            table.setValueAt(i+1, i,0);
        }
    }

    default String @NotNull [] getFieldData(JComboBoxProduct id,
                                            CustomJTextField price,
                                            CustomJTextField sold,
                                            CustomJTextField soldTotal,
                                            CustomJTextField discountPercentage,
                                            CustomJTextField discountTotal) {
        String[] data = new String[7];
        data[0] = (String) id.getSelectedItem();
        data[1] = price.getText();
        data[2] = sold.getText();
        data[3] = soldTotal.getText();
        data[4] = discountPercentage.getText();
        data[5] = discountTotal.getText();
        data[6] = String.valueOf(new BigDecimal(soldTotal.getText()).subtract(new BigDecimal(discountTotal.getText())));
        return data;
    }

    default @NotNull String generateReportId(AddTransactionDAO database) {
        long id;
        String formatId = "";
        boolean flag = true;
        while(flag) {
            id = (long) (Math.random() * 1000000000000L);
            formatId = String.format("%013d",id);
            if(!database.isReportExist(formatId)) flag = false;
        }
        return "TR" + formatId + "-A0";
    }
}
