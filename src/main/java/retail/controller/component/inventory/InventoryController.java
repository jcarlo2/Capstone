package retail.controller.component.inventory;

import org.jetbrains.annotations.NotNull;
import retail.customcomponent.jcombobox.CustomJComboBoxReport;
import retail.customcomponent.jtable.CustomJTableInventory;
import retail.constant.ConstantDialog;
import retail.controller.database.ProductController;
import retail.model.ProductObject;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.bot.manipulator.inventorymanipulator.panel.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

public class InventoryController {
    private final ProductController controller = new ProductController();
    private final AddDeleteUpdateDetailPanel addDeleteUpdateDetailPanel;
    private final InventoryCardPanel inventoryCardPanel;
    private final UpdatePanel updatePanel;
    private final DetailPanel detailPanel;
    private final DeletePanel deletePanel;
    private final AddPanel addPanel;
    private final CustomJComboBoxReport salesId;
    private final CustomJTableInventory tableInventory;
    private final Font sansSerif = new Font("SansSerif",Font.BOLD,12);
    private final Font segoeUI = new Font("Segoe UI",Font.PLAIN,12);

    public InventoryController(@NotNull BottomBorderPanel bottomBorderPanel) {
        addDeleteUpdateDetailPanel = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getAddDeleteUpdateDetailPanel();
        inventoryCardPanel = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getInventoryCardPanel();
        deletePanel = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getDeletePanel();
        updatePanel = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getUpdatePanel();
        detailPanel = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getDetailPanel();
        addPanel = bottomBorderPanel.getManipulatorCard().getInventoryManipulator().getAddPanel();
        tableInventory = bottomBorderPanel.getBottomMainCard().getInventory().getTableInventory();
        salesId =  bottomBorderPanel.getManipulatorCard().getSalesReportManipulator().getReportCardPanel().getAddPanel().getId();

        insertDataInInventoryTable();
        inventoryTableEventListener();
        leftInventoryActionListener();
        setDeleteActionListener();
        allClearButtonActionListener();
        updateInventoryProduct();
        addInventoryProduct();
    }

    public ProductObject createNewProductObject(String @NotNull [] data) {
        return new ProductObject(data[0],data[1],new BigDecimal(data[2]),
        new BigDecimal(data[3]),new BigDecimal(data[4]),new BigDecimal(data[5]));
    }

    private String @NotNull [] getStringInventoryAdd() {
        String[] data = new String[6];
        data[0] = addPanel.getId().getText();
        data[1] = addPanel.getDescription().getText();
        data[2] = addPanel.getPrice().getText();
        data[3] = addPanel.getQuantityByPiece().getText();
        data[4] = addPanel.getPiecesPerBox().getText();
        data[5] = addPanel.getQuantityByBox().getText();
        return  data;
    }

    public void addInventoryProduct() {
        addPanel.getAdd().addActionListener(e -> {
            if(e.getSource() == addPanel.getAdd()) {
                try{
                    String[] data = getStringInventoryAdd();
                    ProductObject product = createNewProductObject(data);
                        if(controller.ifProductExist(product.getId())) {
                            ConstantDialog.DUPLICATE_ID();
                            return;
                        }
                    controller.save(product);
                    insertDataInInventoryTable();
                    salesId.setProductIdList();

                }catch (NumberFormatException a) {
                    ConstantDialog.INVALID_INPUT_DIALOG();
                }
            }
        });
    }

    private void updateInventoryProduct() {
        updatePanel.getUpdate().addActionListener(e -> {
            if(e.getSource() == updatePanel.getUpdate()) {
                try {
                    String[] data = getStringInventoryUpdate();
                    if(!checkIfValidUpdateId(data)) return;
                    String toUpdate = data[0];
                    ProductObject newProduct = new ProductObject(data[1],data[2],new BigDecimal(data[3]),
                            new BigDecimal(data[4]),new BigDecimal(data[5]),new BigDecimal(data[6]));
                    if(!toUpdate.equals(newProduct.getId())) {
                        if(controller.ifProductExist(newProduct.getId())) return;
                    }
                    ProductObject oldProduct = controller.get(toUpdate);
                    setProductObject(oldProduct,newProduct);
                    controller.update(toUpdate,oldProduct);
                    insertDataInInventoryTable();
                }catch (NumberFormatException a) {
                    ConstantDialog.INVALID_INPUT_DIALOG();
                }catch (NullPointerException | ArrayIndexOutOfBoundsException c) {
                    ConstantDialog.EMPTY_FIELD_DIALOG();
                }
            }
        });
    }

