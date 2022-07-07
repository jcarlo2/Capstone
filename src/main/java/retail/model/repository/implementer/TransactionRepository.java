package retail.model.repository.implementer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retail.model.repository.implementation.TransactionReport;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static retail.shared.constant.Constant.PASS;
import static retail.shared.constant.Constant.URL;
import static retail.shared.constant.Constant.USER;

public class TransactionRepository implements TransactionReport {

    @Override
    public void addReport(@NotNull TransactionDetail report, ArrayList<TransactionItemDetail> itemList) {
        TransactionReport.super.addReport(report, itemList);
    }

    @Override
    public boolean isReportExist(String id) {
        String query = "SELECT EXISTS (SELECT id FROM transaction_report WHERE id = ?)";
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
    public ArrayList<TransactionItemDetail> getAllTransactionReportItem(String id) {
        ArrayList<TransactionItemDetail> itemList = new ArrayList<>();
        String query = "SELECT * FROM transaction_report_item WHERE unique_id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String productId = resultSet.getString("prod_id");
                String price = resultSet.getString("price");
                String sold = resultSet.getString("sold");
                String soldTotal = resultSet.getString("sold_total");
                String discountPercentage = resultSet.getString("discount_percentage");
                String discountAmount = resultSet.getString("discount_amount");
                String totalAmount = resultSet.getString("total_amount");
                itemList.add(new TransactionItemDetail(productId,price,sold,soldTotal,
                        discountPercentage,discountAmount,totalAmount));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }


    @Override
    public void deleteReportItem(String id){
        String query = "DELETE FROM transaction_report_item WHERE unique_id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransactionReport(String id) {
        String query = "DELETE FROM transaction_report WHERE id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveTransactionReport(TransactionDetail transactionDetail) {
        String query = "INSERT INTO transaction_report(id,user,total_amount,old_id) VALUES(?,?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, transactionDetail.getId());
            preparedStatement.setString(2, transactionDetail.getUser());
            preparedStatement.setString(3, transactionDetail.getTotalAmount());
            preparedStatement.setString(4, transactionDetail.getOldId());
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveTransactionReportItem(@NotNull TransactionDetail transactionDetail, ArrayList<TransactionItemDetail> transactionItemDetail) {
        String query = "INSERT INTO transaction_report_item(prod_id,price,sold,sold_total,discount_percentage" +
                ",discount_amount,total_amount,unique_id) VALUES(?,?,?,?,?,?,?,?)";
        try {
            for(TransactionItemDetail item : transactionItemDetail) {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, item.getProductId());
                preparedStatement.setString(2, item.getPrice());
                preparedStatement.setString(3, item.getSold());
                preparedStatement.setString(4, item.getSoldTotal());
                preparedStatement.setString(5, item.getDiscountPercentage());
                preparedStatement.setString(6, item.getDiscountAmount());
                preparedStatement.setString(7, item.getTotalAmount());
                preparedStatement.setString(8, transactionDetail.getId());
                preparedStatement.executeUpdate();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<TransactionDetail> getAllValidTransactionReport(){
        ArrayList<TransactionDetail> reportList = new ArrayList<>();
        String query = "SELECT * FROM transaction_report WHERE is_valid = TRUE ORDER BY date_time DESC";
        return getTransactionDetails(reportList, query);
    }

    @Nullable
    private ArrayList<TransactionDetail> getTransactionDetails(ArrayList<TransactionDetail> reportList, String query) {
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transactionReportCreation(reportList, resultSet);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }

    @Override
    public ArrayList<TransactionDetail> getAllTransactionReportList(){
        ArrayList<TransactionDetail> reportList = new ArrayList<>();
        String query = "SELECT * FROM transaction_report ORDER BY date_time DESC";
        return getTransactionDetails(reportList, query);
    }

    @Override
    public TransactionDetail getTransactionReportById(String id) {
        String query = "SELECT * FROM transaction_report WHERE id = ?";
        TransactionDetail report = null;
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                id = resultSet.getString("id");
                String user = resultSet.getString("user");
                String date = resultSet.getString("date");
                String timestamp = resultSet.getString("date_time");
                String totalAmount = resultSet.getString("total_amount");
                String oldId = resultSet.getString("old_id");
                String credit = resultSet.getString("credit");
                String isValid = resultSet.getString("is_valid");
                report = new TransactionDetail(id,date,timestamp,user,totalAmount,oldId,credit,isValid);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }

    @Override
    public void invalidateId(String id) {
        try {
            String query = "UPDATE transaction_report SET is_valid = false WHERE id = ?";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<TransactionDetail> getAllTransactionReportByDate(String start, String end) {
        String query = "SELECT * FROM transaction_report WHERE date_time >= ? AND date_time <= ? ORDER BY date_time DESC";
        return getReportByDateAndValid(start, end, query);
    }

    @Override
    public ArrayList<TransactionDetail> getAllReportByDateAndValidity(String start, String end) {
        String query = "SELECT * FROM transaction_report WHERE date_time >= ? AND date_time <= ? AND is_valid = TRUE ORDER BY date_time DESC";
        return getReportByDateAndValid(start, end, query);
    }

    @Override
    public boolean isValid(String id) {
        String query = "SELECT * FROM transaction_report WHERE id = ? AND is_valid = TRUE";
        boolean flag = false;
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) flag = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public void revalidate(String id) {
        try {
            String query = "UPDATE transaction_report SET is_valid = TRUE WHERE id = ?";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private ArrayList<TransactionDetail> getReportByDateAndValid(String start, String end, String query) {
        ArrayList<TransactionDetail> report = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,start);
            preparedStatement.setString(2,end);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                transactionReportCreation(report, resultSet);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }

    private void transactionReportCreation(@NotNull ArrayList<TransactionDetail> report, @NotNull ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String user = resultSet.getString("user");
        String date = resultSet.getString("date");
        String timestamp = resultSet.getString("date_time");
        String totalAmount = resultSet.getString("total_amount");
        String oldId = resultSet.getString("old_id");
        String credit = resultSet.getString("credit");
        String isValid = resultSet.getString("is_valid");
        report.add(new TransactionDetail(id,date,timestamp,user,totalAmount,oldId,credit,isValid));
    }
}
