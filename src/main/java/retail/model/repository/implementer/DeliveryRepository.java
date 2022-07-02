package retail.model.repository.implementer;

import retail.model.repository.implementation.Delivery;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.DeliveryItemDetail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static retail.shared.constant.Constant.PASS;
import static retail.shared.constant.Constant.URL;
import static retail.shared.constant.Constant.USER;

public class DeliveryRepository implements Delivery {

    @Override
    public void addReport(DeliveryDetail report) {
        try {
            String query = "INSERT INTO product_report(id,user) VALUES(?,?)";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, report.getId());
            preparedStatement.setString(2, report.getUser());
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addReportItem(ArrayList<DeliveryItemDetail> itemList, String id) {
        try {
            String query = "INSERT INTO product_item(prod_id," +
                                                    "quantity_pieces," +
                                                    "quantity_box," +
                                                    "pieces_per_box," +
                                                    "unique_id)" +
                                                    " VALUES(?,?,?,?,?)";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(DeliveryItemDetail item : itemList) {
                preparedStatement.setString(1,item.getProductId());
                preparedStatement.setString(2,item.getQuantityPerPieces());
                preparedStatement.setString(3,item.getQuantityPerBox());
                preparedStatement.setString(4,item.getPiecesPerBox());
                preparedStatement.setString(5,id);
                preparedStatement.executeUpdate();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isReportExist(String id) {
        String query = "SELECT EXISTS (SELECT id FROM product_report WHERE id = ?)";
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
