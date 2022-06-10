package retail.model.repository.implementer;

import retail.model.repository.implementation.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static retail.shared.constant.Constant.*;
import static retail.shared.constant.Constant.PASS;

public class UserRepository implements User {

    @Override
    public boolean checkIfExist(String id, String password) {
        String query = "SELECT EXISTS (SELECT last_name FROM employee WHERE id = ? and password = ?)";
        boolean flag = false;
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = resultSet.getInt(1) == 1;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public String getLastName(String id) {
        String query = "SELECT * FROM employee WHERE id = ?";
        String lastName = "";
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,Long.parseLong(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            lastName = resultSet.getString("last_name");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return lastName;
    }
}
