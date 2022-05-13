package retail.controller.tab.inventory;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductDatabase;
import retail.customcomponent.jtable.inventory.CustomJTableProduct;
import retail.util.constant.ConstantDialog;
import retail.view.main.tab.bot.BottomBorderPanel;
import retail.view.main.tab.bot.manipulator.inventory.panel.Product;

import java.math.BigDecimal;

public class ProductController {
    private final ProductDatabase controller = new ProductDatabase();
    private final Product product;
    private final CustomJTableProduct table;

    public ProductController(@NotNull BottomBorderPanel bottomBorderPanel) {
        product = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getProduct();
        table = bottomBorderPanel.getBottomMainCard().getInventoryCard().getProduct().getTable();

        getDataFromTable();
        addInventoryProduct();
        removeInventoryProduct();

        table.populateProductTable(controller.showProduct());
    }

    private void getDataFromTable() {
        table.getSelectionModel().addListSelectionListener(e -> {
                if(table.getRowCount() > 0) {
                    setStringInventoryDetail();
                }
        });
    }

    private void removeInventoryProduct() {
        product.getDelete().addActionListener(e -> {
                String id = product.getId().getText();
                if(!controller.ifProductExist(id)) {
                    ConstantDialog.ID_DOES_NOT_EXIST();
                    return;
                }
                controller.remove(id);
                table.populateProductTable(controller.showProduct());
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
            product.getId().setText(data[1]);
            // DESCRIPTION
            product.getDescription().setText(data[2]);
            // PRICE
            product.getPrice().setText(data[3]);
            // QUANTITY BY PIECE
            product.getQuantityByPiece().setText(data[4]);
            // PIECES PER BOX
            product.getPiecesPerBox().setText(data[5]);
            // QUANTITY BY BOX
            product.getQuantityByBox().setText(data[6]);
            // FULL DESCRIPTION
            product.getFullDescription().setText(data[2]);
        }catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private String @NotNull [] getStringInventoryAdd() {
        String[] data = new String[6];
        data[0] = product.getId().getText();
        data[1] = product.getDescription().getText();
        data[2] = product.getPrice().getText();
        data[3] = product.getQuantityByPiece().getText();
        data[4] = product.getPiecesPerBox().getText();
        data[5] = product.getQuantityByBox().getText();
        return  data;
    }

    private void addProduct(String @NotNull [] data) {
        controller.save(new retail.model.Product
                (data[0],data[1],new BigDecimal(data[2]),new BigDecimal(data[3]),
                        new BigDecimal(data[4]),new BigDecimal(data[5])));
        table.populateProductTable(controller.showProduct());
    }

//    private void updateProduct(String @NotNull [] data) {
//        controller.update(new retail.model.Product(data[0],data[1],new BigDecimal(data[2]),new BigDecimal(data[3]),
//                new BigDecimal(data[3]),new BigDecimal(data[5])));
//        table.populateProductTable(controller.showProduct());
//    }

    private void addInventoryProduct() {
        product.getAdd().addActionListener(e -> {
            String[] data = getStringInventoryAdd();
            if(data[0].equals("")) {
                ConstantDialog.EMPTY_FIELD();
                return;
            }
            if(controller.ifProductExist(data[0])) {
//                updateProduct(data);
            } else {
                addProduct(data);
            }
        });
    }
}
