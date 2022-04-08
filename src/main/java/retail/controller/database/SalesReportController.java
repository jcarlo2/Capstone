package retail.controller.database;

import org.jetbrains.annotations.NotNull;
import retail.model.SalesReportObject;
import retail.model.SalesReportItemObject;

import java.sql.*;
import java.util.ArrayList;

import static retail.constant.Constant.*;

public class SalesReportController {

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

    public void addReport(@NotNull SalesReportObject salesReport, ArrayList<SalesReportItemObject> salesReportItem) {
        saveSalesReport(salesReport);
        saveSalesReportItem(salesReport,salesReportItem);
    }

    private void saveSalesReport(SalesReportObject salesReport) {
        String query = "INSERT INTO sales_report(id,user) VALUES(?,?)";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,salesReport.getId());
                preparedStatement.setString(2,salesReport.getUser());
                preparedStatement.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void saveSalesReportItem(@NotNull SalesReportObject salesReport, ArrayList<SalesReportItemObject> salesReportItem) {
        String query = "INSERT INTO sales_report_item(prod_id,price,sold,sold_total" +
                            ",damaged_expired,damaged_expired_total,total_amount,unique_id) VALUES(?,?,?,?,?,?,?,?)";

            try {
                for(SalesReportItemObject item : salesReportItem) {
                    Connection connection = DriverManager.getConnection(URL,USER,PASS);
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, item.getProductId());
                    preparedStatement.setBigDecimal(2, item.getPrice());
                    preparedStatement.setBigDecimal(3, item.getSold());
                    preparedStatement.setBigDecimal(4, item.getSoldTotal());
                    preparedStatement.setBigDecimal(5, item.getExpDamaged());
                    preparedStatement.setBigDecimal(6, item.getExpDamagedTotal());
                    preparedStatement.setBigDecimal(7, item.getTotalAmount());
                    preparedStatement.setString(8, salesReport.getId());
                    preparedStatement.executeUpdate();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    public ArrayList<SalesReportObject> getSalesReportList() {
        ArrayList<SalesReportObject> reportList = new ArrayList<>();
        String query = "SELECT * FROM sales_report";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String user = resultSet.getString("user");
                        Date date = resultSet.getDate("date");
                        reportList.add(new SalesReportObject(id,date,user));
                    }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return reportList;
    }
}
