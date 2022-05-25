package retail.controller.database;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import retail.model.Product;
import retail.model.ProductReport;
import retail.model.ProductReportItem;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import static retail.shared.constant.Constant.*;

public class ProductDatabase {
    // PRODUCT
    public boolean ifProductExist(String id) {
        String query = "SELECT EXISTS (SELECT id FROM product WHERE id = ?)";
        boolean flag = false;
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = resultSet.getInt(1) == 1;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public ArrayList<Product> showProduct() {
        String query = "SELECT * FROM product";
        ArrayList<Product> productList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String id = resultSet.getString("id");
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                Integer quantityPerPieces = resultSet.getInt("quantity_per_pieces");
                Double quantityPerBox = resultSet.getDouble("quantity_per_box");
                Integer piecesPerBox = resultSet.getInt("pieces_per_box");
                productList.add(new Product(id,description,price,quantityPerPieces,piecesPerBox,quantityPerBox));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return  productList;
    }

    public Product get(String id) {
        if(!ifProductExist(id)) return null;
        String query = "SELECT * FROM product WHERE id = ?";
        String description = "";
        BigDecimal price = new BigDecimal("0");
        int quantityPerPieces = 0;
        double quantityPerBox = 0.0;
        int piecesPerBox = 0;
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getString("id");
            description = resultSet.getString("description");
            price = resultSet.getBigDecimal("price");
            quantityPerPieces = resultSet.getInt("quantity_per_pieces");
            quantityPerBox = resultSet.getDouble("quantity_per_box");
            piecesPerBox = resultSet.getInt("pieces_per_box");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return new Product(id,description,price,quantityPerPieces,piecesPerBox,quantityPerBox);
    }

    public void save(@NonNull Product product) {
        if(ifProductExist(product.getId())) return;
        String query = "INSERT INTO product(id," +
                "description," +
                "price," +
                "quantity_per_pieces," +
                "quantity_per_box," +
                "pieces_per_box) VALUES(?,?,?,?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,product.getId());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3,product.getPrice());
            preparedStatement.setInt(4,product.getQuantityPerPieces());
            preparedStatement.setDouble(5,product.getQuantityPerBox());
            preparedStatement.setInt(6,product.getPiecesPerBox());
            preparedStatement.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(String id) {
        String query = "DELETE FROM product WHERE id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProductQuantity(String id, Integer quantity) {
        String query = "UPDATE product SET quantity_per_pieces = ? WHERE id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,quantity);
            preparedStatement.setString(2,id);
            preparedStatement.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    // PRODUCT REPORT
    public void addReport(ArrayList<ProductReportItem> list, ProductReport report, @NotNull ArrayList<Product> productList) {
        saveReport(report);
        saveReportItem(list,report);
        for(Product product : productList) {
            updateProductQuantity("product", 123);
        }
    }

    private void deleteReportItem(String id) {
        String query = "DELETE FROM product_report_item WHERE unique_id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteReport(String id) {
        String query = "DELETE FROM product_report WHERE id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        deleteReportItem(id);
    }

    public ArrayList<ProductReportItem> getAllProductReportItem(String id) {
        ArrayList<ProductReportItem> itemList = new ArrayList<>();
        String query = "SELECT * FROM product_report_item WHERE unique_id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                setItemList(itemList,resultSet);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    private void setItemList(ArrayList<ProductReportItem> itemList, ResultSet resultSet) {
        try {
            String productId = resultSet.getString("prod_id");
            BigDecimal price = resultSet.getBigDecimal("price");
            Integer quantityByPieces = resultSet.getInt("quantity_pieces");
            Double quantityByBox = resultSet.getDouble("quantity_box");
            Integer piecesPerBox = resultSet.getInt("pieces_per_box");

            itemList.add(new ProductReportItem(productId,price,quantityByPieces,quantityByBox,piecesPerBox));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeIsDeletableStatus(String id) {
        String query = "UPDATE product_report SET is_deletable = NOT is_deletable WHERE id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void saveReport(ProductReport report) {
        String query = "INSERT INTO product_report(id,user) VALUES(?,?)";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,report.getId());
                preparedStatement.setString(2,report.getUser());
                preparedStatement.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    public boolean isReportExist(String formatId) {
        String query = "SELECT EXISTS (SELECT id FROM product_report WHERE id = ?)";
        boolean flag = false;
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,formatId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = resultSet.getInt(1) == 1;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public ArrayList<ProductReport> getProductReportList() {
        ArrayList<ProductReport> reportList = new ArrayList<>();
        String query = "SELECT * FROM product_report";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String user = resultSet.getString("user");
                Date date = resultSet.getDate("date");
                reportList.add(new ProductReport(id,user,date));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }

    public ProductReport getProductReport(String id) {
        String query = "SELECT * FROM sales_report WHERE id = ?";
        String user = "";
        Date date = null;
            try {
                    Connection connection = DriverManager.getConnection(URL,USER,PASS);
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                        while(resultSet.next()) {
                            id = resultSet.getString("id");
                            user = resultSet.getString("user");
                            date = resultSet.getDate("date");
                        }
            }catch (Exception e) {
                e.printStackTrace();
            }
        return new ProductReport(id,user,date);
    }

    public boolean isDeletable(String id) {
        String query = "SELECT is_deletable FROM product_report WHERE id = ?";
        boolean flag = false;
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    flag = resultSet.getBoolean("is_deletable");
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        return flag;
    }

    // PRODUCT REPORT ITEM
    private void saveReportItem(@NotNull ArrayList<ProductReportItem> list, ProductReport report) {
        for(ProductReportItem item : list) {
            String query = "INSERT INTO product_report_item(prod_id,price,quantity_pieces," +
                    "quantity_box,pieces_per_box,unique_id) VALUES(?,?,?,?,?,?)";
            try {
                Connection connection = DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,item.getProductId());
                preparedStatement.setBigDecimal(2,item.getPrice());
                preparedStatement.setInt(3,item.getQuantityByPieces());
                preparedStatement.setDouble(4,item.getQuantityByBox());
                preparedStatement.setInt(5,item.getPiecesPerBox());
                preparedStatement.setString(6, report.getId());
                preparedStatement.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