    private void setProductObject(@NotNull ProductObject product, @NotNull ProductObject productAlternative) {
        product.setId(productAlternative.getId());
        product.setDescription(productAlternative.getDescription());
        product.setPrice(productAlternative.getPrice());
        product.setPiecesPerBox(productAlternative.getPiecesPerBox());
        product.setQuantityPerBox(productAlternative.getQuantityPerBox());
        product.setQuantityPerPieces(productAlternative.getQuantityPerPieces());
    }

    private boolean checkIfValidUpdateId(String @NotNull [] data) {
        if(data[1].equals("")) {
            ConstantDialog.EMPTY_FIELD_DIALOG();
            return false;
        }
        if (!controller.ifProductExist(data[0])) {
            ConstantDialog.ID_DOES_NOT_EXIST_DIALOG();
            return false;
        }

        return true;
    }

    private void allClearButtonActionListener() {
        addPanel.getClear().addActionListener(e -> {
            if(e.getSource() == addPanel.getClear()) {
                addPanel.getId().setText("");
                addPanel.getDescription().setText("");
                addPanel.getPrice().setText("");
                addPanel.getQuantityByPiece().setText("");
                addPanel.getQuantityByBox().setText("");
                addPanel.getPiecesPerBox().setText("");
            }
        });
        updatePanel.getClear().addActionListener(e ->{
            updatePanel.getIdToUpdate().setText("");
            updatePanel.getId().setText("");
            updatePanel.getDescription().setText("");
            updatePanel.getPrice().setText("");
            updatePanel.getQuantityByPiece().setText("");
            updatePanel.getQuantityByBox().setText("");
            updatePanel.getPiecesPerBox().setText("");
        });
    }

    private void setDeleteActionListener() {
        deletePanel.getDelete().addActionListener(e -> {
            if(e.getSource() == deletePanel.getDelete()) {
                String id = deletePanel.getGetId().getText();
                if(!controller.ifProductExist(id)) {
                    ConstantDialog.ID_DOES_NOT_EXIST_DIALOG();
                    return;
                }
                controller.remove(id);
                insertDataInInventoryTable();
                salesId.setProductIdList();
            }
        });
    }

