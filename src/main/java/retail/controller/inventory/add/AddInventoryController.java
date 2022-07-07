package retail.controller.inventory.add;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.inventory.add.AddInventoryFacade;
import retail.view.main.tab.bot.inventory.center.add.AddInventoryCenter;
import retail.shared.pojo.DeliveryAdd;
import retail.view.main.tab.bot.inventory.manipulator.add.AddManipulator;
import retail.view.main.tab.top.User;

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
    private final AddManipulator manipulator;
    private final AddInventoryFacade facade;
    private final User user;

    public AddInventoryController(AddInventoryCenter center, @NotNull AddManipulator manipulator, AddInventoryFacade facade, User user) {
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
            DeliveryAdd item = facade.createItemDetail(manipulator.getData());
            if(facade.isValidNumber(item.getQuantityPerBox())) {
                center.getTable().addItem(item);
            }
        });
    }

    private void saveEvent() {
        manipulator.getSave().addActionListener(e -> {
            boolean check = manipulator.getNullProduct().isEnabled();
            if(center.getTable().getRowCount() == 0) {
                EMPTY_TABLE();
                return;
            }
            String[][] dataList = center.getTable().tableGrabber();
            String[] data = {manipulator.getReportId().getText(),user.getLastName().getText(),"",""};
            if(check) facade.saveDelivery(dataList,data);
            else facade.saveNull(dataList,data);

            ((DefaultTableModel)center.getTable().getModel()).setRowCount(0);
            manipulator.clear();
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
            String pieces = new BigDecimal(facade.findBoxPiecesById(id)).setScale(2, RoundingMode.HALF_EVEN).toString();
            if(!manipulator.getPiecesPerBox().getText().equals(pieces)) {
                SwingUtilities.invokeLater(() -> manipulator.getPiecesPerBox().setText(pieces));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, 1, 500, TimeUnit.MILLISECONDS);
    }

    private void autoUpdateReportId() {
        Runnable runnable = () -> {
            boolean check = manipulator.getNullProduct().isEnabled();
            String id = manipulator.getReportId().getText();
            String initial = "";
            if(id.length() > 2) initial = id.substring(0,2);

            if(initial.equals("IR") && !check)
                SwingUtilities.invokeLater(() -> manipulator.getReportId().setText(facade.generateId(false)));
            else if(initial.equals("NR") && check)
                SwingUtilities.invokeLater(() -> manipulator.getReportId().setText(facade.generateId(true)));
            else if(id.equals("0") || facade.isDeliveryReportIdExist(id) || facade.isNullReportExist(id))
                SwingUtilities.invokeLater(() -> manipulator.getReportId().setText(facade.generateId(check)));
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,1,1,TimeUnit.SECONDS);
    }
}
