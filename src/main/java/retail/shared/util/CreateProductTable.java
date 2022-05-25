package retail.shared.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static retail.shared.constant.Constant.*;

public interface CreateProductTable {

    default void createProductTable() {
        String query = "CREATE TABLE IF NOT EXISTS product (" +
                "id VARCHAR(255) NOT NULL PRIMARY KEY," +
                "description VARCHAR(255) NOT NULL," +
                "price DECIMAL(30,4) NOT NULL," +
                "quantity_per_pieces INT NOT NULL," +
                "quantity_per_box DECIMAL(30,2) NOT NULL," +
                 "pieces_per_box INT NOT NULL DEFAULT 1)";
                try{
                    Connection connection = DriverManager.getConnection(URL,USER,PASS);
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.executeUpdate();
                }catch (Exception e) {
                    e.printStackTrace();
                }
    }

    default void createProductReport() {
        String query = "CREATE TABLE IF NOT EXISTS product_report(" +
                "id VARCHAR(255) NOT NULL PRIMARY KEY," +
                "user VARCHAR(255) NOT NULL," +
                "date DATE  AS (DATE(date_time)) NOT NULL," +
                "date_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL)";
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void createProductReportItem() {
        String query = "CREATE TABLE IF NOT EXISTS product_report_item(" +
                "prod_id VARCHAR(255) NOT NULL PRIMARY KEY," +
                "price DECIMAL(30,4) NOT NULL," +
                "quantity_pieces DECIMAL(30,4) NOT NULL," +
                "quantity_box DECIMAL(30,4) NOT NULL," +
                "pieces_per_box DECIMAL(30,4) NOT NULL," +
                 "unique_id VARCHAR(255) NOT NULL)";
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
