package retail.shared.util.tablecreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static retail.shared.constant.Constant.*;

public interface CreateProductTable {

    default void createProductTable() {
        String query = "CREATE TABLE IF NOT EXISTS product (" +
                "id VARCHAR(255) NOT NULL PRIMARY KEY," +
                "description VARCHAR(255) NOT NULL DEFAULT ' '," +
                "price DECIMAL(30,4) NOT NULL," +
                "quantity_per_pieces INT NOT NULL DEFAULT 0," +
                "quantity_per_box DECIMAL(30,2) GENERATED ALWAYS AS (quantity_per_pieces / pieces_per_box) STORED," +
                 "pieces_per_box INT NOT NULL DEFAULT 0)";
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
                "no INT PRIMARY KEY AUTO_INCREMENT, prod_id VARCHAR(255) NOT NULL ," +
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

    // CREATE NULL PRODUCT REPORT :: id - user - date - date_time - total_credit
    default void createNullReport() {
        String createTable = "CREATE TABLE IF NOT EXISTS null_report(" +
                "id VARCHAR(255) PRIMARY KEY," +
                "user VARCHAR(255) NOT NULL," +
                "date DATE AS (DATE(date_time)) NOT NULL," +
                "date_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL)";
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CREATE NULL PRODUCT REPORT ITEM :: no. - id - product_id - expired_damage_quantity - credit
    default void createNullReportItem() {
        String query = "CREATE TABLE IF NOT EXISTS null_report_item (" +
                "no INT AUTO_INCREMENT PRIMARY KEY," +
                "id VARCHAR(255) NOT NULL," +
                "price DECIMAL(30,4) NOT NULL," +
                "quantity_per_pieces INT NOT NULL DEFAULT 0," +
                "quantity_per_box DECIMAL(30,2) GENERATED ALWAYS AS (quantity_per_pieces / pieces_per_box) STORED," +
                "pieces_per_box INT NOT NULL DEFAULT 0)";
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
