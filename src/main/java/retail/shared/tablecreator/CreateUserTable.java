package retail.shared.tablecreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static retail.shared.constant.Constant.PASS;
import static retail.shared.constant.Constant.URL;
import static retail.shared.constant.Constant.USER;

public interface CreateUserTable {
     default void createEmployeeTableAndAdminAccount() {
        String admin = "INSERT IGNORE INTO employee VALUES(?,?,?,?)";
        String query = "CREATE TABLE IF NOT EXISTS employee(" +
                                        "id INT NOT NULL PRIMARY KEY," +
                                        "last_name VARCHAR(255) NOT NULL," +
                                        "first_name VARCHAR(255) NOT NULL," +
                                        "password VARCHAR(255) NOT NULL)";
        try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                // CREATE EMPLOYEE TABLE
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                // CREATE TABLE
                preparedStatement = connection.prepareStatement(admin);
                preparedStatement.setLong(1,7777);
                preparedStatement.setString(2,"admin");
                preparedStatement.setString(3,"admin");
                preparedStatement.setString(4,"admin");
                preparedStatement.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
    }
}
