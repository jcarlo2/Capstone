package retail.model.repository.implementation;

import retail.shared.entity.Merchandise;
import retail.shared.pojo.ProductDisplay;

import java.util.ArrayList;

public interface Product {

    boolean isProductExist(String id);

    String findPriceById(String productId);

    Merchandise findProductById(String id);

    ArrayList<Merchandise> getAllProduct();

    void updateProductQuantity(String id, Double quantity);

    void updateProduct(ProductDisplay display);

    void addProduct(Merchandise merchandise);

    void delete(String id);

//    default ArrayList<Merchandise> getAllProduct() {
//        String query = "SELECT * FROM product";
//        ArrayList<Merchandise> merchandiseList = new ArrayList<>();
//        try {
//            Connection connection = DriverManager.getConnection(URL,USER,PASS);
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()) {
//                String id = resultSet.getString("id");
//                String description = resultSet.getString("description");
//                String price = resultSet.getString("price");
//                String quantityPerPieces = resultSet.getString("quantity_per_pieces");
//                String quantityPerBox = resultSet.getString("quantity_per_box");
//                String piecesPerBox = resultSet.getString("pieces_per_box");
//                merchandiseList.add(new Merchandise(id,description,price,quantityPerPieces,piecesPerBox,quantityPerBox));
//            }
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return merchandiseList;
//    }
//
//    default Merchandise findProductById(String id) {
//        if(!ifProductExist(id)) return null;
//        String query = "SELECT * FROM product WHERE id = ?";
//        String description = "";
//        String price = "";
//        String quantityPerPieces = "";
//        String quantityPerBox = "";
//        String piecesPerBox = "";
//        try {
//            Connection connection = DriverManager.getConnection(URL,USER,PASS);
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            id = resultSet.getString("id");
//            description = resultSet.getString("description");
//            price = resultSet.getString("price");
//            quantityPerPieces = resultSet.getString("quantity_per_pieces");
//            quantityPerBox = resultSet.getString("quantity_per_box");
//            piecesPerBox = resultSet.getString("pieces_per_box");
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return new Merchandise(id,description,price,quantityPerPieces,piecesPerBox,quantityPerBox);
//    }
//
//    default void saveProduct(@NonNull Merchandise merchandise) {
//        if(ifProductExist(merchandise.getId())) return;
//        String query = "INSERT INTO product(id," +
//                "description," +
//                "price," +
//                "quantity_per_pieces," +
//                "pieces_per_box) VALUES(?,?,?,?,?)";
//        try {
//            Connection connection = DriverManager.getConnection(URL,USER,PASS);
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, merchandise.getId());
//            preparedStatement.setString(2, merchandise.getDescription());
//            preparedStatement.setString(3, merchandise.getPrice());
//            preparedStatement.setString(4, merchandise.getQuantityPerPieces());
//            preparedStatement.setString(5, merchandise.getPiecesPerBox());
//            preparedStatement.executeUpdate();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    default void removeProduct(String id) {
//        String query = "DELETE FROM product WHERE id = ?";
//        try {
//            Connection connection = DriverManager.getConnection(URL,USER,PASS);
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,id);
//            preparedStatement.executeUpdate();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
//

//
//    // PRODUCT REPORT
//
//    private void deleteReportItem(String id) {
//        String query = "DELETE FROM product_item WHERE unique_id = ?";
//        try {
//            Connection connection = DriverManager.getConnection(URL,USER,PASS);
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,id);
//            preparedStatement.executeUpdate();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    default void deleteReport(String id) {
//        String query = "DELETE FROM product_report WHERE id = ?";
//        try {
//            Connection connection = DriverManager.getConnection(URL,USER,PASS);
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,id);
//            preparedStatement.executeUpdate();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        deleteReportItem(id);
//    }
//
//    default ArrayList<DeliveryItemDetail> getAllProductReportItem(String id) {
//        ArrayList<DeliveryItemDetail> itemList = new ArrayList<>();
//        String query = "SELECT * FROM product_item WHERE unique_id = ?";
//        try {
//            Connection connection = DriverManager.getConnection(URL,USER,PASS);
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()) {
//                setItemList(itemList,resultSet);
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return itemList;
//    }
//
//    private void setItemList(ArrayList<DeliveryItemDetail> itemList, ResultSet resultSet) {
//        try {
//            String productId = resultSet.getString("prod_id");
//            String price = resultSet.getString("price");
//            String quantityByPieces = resultSet.getString("quantity_pieces");
//            String quantityByBox = resultSet.getString("quantity_box");
//            String piecesPerBox = resultSet.getString("pieces_per_box");
//
//            itemList.add(new DeliveryItemDetail(productId,price,quantityByPieces,quantityByBox,piecesPerBox));
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    default void saveReport(DeliveryDetail report) {
//        String query = "INSERT INTO product_report(id,user) VALUES(?,?)";
//            try {
//                Connection connection = DriverManager.getConnection(URL,USER,PASS);
//                PreparedStatement preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setString(1,report.getId());
//                preparedStatement.setString(2,report.getUser());
//                preparedStatement.executeUpdate();
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//    }
//
//    default boolean isReportExist(String formatId) {
//        String query = "SELECT EXISTS (SELECT id FROM product_report WHERE id = ?)";
//        boolean flag = false;
//        try {
//            Connection connection = DriverManager.getConnection(URL,USER,PASS);
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,formatId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                flag = resultSet.getInt(1) == 1;
//            }
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }
//
//    default ArrayList<DeliveryDetail> getProductReportList() {
//        ArrayList<DeliveryDetail> reportList = new ArrayList<>();
//        String query = "SELECT * FROM product_report";
//        try {
//            Connection connection = DriverManager.getConnection(URL,USER,PASS);
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                String id = resultSet.getString("id");
//                String user = resultSet.getString("user");
//                String date = resultSet.getString("date");
//                reportList.add(new DeliveryDetail(id,user,date));
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return reportList;
//    }
//
//    default DeliveryDetail getProductReport(String id) {
//        String query = "SELECT * FROM product_report WHERE id = ?";
//        String user = "";
//        String date = null;
//            try {
//                    Connection connection = DriverManager.getConnection(URL,USER,PASS);
//                    PreparedStatement preparedStatement = connection.prepareStatement(query);
//                    preparedStatement.setString(1,id);
//                    ResultSet resultSet = preparedStatement.executeQuery();
//                        while(resultSet.next()) {
//                            id = resultSet.getString("id");
//                            user = resultSet.getString("user");
//                            date = resultSet.getString("date");
//                        }
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        return new DeliveryDetail(id,user,date);
//    }
//
//    // PRODUCT REPORT ITEM
//    default void saveReportItem(@NotNull ArrayList<DeliveryItemDetail> list, DeliveryDetail report) {
//        for(DeliveryItemDetail item : list) {
//            String query = "INSERT INTO product_item(prod_id,quantity_pieces," +
//                    "quantity_box,pieces_per_box,unique_id) VALUES(?,?,?,?,?)";
//            try {
//                Connection connection = DriverManager.getConnection(URL,USER,PASS);
//                PreparedStatement preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setString(1,item.getProductId());
//                preparedStatement.setString(2,item.getQuantityByPieces());
//                preparedStatement.setString(3,item.getQuantityByBox());
//                preparedStatement.setString(4,item.getPiecesPerBox());
//                preparedStatement.setString(5, report.getId());
//                preparedStatement.executeUpdate();
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
