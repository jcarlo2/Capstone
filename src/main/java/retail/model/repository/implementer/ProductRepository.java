package retail.model.repository.implementer;

import retail.model.repository.implementation.Product;
import retail.shared.entity.Merchandise;
import retail.shared.pojo.ProductDisplay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static retail.shared.constant.Constant.*;


public class ProductRepository implements Product {

    @Override
    public String findPriceById(String id) {
        return findProductById(id).getPrice();
    }

    @Override
    public boolean isProductExist(String id) {
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

    @Override
    public ArrayList<Merchandise> getAllProduct() {
        String query = "SELECT * FROM product";
        ArrayList<Merchandise> merchandiseList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String id = resultSet.getString("id");
                String description = resultSet.getString("description");
                String price = resultSet.getString("price");
                String quantityPerPieces = resultSet.getString("quantity_per_pieces");
                String quantityPerBox = resultSet.getString("quantity_per_box");
                String piecesPerBox = resultSet.getString("pieces_per_box");
                merchandiseList.add(new Merchandise(id,description,price,quantityPerPieces,piecesPerBox,quantityPerBox));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return merchandiseList;
    }

    @Override
    public Merchandise findProductById(String id) {
        if(!isProductExist(id)) return null;
        String query = "SELECT * FROM product WHERE id = ?";
        String description = "";
        String price = "";
        String quantityPerPieces = "";
        String quantityPerBox = "";
        String piecesPerBox = "";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getString("id");
            description = resultSet.getString("description");
            price = resultSet.getString("price");
            quantityPerPieces = resultSet.getString("quantity_per_pieces");
            quantityPerBox = resultSet.getString("quantity_per_box");
            piecesPerBox = resultSet.getString("pieces_per_box");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return new Merchandise(id,description,price,quantityPerPieces,piecesPerBox,quantityPerBox);
    }

    @Override
    public void updateProductQuantity(String id, Double quantity) {
        String query = "UPDATE product SET quantity_per_pieces = quantity_per_pieces + ? WHERE id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1,quantity);
            preparedStatement.setString(2,id);
            preparedStatement.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(ProductDisplay display) {
        try {
            String query = "UPDATE product SET id = ?, description = ?, price = ?,pieces_per_box = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,display.getNewId());
            preparedStatement.setString(2,display.getDescription());
            preparedStatement.setString(3,display.getPrice());
            preparedStatement.setString(4,display.getPiecesPerBox());
            preparedStatement.setString(5,display.getId());
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProduct(Merchandise merchandise) {
        try {
            String query = "INSERT INTO product(id,description,price,pieces_per_box) VALUES(?,?,?,?)";
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,merchandise.getId());
            preparedStatement.setString(2,merchandise.getDescription());
            preparedStatement.setString(3,merchandise.getPrice());
            preparedStatement.setString(4, merchandise.getPiecesPerBox());
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM product WHERE id = ?";
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
    public String findBoxPiecesById(String id) {
        return findProductById(id).getPiecesPerBox();
    }

    @Override
    public String findQuantityById(String id) {
        return findProductById(id).getQuantityPerPieces();
    }

}
