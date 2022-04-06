package retail.controller.database;

import lombok.NonNull;
import retail.dto.ProductObject;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static retail.constant.ConstantString.*;

public class ProductController {

    public boolean ifProductExist(String id) {
        String query = "SELECT EXISTS (SELECT id FROM product WHERE id = ?)";
        int flag = -1;
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = resultSet.getInt(1);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return flag == 1;
    }

    public ArrayList<ProductObject> show() {
        String query = "SELECT * FROM product";
        ArrayList<ProductObject> listOfEntity = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String id = resultSet.getString("id");
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                BigDecimal quantityPerPieces = resultSet.getBigDecimal("quantity_per_pieces");
                BigDecimal quantityPerBox = resultSet.getBigDecimal("quantity_per_box");
                BigDecimal piecesPerBox = resultSet.getBigDecimal("pieces_per_box");
                listOfEntity.add(new ProductObject(id,description,price,quantityPerPieces,piecesPerBox,quantityPerBox));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return  listOfEntity;
    }

    public ProductObject get(String id) {
        if(!ifProductExist(id)) return null;
        String query = "SELECT * FROM product WHERE id = ?";
        String description = "";
        BigDecimal price = new BigDecimal("0");
        BigDecimal quantityPerPieces = new BigDecimal("0");
        BigDecimal quantityPerBox = new BigDecimal("0");
        BigDecimal piecesPerBox = new BigDecimal("0");
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getString("id");
            description = resultSet.getString("description");
            price = resultSet.getBigDecimal("price");
            quantityPerPieces = resultSet.getBigDecimal("quantity_per_pieces");
            quantityPerBox = resultSet.getBigDecimal("quantity_per_box");
            piecesPerBox = resultSet.getBigDecimal("pieces_per_box");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ProductObject(id,description,price,quantityPerPieces,piecesPerBox,quantityPerBox);
    }

    public void save(@NonNull ProductObject product) {
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
            preparedStatement.setBigDecimal(4,product.getQuantityPerPieces());
            preparedStatement.setBigDecimal(5,product.getQuantityPerBox());
            preparedStatement.setBigDecimal(6,product.getPiecesPerBox());
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

    public void update(String id, ProductObject product) {
        String query = "UPDATE product SET id = ?, " +
                "description = ?, " +
                "price = ?, " +
                "quantity_per_pieces = ?, " +
                "quantity_per_box = ?," +
                "pieces_per_box = ? WHERE id = ?;";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getId());
            preparedStatement.setString(2,product.getDescription());
            preparedStatement.setBigDecimal(3,product.getPrice());
            preparedStatement.setBigDecimal(4,product.getQuantityPerPieces());
            preparedStatement.setBigDecimal(5,product.getQuantityPerBox());
            preparedStatement.setBigDecimal(6,product.getPiecesPerBox());
            preparedStatement.setString(7,id);
            preparedStatement.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
