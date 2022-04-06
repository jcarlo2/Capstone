package retail.controller.inventory;

import org.jetbrains.annotations.NotNull;
import retail.dto.ProductObject;
import retail.view.main.panel.leftpanel.LeftCenterPanel;
import retail.view.main.panel.leftpanel.inventorymanipulator.panel.DetailPanel;
import retail.view.main.panel.leftpanel.inventorymanipulator.panel.UpdatePanel;
import retail.view.main.panel.rightpanel.RightCenterPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static retail.constant.ConstantString.*;

public class TableController {
    private final RightCenterPanel rightCenterPanel;
    private final LeftCenterPanel leftCenterPanel;
    private final UpdatePanel updatePanel;
    private final DetailPanel detailPanel;

    public TableController(RightCenterPanel rightCenterPanel, @NotNull LeftCenterPanel leftCenterPanel) {
        this.rightCenterPanel = rightCenterPanel;
        this.leftCenterPanel = leftCenterPanel;
        updatePanel = leftCenterPanel.getInventoryManipulator().getUpdatePanel();
        detailPanel = leftCenterPanel.getInventoryManipulator().getDetailPanel();

        insertDataInInventoryTable();
        inventoryTableEventListener();
    }

    public void inventoryTableEventListener() {
        rightCenterPanel.getInventoryPanel().getInventoryTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Point point = e.getPoint();
                int row = rightCenterPanel.getInventoryPanel().getInventoryTable().rowAtPoint(point);
                    if(e.getClickCount() == 2
                        && rightCenterPanel.getInventoryPanel().getInventoryTable().getSelectedRow() != -1) {
                            if(leftCenterPanel.getInventoryManipulator().getAddDeleteUpdateDetailPanel().getIsUpdate()) {
                                setStringInventoryUpdate(row);
                            }
                    }
            }
        });

        rightCenterPanel.getInventoryPanel().getInventoryTable().getSelectionModel().addListSelectionListener(e -> {
            int row = rightCenterPanel.getInventoryPanel().getInventoryTable().getSelectedRow();
                if(leftCenterPanel.getInventoryManipulator().getAddDeleteUpdateDetailPanel().getIsUpdate()) {
                    setStringInventoryDetail(row);
                }
        });
    }

    private void setStringInventoryDetail(int row) {
        String[] data = new String[7];
        int NUMBER_OF_COLUMN = 7;
            for(int i = 0; i< NUMBER_OF_COLUMN; i++) {
                data[i] = (String) rightCenterPanel.getInventoryPanel().getInventoryTable().getValueAt(row,i);
            }
        // ID
        detailPanel.getId().setText(data[1]);
        // DESCRIPTION
        detailPanel.getDescription().setText(data[2]);
        // PRICE
        detailPanel.getPrice().setText(data[3]);
        // QUANTITY BY PIECE
        detailPanel.getQuantityByPiece().setText(data[4]);
        // PIECES PER BOX
        detailPanel.getPiecesPerBox().setText(data[5]);
        // QUANTITY BY BOX
        detailPanel.getQuantityByBox().setText(data[6]);
        // FULL DESCRIPTION
        detailPanel.getFullDescription().setText(data[2]);
    }

    private void setStringInventoryUpdate(int row) {
    String[] data = new String[7];
    int NUMBER_OF_COLUMN = 7;
        for(int i = 0; i< NUMBER_OF_COLUMN; i++) {
            data[i] = (String) rightCenterPanel.getInventoryPanel().getInventoryTable().getValueAt(row,i);
        }
    // ID UP TO DATE
    updatePanel.getIdToUpdate().setText(data[1]);
    // ID
    updatePanel.getId().setText(data[1]);
    // DESCRIPTION
    updatePanel.getDescription().setText(data[2]);
    // PRICE
    updatePanel.getPrice().setText(data[3]);
    // QUANTITY BY PIECE
    updatePanel.getQuantityByPiece().setText(data[4]);
    // PIECES PER BOX
    updatePanel.getPiecesPerBox().setText(data[5]);
    // QUANTITY BY BOX
    updatePanel.getQuantityByBox().setText(data[6]);
    }

    public void insertDataInInventoryTable() {
        rightCenterPanel.getInventoryPanel().getInventoryTable().populateInventoryJTable(getListInDatabase());
    }

    private @NotNull ArrayList<ProductObject> getListInDatabase() {
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
}
