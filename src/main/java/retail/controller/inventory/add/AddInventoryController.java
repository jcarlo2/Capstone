package retail.controller.inventory.add;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.inventory.add.AddInventoryFacade;
import retail.shared.pojo.InventoryItemDetail;
import retail.view.main.tab.bot.inventory.center.add.AddInventoryCenter;
import retail.view.main.tab.bot.inventory.manipulator.panel.add.AddInventoryManipulator;
import retail.view.main.tab.top.UserPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static retail.shared.constant.ConstantDialog.EMPTY_TABLE;
import static retail.shared.constant.ConstantDialog.SAVED_REPORT;

public class AddInventoryController {
    private final AddInventoryCenter center;
    private final AddInventoryManipulator manipulator;
    private final AddInventoryFacade facade;
    private final UserPanel user;

    public AddInventoryController(AddInventoryCenter center, @NotNull AddInventoryManipulator manipulator, AddInventoryFacade facade, UserPanel user) {
        this.center = center;
        this.manipulator = manipulator;
        this.facade = facade;
        this.user = user;

        autoUpdateProductList();
        autoUpdateDetail();
        autoUpdateReportId();

        quantityPerPiecesDocumentListener();
        quantityPerBoxDocumentListener();

        addEvent();
        saveEvent();
        manipulator.getDelete().addActionListener(e -> center.getTable().removeSelectedRow());
        manipulator.getProductId().addItemListener(e -> manipulator.clear());
        manipulator.getClear().addActionListener(e -> manipulator.clear());
    }

    private void addEvent() {
        manipulator.getAdd().addActionListener(e -> {
            InventoryItemDetail item = facade.createItemDetail(manipulator.getData());
            if(facade.isValidNumber(item.getQuantityPerPieces()) && facade.isValidNumber(item.getQuantityPerBox())) {
                center.getTable().addItem(item);
            }
        });
    }

    private void saveEvent() {
        manipulator.getSave().addActionListener(e -> {
            if(center.getTable().getRowCount() == 0) {
                EMPTY_TABLE();
                return;
            }
            String[][] dataList = center.getTable().tableGrabber();
            String[] data = {manipulator.getReportId().getText(),user.getLastName().getText(),""};
            facade.save(dataList,data);
            ((DefaultTableModel)center.getTable().getModel()).setRowCount(0);
            SAVED_REPORT();
        });
    }

    private void pieceQuantityEvent() {
        if(!manipulator.getQuantityPerPiece().isFocusOwner()) return;
        String quantity = manipulator.getQuantityPerPiece().getText();
        String pieces = manipulator.getPiecesPerBox().getText();
        if(facade.isValidNumber(quantity)) {
            quantity = facade.division(quantity,pieces);
            manipulator.getQuantityPerBox().setText(quantity);
        }
    }

    private void boxQuantityEvent() {
        if(!manipulator.getQuantityPerBox().isFocusOwner()) return;
        String quantity = manipulator.getQuantityPerBox().getText();
        String pieces = manipulator.getPiecesPerBox().getText();
        if(facade.isValidNumber(quantity)) {
            quantity = facade.multiplication(quantity,pieces);
            manipulator.getQuantityPerPiece().setText(quantity);
        }
    }

    private void quantityPerBoxDocumentListener() {
        manipulator.getQuantityPerBox().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boxQuantityEvent();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                boxQuantityEvent();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void quantityPerPiecesDocumentListener() {
        manipulator.getQuantityPerPiece().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                pieceQuantityEvent();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                pieceQuantityEvent();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void autoUpdateProductList() {
        Runnable runnable = () -> {
            if(manipulator.getProductId().isNotSameData(facade.getAllProduct())) {
                SwingUtilities.invokeLater(() -> manipulator.getProductId().setProductIdList(facade.getAllProduct()));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }

    private void autoUpdateDetail() {
        Runnable runnable = () -> {
            if(manipulator.getProductId().getSelectedItem() == null) return;
            String id = manipulator.getProductId().getSelectedItem().toString();
            String price = new BigDecimal(facade.findPriceById(id)).setScale(2, RoundingMode.HALF_EVEN).toString();
            String pieces = new BigDecimal(facade.findBoxPiecesById(id)).setScale(2, RoundingMode.HALF_EVEN).toString();
            if(!manipulator.getPrice().getText().equals(price)) {
                SwingUtilities.invokeLater(() -> {
                    manipulator.getPrice().setText(price);
                    manipulator.getPiecesPerBox().setText(pieces);
                });
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, 1, 500, TimeUnit.MILLISECONDS);
    }

    private void autoUpdateReportId() {
        Runnable runnable = () -> {
            String id = manipulator.getReportId().getText();
            if(id.equals("0") || facade.isReportIdExist(id)) {
                SwingUtilities.invokeLater(() -> manipulator.getReportId().setText(facade.generateId()));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,1,1,TimeUnit.SECONDS);
    }
}
