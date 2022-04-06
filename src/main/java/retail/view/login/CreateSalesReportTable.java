package retail.view.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static retail.constant.ConstantString.*;

public interface CreateSalesReportTable {
    default void createSalesReportTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS sales_report (" +
                "id VARCHAR(255) NOT NULL PRIMARY KEY," +
                "date DATE NOT NULL, user VARCHAR(255) NOT NULL)";
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void createSalesReportTableItem() {
        String createTable = "CREATE TABLE IF NOT EXISTS sales_report_item (" +
                "num INT PRIMARY KEY AUTO_INCREMENT, prod_id VARCHAR(255) NOT NULL," +
                "price DECIMAL(30,4) NOT NULL, sold DECIMAL(30,4) NOT NULL, sold_total DECIMAL(30,4) NOT NULL, " +
                 "damaged_expired DECIMAL(30,4) NOT NULL, damaged_expired_total DECIMAL(30,4) NOT NULL, " +
                  "total_amount DECIMAL(30,4) NOT NULL, unique_id VARCHAR(255) NOT NULL)";
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
