package retail.controller.tab.inventory.product;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductDatabase;
import retail.shared.customcomponent.jtable.JTableProduct;
import retail.shared.constant.ConstantDialog;
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.bot.inventory.manipulator.panel.Product;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProductController {
    private final ProductDatabase productDatabase = new ProductDatabase();
    private final Product productPanel;
    private final JTableProduct table;

    public ProductController(@NotNull BottomBorderPanel bottomBorderPanel) {
        productPanel = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getProduct();
        table = bottomBorderPanel.getBottomMainCard().getInventoryCard().getProduct().getTable();

        getDataFromTable();
        addInventoryProduct();
        removeInventoryProduct();
        update();
    }

    private void update() {
        Runnable runnable = () -> {
            if(!isSameData(table)) {
                SwingUtilities.invokeLater(() -> table.populateProductTable(productDatabase.showProduct()));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,0,1, TimeUnit.SECONDS);
    }

    private boolean isSameData(@NotNull JTableProduct table) {
        int count = table.getRowCount();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<retail.shared.pojo.Product> prodList = productDatabase.showProduct();
        for(int i=0;i<count;i++) {
            list.add(table.getValueAt(i,1).toString());
        }
        for(retail.shared.pojo.Product product : prodList) {
            if(!list.contains(product.getId())) return false;
        }
        return list.size() == prodList.size();
    }

    private void getDataFromTable() {
        table.getSelectionModel().addListSelectionListener(e -> {
                if(table.getRowCount() > 0) {
                    setStringInventoryDetail();
                }
        });
    }

    private void removeInventoryProduct() {
        productPanel.getDelete().addActionListener(e -> {
                String id = productPanel.getId().getText();
                if(!productDatabase.ifProductExist(id)) {
                    ConstantDialog.ID_DOES_NOT_EXIST();
                    return;
                }
                productDatabase.remove(id);
                table.populateProductTable(productDatabase.showProduct());
        });
    }

    private void setStringInventoryDetail() {
        try {
            String[] data = new String[7];
            int row = table.getSelectedRow();
            for(int i = 0; i< table.getColumnCount(); i++) {
                data[i] = (String) table.getValueAt(row,i);
            }
            // ID
            productPanel.getId().setText(data[1]);
            // DESCRIPTION
            productPanel.getDescription().setText(data[2]);
            // PRICE
            productPanel.getPrice().setText(data[3]);
            // PIECES PER BOX
            productPanel.getPiecesPerBox().setText(data[5]);
            // FULL DESCRIPTION
            productPanel.getFullDescription().setText(data[2]);
        }catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private String @NotNull [] getStringInventoryAdd() {
        String[] data = new String[4];
        data[0] = productPanel.getId().getText();
        data[1] = productPanel.getDescription().getText();
        data[2] = productPanel.getPrice().getText();
        data[3] = productPanel.getPiecesPerBox().getText();
        return  data;
    }

    private void addProduct(String @NotNull [] data) {
        productDatabase.save(new retail.shared.pojo.Product
                (data[0],data[1],new BigDecimal(data[2]),Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]),Double.parseDouble(data[5])));
        table.populateProductTable(productDatabase.showProduct());
    }

//    private void updateProduct(String @NotNull [] data) {
//        controller.update(new retail.shared.pojo.Product(data[0],data[1],new BigDecimal(data[2]),new BigDecimal(data[3]),
//                new BigDecimal(data[3]),new BigDecimal(data[5])));
//        table.populateProductTable(controller.showProduct());
//    }

    private void addInventoryProduct() {
        productPanel.getAdd().addActionListener(e -> {
            String[] data = getStringInventoryAdd();
            if(data[0].equals("")) {
                ConstantDialog.EMPTY_FIELD();
                return;
            }
            if(productDatabase.ifProductExist(data[0])) {
//                updateProduct(data);
            } else {
                addProduct(data);
            }
        });
    }
}