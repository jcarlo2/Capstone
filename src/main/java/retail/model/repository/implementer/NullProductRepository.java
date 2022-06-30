package retail.model.repository.implementer;

import retail.model.repository.implementation.NullProduct;
import retail.shared.entity.NullProductReport;
import retail.shared.entity.NullReportItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static retail.shared.constant.Constant.*;

public class NullProductRepository implements NullProduct {
    @Override
    public void addNullProductReport(NullProductReport report) {
        try {
            String query = "INSERT INTO null_report(id,user,total_amount,transaction_link) VALUES(?,?,?,?)";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,report.getId());
            preparedStatement.setString(2,report.getUser());
            preparedStatement.setString(3,report.getTotalAmount());
            preparedStatement.setString(4,report.getTransactionLink());
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNullReportItem(ArrayList<NullReportItem> itemList) {
        try {
            String query = "INSERT INTO null_item(id,price,quantity_per_pieces,total_amount,report_id) VALUES(?,?,?,?,?)";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(NullReportItem item : itemList) {
                preparedStatement.setString(1,item.getId());
                preparedStatement.setString(2,item.getPrice());
                preparedStatement.setString(3,item.getQuantity());
                preparedStatement.setString(4,item.getTotal());
                preparedStatement.setString(5,item.getReportId());
                preparedStatement.executeUpdate();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isReportExist(String id) {
        String query = "SELECT EXISTS (SELECT id FROM null_report WHERE id = ?)";
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
}
