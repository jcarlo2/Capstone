package retail.controller.database;

import org.jetbrains.annotations.NotNull;
import retail.shared.pojo.ReturnTransactionReport;
import retail.shared.pojo.TransactionReportItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import static retail.shared.constant.Constant.*;

public class ReturnedTransactionDatabase {

    public void addReport(@NotNull ReturnTransactionReport report, ArrayList<TransactionReportItem> reportItems) {
        saveReturnReport(report);
        saveTransactionReportItem(report, reportItems);
    }

    private void saveReturnReport(ReturnTransactionReport report) {
        String query = "INSERT INTO returned_transaction_report(id,user,total_amount,total_credit,old_id) VALUES(?,?,?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, report.getId());
            preparedStatement.setString(2, report.getUser());
            preparedStatement.setBigDecimal(3, report.getTotalAmount());
            preparedStatement.setBigDecimal(4, report.getCredit());
            preparedStatement.setString(5, report.getOldId());
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveTransactionReportItem(@NotNull ReturnTransactionReport report, ArrayList<TransactionReportItem> reportItems) {
        String query = "INSERT INTO returned_transaction_item(product_id,price,sold,sold_total,discount_percentage" +
                ",discount_total,total_amount,unique_id) VALUES(?,?,?,?,?,?,?,?)";
        try {
            for(TransactionReportItem item : reportItems) {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, item.getProductId());
                preparedStatement.setDouble(2, item.getPrice());
                preparedStatement.setDouble(3, item.getSold());
                preparedStatement.setDouble(4, item.getSoldTotal());
                preparedStatement.setDouble(5, item.getDiscountPercentage());
                preparedStatement.setDouble(6, item.getDiscountAmount());
                preparedStatement.setBigDecimal(7, item.getTotalAmount());
                preparedStatement.setString(8, report.getId());
                preparedStatement.executeUpdate();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
