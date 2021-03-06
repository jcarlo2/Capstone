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
            String query = "INSERT INTO null_report(id,user,total_amount,link) VALUES(?,?,?,?)";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,report.getId());
            preparedStatement.setString(2,report.getUser());
            preparedStatement.setString(3,report.getTotal());
            preparedStatement.setString(4,report.getLink());
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNullReportItem(ArrayList<NullReportItem> itemList) {
        try {
            String query = "INSERT INTO null_item(id,price,quantity_per_pieces,quantity_per_box,pieces_per_box,total_amount,report_id) VALUES(?,?,?,?,?,?,?)";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(NullReportItem item : itemList) {
                preparedStatement.setString(1,item.getId());
                preparedStatement.setString(2,item.getPrice());
                preparedStatement.setString(3,item.getQuantity());
                preparedStatement.setString(4,item.getBox());
                preparedStatement.setString(5,item.getPieces());
                preparedStatement.setString(6,item.getTotal());
                preparedStatement.setString(7,item.getReportId());
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

    @Override
    public ArrayList<NullProductReport> findAllReport() {
        ArrayList<NullProductReport> reportList = new ArrayList<>();
        try {
            String query = "SELECT * FROM null_report";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                reportList.add(new NullProductReport(resultSet.getString("id"),
                                                     resultSet.getString("user"),
                                                     resultSet.getString("total_amount"),
                                                     resultSet.getString("link"),
                                                     resultSet.getString("date_time")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }

    @Override
    public ArrayList<NullProductReport> findAllReportByDate(String start, String end) {
        ArrayList<NullProductReport> itemList = new ArrayList<>();
        try {
            String query = "SELECT * FROM null_report WHERE date_time >= ? AND date_time <= ? ORDER BY date_time DESC";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,start);
            preparedStatement.setString(2,end);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                itemList.add(new NullProductReport(resultSet.getString("id"),
                                                     resultSet.getString("user"),
                                                     resultSet.getString("total_amount"),
                                                     resultSet.getString("link"),
                                                     resultSet.getString("date_time")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public ArrayList<NullReportItem> findAllNullReportItemById(String id) {
        ArrayList<NullReportItem> itemList = new ArrayList<>();
        try {
            String query = "SELECT * FROM null_item WHERE report_id = ?";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                itemList.add(new NullReportItem(resultSet.getString("id"),
                                                 resultSet.getString("price"),
                                                 resultSet.getString("quantity_per_pieces"),
                                                 resultSet.getString("quantity_per_box"),
                                                 resultSet.getString("pieces_per_box"),
                                                 resultSet.getString("total_amount"),
                                                 resultSet.getString("report_id")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public ArrayList<NullProductReport> findNullReportByLink(String search) {
        ArrayList<NullProductReport> reportList = new ArrayList<>();
        try {
            String query = "SELECT * FROM null_report WHERE link LIKE '%" + search + "%'";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                reportList.add(new NullProductReport(resultSet.getString("id"),
                        resultSet.getString("user"),
                        resultSet.getString("total_amount"),
                        resultSet.getString("link"),
                        resultSet.getString("date_time")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }

    @Override
    public NullProductReport findNullReportById(String id) {
        NullProductReport report = null;
        try {
            String query = "SELECT * FROM null_report WHERE id = ? ";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                report  = new NullProductReport(
                        resultSet.getString("id"),
                        resultSet.getString("user"),
                        resultSet.getString("total_amount"),
                        resultSet.getString("link"),
                        resultSet.getString("date_time"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return report;
    }
}
