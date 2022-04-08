package retail.view.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static retail.constant.Constant.*;

public interface CreateProductTable {
    default void createProductTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS product (" +
                "id VARCHAR(255) NOT NULL PRIMARY KEY," +
                "description VARCHAR(255) NOT NULL," +
                "price DECIMAL(30,4) NOT NULL," +
                "quantity_per_pieces INT NOT NULL," +
                "quantity_per_box DECIMAL(30,2) NOT NULL," +
                 "pieces_per_box INT NOT NULL DEFAULT 1)";
                try{
                    Connection connection = DriverManager.getConnection(URL,USER,PASS);
                    PreparedStatement preparedStatement = connection.prepareStatement(createTable);
                    preparedStatement.executeUpdate();
                }catch (Exception e) {
                    e.printStackTrace();
                }
    }
}
