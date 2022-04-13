package retail.controller.database;

import org.jetbrains.annotations.NotNull;
import retail.model.TransactionReport;
import retail.model.TransactionReportItem;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import static retail.util.constant.Constant.*;

public class TransactionController {

    public boolean ifReportExist(String id) {
        String query = "SELECT EXISTS (SELECT id FROM sales_report WHERE id = ?)";
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

    public ArrayList<TransactionReportItem> getAllSalesReportItem(String id) {
        ArrayList<TransactionReportItem> itemList = new ArrayList<>();
        String query = "SELECT * FROM sales_report_item WHERE unique_id = ?";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()) {
                        setItemList(itemList,resultSet);
                    }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return itemList;
    }

    private void setItemList(ArrayList<TransactionReportItem> itemList, ResultSet resultSet) {
        try {
            String prod_id = resultSet.getString("prod_id");
            BigDecimal price = resultSet.getBigDecimal("price");
            BigDecimal sold = resultSet.getBigDecimal("sold");
            BigDecimal sold_total = resultSet.getBigDecimal("sold_total");
            BigDecimal damaged_expired = resultSet.getBigDecimal("damaged_expired");
            BigDecimal damaged_expired_total = resultSet.getBigDecimal("damaged_expired_total");
            BigDecimal total_amount = resultSet.getBigDecimal("total_amount");

            itemList.add(new TransactionReportItem(prod_id,price,sold,
                    sold_total,damaged_expired,
                    damaged_expired_total,total_amount));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteReportItem(String id) {
        String query = "DELETE FROM sales_report_item WHERE unique_id = ?";
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
        String query = "DELETE FROM sales_report WHERE id = ?";
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
        String query = "UPDATE sales_report SET is_deletable = NOT is_deletable WHERE id = ?";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,id);
                preparedStatement.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    public boolean isReportDeletable(String id) {
        if(id.equals("")) return false;
        String query = "SELECT is_deletable FROM sales_report WHERE id = ?";
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
        saveSalesReport(transactionReport);
        saveSalesReportItem(transactionReport, transactionReportItem);
    }

    private void saveSalesReport(TransactionReport transactionReport) {
        String query = "INSERT INTO sales_report(id,user) VALUES(?,?)";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, transactionReport.getId());
                preparedStatement.setString(2, transactionReport.getUser());
                preparedStatement.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void saveSalesReportItem(@NotNull TransactionReport transactionReport, ArrayList<TransactionReportItem> transactionReportItem) {
        String query = "INSERT INTO sales_report_item(prod_id,price,sold,sold_total" +
                            ",damaged_expired,damaged_expired_total,total_amount,unique_id) VALUES(?,?,?,?,?,?,?,?)";

            try {
                for(TransactionReportItem item : transactionReportItem) {
                    Connection connection = DriverManager.getConnection(URL,USER,PASS);
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, item.getProductId());
                    preparedStatement.setBigDecimal(2, item.getPrice());
                    preparedStatement.setBigDecimal(3, item.getSold());
                    preparedStatement.setBigDecimal(4, item.getSoldTotal());
                    preparedStatement.setBigDecimal(5, item.getExpDamaged());
                    preparedStatement.setBigDecimal(6, item.getExpDamagedTotal());
                    preparedStatement.setBigDecimal(7, item.getTotalAmount());
                    preparedStatement.setString(8, transactionReport.getId());
                    preparedStatement.executeUpdate();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    public ArrayList<TransactionReport> getSalesReportList() {
        ArrayList<TransactionReport> reportList = new ArrayList<>();
        String query = "SELECT * FROM sales_report";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String user = resultSet.getString("user");
                        Date date = resultSet.getDate("date");
                        reportList.add(new TransactionReport(id,date,user));
                    }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return reportList;
    }
}
