package retail.shared.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static retail.shared.constant.Constant.*;

public interface CreateTransactionTable {
    default void createSalesReportTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS transaction_report(" +
                                        "id VARCHAR(255) PRIMARY KEY," +
                                        "user VARCHAR(255) NOT NULL," +
                                        "date DATE AS (DATE(date_time)) NOT NULL," +
                                        "date_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL," +
                                        "is_returned BOOLEAN NOT NULL DEFAULT FALSE," +
                                         "total_amount DECIMAL(30,4) NOT NULL,reason VARCHAR(255) NOT NULL, action VARCHAR(255) NOT NULL)";
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void createSalesReportTableItem() {
        String createTable = "CREATE TABLE IF NOT EXISTS transaction_report_item (" +
                                        "num INT PRIMARY KEY AUTO_INCREMENT, " +
                                        "prod_id VARCHAR(255) NOT NULL," +
                                        "price DECIMAL(30,4) NOT NULL, " +
                                        "sold DECIMAL(30,4) NOT NULL, " +
                                        "sold_total DECIMAL(30,4) NOT NULL," +
                                        "discount_percentage DECIMAL(30,4) NOT NULL," +
                                        "discount_amount DECIMAL(30,4) NOT NULL," +
                                        "total_amount DECIMAL(30,4) NOT NULL,"+
                                        "unique_id VARCHAR(255) NOT NULL," +
                                         "is_returned BOOLEAN NOT NULL DEFAULT FALSE)";
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
