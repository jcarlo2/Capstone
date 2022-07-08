package retail.model.repository.implementer;

import retail.model.repository.implementation.Delivery;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.DeliveryItemDetail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static retail.shared.constant.Constant.*;

public class DeliveryRepository implements Delivery {

    @Override
    public void addReport(DeliveryDetail report) {
        try {
            String query = "INSERT INTO product_report(id,user,total) VALUES(?,?,?)";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, report.getId());
            preparedStatement.setString(2, report.getUser());
            preparedStatement.setString(3, report.getTotal());
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
                                                    "total_price," +
                                                    "discount_percent," +
                                                    "discount_total," +
                                                    "total_amount," +
                                                    "unique_id)" +
                                                    " VALUES(?,?,?,?,?,?,?,?,?)";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(DeliveryItemDetail item : itemList) {
                preparedStatement.setString(1,item.getProductId());
                preparedStatement.setString(2,item.getQuantityPerPieces());
                preparedStatement.setString(3,item.getQuantityPerBox());
                preparedStatement.setString(4,item.getPiecesPerBox());
                preparedStatement.setString(5,item.getTotalPrice());
                preparedStatement.setString(6,item.getDiscountPercentage());
                preparedStatement.setString(7, item.getDiscountTotal());
                preparedStatement.setString(8, item.getTotalAmount());
                preparedStatement.setString(9,id);
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
    public ArrayList<DeliveryDetail> findAllReport() {
        ArrayList<DeliveryDetail> reportList = new ArrayList<>();
        try {
            String query = "SELECT * FROM product_report ORDER BY date_time DESC";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                reportList.add(new DeliveryDetail(resultSet.getString("id"),
                                                  resultSet.getString("user"),
                                                  resultSet.getString("total"),
                                                  resultSet.getString("date_time")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }

    @Override
    public ArrayList<DeliveryDetail> findAllReportByDate(String start, String end) {
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
                                                    resultSet.getString("total"),
                                                    resultSet.getString("date_time")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }

    @Override
    public ArrayList<DeliveryItemDetail> findAllDeliveryItemById(String id) {
        ArrayList<DeliveryItemDetail> reportList = new ArrayList<>();
        try {
            String query = "SELECT * FROM product_item WHERE unique_id = ?";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                reportList.add(new DeliveryItemDetail(
                        resultSet.getString("prod_id"),
                        resultSet.getString("quantity_pieces"),
                        resultSet.getString("quantity_box"),
                        resultSet.getString("pieces_per_box"),
                        resultSet.getString("total_price"),
                        resultSet.getString("discount_percent"),
                        resultSet.getString("discount_total"),
                        resultSet.getString("total_amount")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }

    @Override
    public DeliveryDetail findDeliveryReportById(String id) {
        DeliveryDetail report = null;
        try {
            String query = "SELECT * FROM product_report WHERE id = ? ";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                report  = new DeliveryDetail(
                        resultSet.getString("id"),
                        resultSet.getString("user"),
                        resultSet.getString("total"),
                        resultSet.getString("date_time"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }
}
