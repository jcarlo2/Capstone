package retail.model.repository.implementer;

import retail.model.repository.implementation.Delivery;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.DeliveryItemDetail;
import retail.shared.pojo.InventoryItem;

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

    @Override
    public ArrayList<DeliveryDetail> getAllReport() {
        ArrayList<DeliveryDetail> reportList = new ArrayList<>();
        try {
            String query = "SELECT * FROM product_report ORDER BY date_time DESC";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                reportList.add(new DeliveryDetail(resultSet.getString("id"),
                                                  resultSet.getString("user"),
                                                  resultSet.getString("date_time")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }

    @Override
    public ArrayList<DeliveryDetail> getAllReportByDate(String start, String end) {
        ArrayList<DeliveryDetail> reportList = new ArrayList<>();
        try {
            String query = "SELECT * FROM product_report WHERE date_time >= ? AND date_time <= ? ORDER BY date_time DESC";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,start);
            preparedStatement.setString(2,end);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                reportList.add(new DeliveryDetail(resultSet.getString("id"),
                        resultSet.getString("user"),
                        resultSet.getString("date_time")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }

    @Override
    public ArrayList<InventoryItem> findDeliveryReportById(String id) {
        ArrayList<InventoryItem> reportList = new ArrayList<>();
        try {
            String query = "SELECT * FROM product_item WHERE unique_id = ?";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                reportList.add(new InventoryItem(resultSet.getString("prod_id"),"",
                                                 resultSet.getString("quantity_pieces")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }
}
