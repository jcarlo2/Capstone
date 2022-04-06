package retail.controller.database;

import org.jetbrains.annotations.NotNull;
import retail.dto.SalesReportObject;
import retail.dto.SalesReportItemObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static retail.constant.ConstantString.*;

public class SalesReportController {

    public boolean ifReportExist(String id) {
        String query = "SELECT EXISTS (SELECT id FROM product WHERE id = ?)";
        boolean flag = false;
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()) {
                        flag = resultSet.getInt("id") == 1;
                    }
            }catch (Exception e) {
                e.printStackTrace();
            }
        return flag;
    }

    public boolean addReport(@NotNull SalesReportObject salesReport, SalesReportItemObject[] salesReportItem) {
        if(ifReportExist(salesReport.getId())) return false;

        saveSalesReport(salesReport);
        saveSalesReportItem(salesReport,salesReportItem);
        return true;
    }

    public void saveSalesReport(SalesReportObject salesReport) {
        String query = "INSERT INTO sales_report VALUES(?,?,?)";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,salesReport.getId());
                preparedStatement.setString(2,salesReport.getDate());
                preparedStatement.setString(3,salesReport.getUser());
                preparedStatement.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void saveSalesReportItem(@NotNull SalesReportObject salesReport, SalesReportItemObject[] salesReportItem) {
        String query = "INSERT INTO sales_report_item(prod_id,price,sold,sold_total" +
                            ",damaged_expired,damaged_expired_total,total_amount,unique_id) VALUES(?,?,?,?,?,?,?,?)";
        String uniqueID = generateUniqueID(salesReport.getId(),salesReport.getDate());
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
                    preparedStatement.setString(7,uniqueID);
                    preparedStatement.executeUpdate();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    private @NotNull String generateUniqueID(String id,String date) {
        return id + ":" + date;
    }
}