    private void inventoryTableEventListener() {
        tableInventory.getSelectionModel().addListSelectionListener(e -> {
            int row = tableInventory.getSelectedRow();
                if(addDeleteUpdateDetailPanel.getIsDetail()) setStringInventoryDetail(row);
        });

        tableInventory.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Point point = e.getPoint();
                int row = tableInventory.rowAtPoint(point);
                    if(e.getClickCount() == 2
                        && tableInventory.getSelectedRow() != -1) {
                            if(addDeleteUpdateDetailPanel.getIsUpdate()) setStringInventoryUpdate(row);
                    }
            }
        });
    }

    private void setStringInventoryDetail(int row) {
        String[] data = new String[7];
        int NUMBER_OF_COLUMN = 7;
            for(int i = 0; i< NUMBER_OF_COLUMN; i++) {
                data[i] = (String) tableInventory.getValueAt(row,i);
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
                data[i] = (String) tableInventory.getValueAt(row,i);
            }
        // ID UP TO DATE
        updatePanel.getIdToUpdate().setText(data[1]);
        // ID
        updatePanel.getId().setText(data[1]);
        // PRICE
        updatePanel.getPrice().setText(data[2]);
        // QUANTITY BY PIECE
        updatePanel.getQuantityByPiece().setText(data[3]);
        // PIECES PER BOX
        updatePanel.getPiecesPerBox().setText(data[4]);
        // QUANTITY BY BOX
        updatePanel.getQuantityByBox().setText(data[5]);
        // DESCRIPTION
        updatePanel.getDescription().setText(data[6]);
    }

    private String @NotNull [] getStringInventoryUpdate() {
        String[] data = new String[7];
        // ID UP TO DATE
        data[0] = updatePanel.getIdToUpdate().getText();
        // ID
        data[1] = updatePanel.getId().getText();
        // DESCRIPTION
        data[2] = updatePanel.getDescription().getText();
        // PRICE
        data[3] = updatePanel.getPrice().getText();
        // QUANTITY BY PIECE
        data[4] = updatePanel.getQuantityByPiece().getText();
        // PIECES PER BOX
        data[5] = updatePanel.getPiecesPerBox().getText();
        // QUANTITY BY BOX
        data[6] = updatePanel.getQuantityByBox().getText();
        return data;
    }

    private void insertDataInInventoryTable() {
        tableInventory.populateInventoryJTable(controller.show());
    }

    private void leftInventoryActionListener() {
        addDeleteUpdateDetailPanel.getAdd().addActionListener(e -> {
            if(e.getSource() == addDeleteUpdateDetailPanel.getAdd()) {
                inventoryCardPanel.getCardLayout().show(inventoryCardPanel,"add");
                addDeleteUpdateDetailPanel.getAdd().setFont(sansSerif);
                addDeleteUpdateDetailPanel.getDelete().setFont(segoeUI);
                addDeleteUpdateDetailPanel.getDetail().setFont(segoeUI);
                addDeleteUpdateDetailPanel.getUpdate().setFont(segoeUI);

                addDeleteUpdateDetailPanel.getAdd().setEnabled(false);
                addDeleteUpdateDetailPanel.getDelete().setEnabled(true);
                addDeleteUpdateDetailPanel.getDetail().setEnabled(true);
                addDeleteUpdateDetailPanel.getUpdate().setEnabled(true);
                addDeleteUpdateDetailPanel.setIsDetail(false);
                addDeleteUpdateDetailPanel.setIsUpdate(false);
            }
        });

        addDeleteUpdateDetailPanel.getDelete().addActionListener(e -> {
            if(e.getSource() == addDeleteUpdateDetailPanel.getDelete()) {
                inventoryCardPanel.getCardLayout().show(inventoryCardPanel,"delete");
                addDeleteUpdateDetailPanel.getAdd().setFont(segoeUI);
                addDeleteUpdateDetailPanel.getDelete().setFont(sansSerif);
                addDeleteUpdateDetailPanel.getDetail().setFont(segoeUI);
                addDeleteUpdateDetailPanel.getUpdate().setFont(segoeUI);
                addDeleteUpdateDetailPanel.getAdd().setEnabled(true);
                addDeleteUpdateDetailPanel.getDelete().setEnabled(false);
                addDeleteUpdateDetailPanel.getDetail().setEnabled(true);
                addDeleteUpdateDetailPanel.getUpdate().setEnabled(true);
                addDeleteUpdateDetailPanel.setIsDetail(false);
                addDeleteUpdateDetailPanel.setIsUpdate(false);
            }
        });

        addDeleteUpdateDetailPanel.getUpdate().addActionListener(e -> {
            if(e.getSource() == addDeleteUpdateDetailPanel.getUpdate()) {
                inventoryCardPanel.getCardLayout().show(inventoryCardPanel,"update");
                addDeleteUpdateDetailPanel.getAdd().setFont(segoeUI);
                addDeleteUpdateDetailPanel.getDelete().setFont(segoeUI);
                addDeleteUpdateDetailPanel.getDetail().setFont(segoeUI);
                addDeleteUpdateDetailPanel.getUpdate().setFont(sansSerif);
                addDeleteUpdateDetailPanel.getAdd().setEnabled(true);
                addDeleteUpdateDetailPanel.getDelete().setEnabled(true);
                addDeleteUpdateDetailPanel.getDetail().setEnabled(true);
                addDeleteUpdateDetailPanel.getUpdate().setEnabled(false);
                addDeleteUpdateDetailPanel.setIsDetail(false);
                addDeleteUpdateDetailPanel.setIsUpdate(true);
            }
        });

        addDeleteUpdateDetailPanel.getDetail().addActionListener(e -> {
            if(e.getSource() == addDeleteUpdateDetailPanel.getDetail()) {
                inventoryCardPanel.getCardLayout().show(inventoryCardPanel,"detail");
                addDeleteUpdateDetailPanel.getAdd().setFont(segoeUI);
                addDeleteUpdateDetailPanel.getDelete().setFont(segoeUI);
                addDeleteUpdateDetailPanel.getDetail().setFont(sansSerif);
                addDeleteUpdateDetailPanel.getUpdate().setFont(segoeUI);
                addDeleteUpdateDetailPanel.getAdd().setEnabled(true);
                addDeleteUpdateDetailPanel.getDelete().setEnabled(true);
                addDeleteUpdateDetailPanel.getDetail().setEnabled(false);
                addDeleteUpdateDetailPanel.getUpdate().setEnabled(true);
                addDeleteUpdateDetailPanel.setIsDetail(true);
                addDeleteUpdateDetailPanel.setIsUpdate(false);
            }
        });
    }
}