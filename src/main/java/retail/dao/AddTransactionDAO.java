package retail.dao;

import org.jetbrains.annotations.NotNull;
import retail.shared.pojo.TransactionReport;
import retail.shared.pojo.TransactionReportItem;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import static retail.shared.constant.Constant.*;

public class AddTransactionDAO {

    public boolean isReportExist(String id) {
        String query = "SELECT EXISTS (SELECT id FROM transaction_report WHERE id = ?)";
        boolean flag = false;
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()) {
                        flag = resultSet.getInt(1) == 1;
                    }
            }catch (Exception e) {
                e.printStackTrace();
            }
        return flag;
    }

    public ArrayList<TransactionReportItem> getAllTransactionReportItem(String id) {
        ArrayList<TransactionReportItem> itemList = new ArrayList<>();
        String query = "SELECT * FROM transaction_report_item WHERE unique_id = ?";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()) {
                        String productId = resultSet.getString("prod_id");
                        Double price = resultSet.getDouble("price");
                        Double sold = resultSet.getDouble("sold");
                        Double soldTotal = resultSet.getDouble("sold_total");
                        Double discountPercentage = resultSet.getDouble("discount_percentage");
                        Double discountAmount = resultSet.getDouble("discount_amount");
                        BigDecimal totalAmount = resultSet.getBigDecimal("total_amount");
                        itemList.add(new TransactionReportItem(productId,price,sold,soldTotal,
                        discountPercentage,discountAmount,totalAmount));
                    }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return itemList;
    }

    private void deleteReportItem(String id) {
        String query = "DELETE FROM transaction_report_item WHERE unique_id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteReport(String id) {
        String query = "DELETE FROM transaction_report WHERE id = ?";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,id);
                preparedStatement.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
            deleteReportItem(id);
    }

    public void changeIsDeletableStatus(String id) {
        String query = "UPDATE transaction_report SET is_deletable = NOT is_deletable WHERE id = ?";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,id);
                preparedStatement.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    public boolean isReportDeletable(@NotNull String id) {
        if(id.equals("")) return false;
        String query = "SELECT is_deletable FROM transaction_report WHERE id = ?";
        boolean isDeletable = false;
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) isDeletable = resultSet.getInt(1) == 1;
            }catch (Exception e) {
                e.printStackTrace();
            }
        return isDeletable;
    }

    public void addReport(@NotNull TransactionReport transactionReport, ArrayList<TransactionReportItem> transactionReportItem) {
        saveTransactionReport(transactionReport);
        saveTransactionReportItem(transactionReport, transactionReportItem);
    }

    private void saveTransactionReport(TransactionReport transactionReport) {
        String query = "INSERT INTO transaction_report(id,user,total_amount) VALUES(?,?,?)";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, transactionReport.getId());
                preparedStatement.setString(2, transactionReport.getUser());
                preparedStatement.setBigDecimal(3, transactionReport.getTotalAmount());
                preparedStatement.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void saveTransactionReportItem(@NotNull TransactionReport transactionReport, ArrayList<TransactionReportItem> transactionReportItem) {
        String query = "INSERT INTO transaction_report_item(prod_id,price,sold,sold_total,discount_percentage" +
         ",discount_amount,total_amount,unique_id) VALUES(?,?,?,?,?,?,?,?)";
            try {
                for(TransactionReportItem item : transactionReportItem) {
                    Connection connection = DriverManager.getConnection(URL,USER,PASS);
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, item.getProductId());
                    preparedStatement.setDouble(2, item.getPrice());
                    preparedStatement.setDouble(3, item.getSold());
                    preparedStatement.setDouble(4, item.getSoldTotal());
                    preparedStatement.setDouble(5, item.getDiscountPercentage());
                    preparedStatement.setDouble(6, item.getDiscountAmount());
                    preparedStatement.setBigDecimal(7, item.getTotalAmount());
                    preparedStatement.setString(8, transactionReport.getId());
                    preparedStatement.executeUpdate();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    public ArrayList<TransactionReport> getTransactionReportList() {
        ArrayList<TransactionReport> reportList = new ArrayList<>();
        String query = "SELECT * FROM transaction_report ORDER BY date_time DESC";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String user = resultSet.getString("user");
                        Date date = resultSet.getDate("date");
                        Timestamp timestamp = resultSet.getTimestamp("date_time");
                        BigDecimal totalAmount = resultSet.getBigDecimal("total_amount");
                        reportList.add(new TransactionReport(id,date,timestamp,user,totalAmount));
                    }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return reportList;
    }

    public TransactionReport getTransactionReport(String id) {
        String query = "SELECT * FROM transaction_report WHERE id = ?";
        TransactionReport report = null;
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                id = resultSet.getString("id");
                String user = resultSet.getString("user");
                Date date = resultSet.getDate("date");
                Timestamp timestamp = resultSet.getTimestamp("date_time");
                BigDecimal totalAmount = resultSet.getBigDecimal("total_amount");
                report = new TransactionReport(id,date,timestamp,user,totalAmount);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }
}
