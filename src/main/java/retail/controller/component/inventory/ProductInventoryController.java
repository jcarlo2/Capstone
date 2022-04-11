package retail.controller.component.inventory;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductController;
import retail.customcomponent.jtable.CustomJTableProduct;
import retail.model.Product;
import retail.util.constant.ConstantDialog;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.manipulator.inventorymanipulator.panel.ProductList;

import java.math.BigDecimal;

public class ProductInventoryController {
    private final ProductController controller = new ProductController();
    private final ProductList productList;
    private final CustomJTableProduct table;

    public ProductInventoryController(@NotNull BottomBorderPanel bottomBorderPanel) {
        productList = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getProduct();
        table = bottomBorderPanel.getBottomMainCard().getInventory().getProduct().getTable();

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
        productList.getDelete().addActionListener(e -> {
                String id = productList.getId().getText();
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
            productList.getId().setText(data[1]);
            // DESCRIPTION
            productList.getDescription().setText(data[2]);
            // PRICE
            productList.getPrice().setText(data[3]);
            // QUANTITY BY PIECE
            productList.getQuantityByPiece().setText(data[4]);
            // PIECES PER BOX
            productList.getPiecesPerBox().setText(data[5]);
            // QUANTITY BY BOX
            productList.getQuantityByBox().setText(data[6]);
            // FULL DESCRIPTION
            productList.getFullDescription().setText(data[2]);
        }catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private String @NotNull [] getStringInventoryAdd() {
        String[] data = new String[6];
        data[0] = productList.getId().getText();
        data[1] = productList.getDescription().getText();
        data[2] = productList.getPrice().getText();
        data[3] = productList.getQuantityByPiece().getText();
        data[4] = productList.getPiecesPerBox().getText();
        data[5] = productList.getQuantityByBox().getText();
        return  data;
    }

    private void addProduct(String @NotNull [] data) {
        controller.save(new Product
                (data[0],data[1],new BigDecimal(data[2]),new BigDecimal(data[3]),
                        new BigDecimal(data[4]),new BigDecimal(data[5])));
        table.populateProductTable(controller.showProduct());
    }

    private void updateProduct(String @NotNull [] data) {
        controller.update(new Product(data[0],data[1],new BigDecimal(data[2]),new BigDecimal(data[3]),
                new BigDecimal(data[3]),new BigDecimal(data[5])));
        table.populateProductTable(controller.showProduct());
        }

    private void addInventoryProduct() {
        productList.getAdd().addActionListener(e -> {
            String[] data = getStringInventoryAdd();
            if(data[0].equals("")) {
                ConstantDialog.EMPTY_FIELD();
                return;
            }
            if(controller.ifProductExist(data[0])) {
                updateProduct(data);
            } else {
                addProduct(data);
            }
        });
    }
}
