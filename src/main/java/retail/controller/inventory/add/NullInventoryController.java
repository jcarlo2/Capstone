package retail.controller.inventory.add;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.inventory.add.NullInventoryFacade;
import retail.view.main.tab.bot.inventory.center.add.NullInventoryCenter;
import retail.view.main.tab.bot.inventory.manipulator.add.NullInventoryManipulator;
import retail.view.main.tab.top.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static retail.shared.constant.ConstantDialog.*;

public class NullInventoryController {
    private final NullInventoryCenter center;
    private final NullInventoryManipulator manipulator;
    private final NullInventoryFacade facade;
    private final User user;

    public NullInventoryController(NullInventoryCenter center,
                                   @NotNull NullInventoryManipulator manipulator,
                                   NullInventoryFacade facade,
                                   User user) {
        this.center = center;
        this.manipulator = manipulator;
        this.facade = facade;
        this.user = user;

        autoUpdateReportId();
        autoUpdateProductList();

        quantityDocumentListener();
        boxQuantityDocumentListener();
        tableListener();

        productListener();
        manipulator.getAdd().addActionListener(e -> addEvent());
        manipulator.getDelete().addActionListener(e -> center.getTable().removeSelectedRow());
        manipulator.getSave().addActionListener(e -> saveEvent());
        manipulator.getClear().addActionListener(e -> manipulator.clear());
    }

    private void calculateTotalAmount() {
        String quantity = manipulator.getQuantity().getText();
        String price = manipulator.getPrice().getText();
        manipulator.getTotalAmount().setText(facade.multiply(price,quantity));
    }

    private void tableListener() {
        center.getTable().getModel().addTableModelListener(e -> {
            String total = facade.getTotal(center.getTable().tableGrabber());
            center.getTotal().setText(total);
        });
    }

    private void saveEvent() {
        if(center.getTable().getRowCount() > 0) {
            String total = center.getTotal().getText();
            String id = manipulator.getReportId().getText();
            String userName = user.getLastName().getText();
            facade.save(center.getTable().tableGrabber(),total,id,userName);
            SAVED_REPORT();
        }else EMPTY_TABLE();
    }

    private void addEvent() {
        String[] data = manipulator.getData();
        String quantity = manipulator.getQuantity().getText();
        String box = manipulator.getBox().getText();
        if(facade.isValidNumber(data[2],data[3]) && !quantity.equals("0") && !box.equals("0") && facade.isWholeNumber(quantity)) {
            center.getTable().addItem(data);
        }else INVALID_INPUT();
    }

    private void boxQuantityEvent() {
        if(!manipulator.getBox().isFocusOwner()) return;
        String box = manipulator.getBox().getText();
        if(facade.isValidNumber(box)) {
            String pieces = manipulator.getPieces().getText();
            String quantity = facade.multiplication(box,pieces);
            SwingUtilities.invokeLater(() -> {
                manipulator.getQuantity().setText(quantity);
                calculateTotalAmount();

            });
        }
    }

    private void quantityEvent() {
        if(!manipulator.getQuantity().isFocusOwner()) return;
        String quantity = manipulator.getQuantity().getText();
        if(facade.isValidNumber(quantity)) {
            String pieces = manipulator.getPieces().getText();
            String box = facade.division(quantity,pieces);
            SwingUtilities.invokeLater(() -> manipulator.getBox().setText(box));
            calculateTotalAmount();
        }
    }

    private void quantityDocumentListener() {
        manipulator.getQuantity().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                quantityEvent();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                quantityEvent();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void boxQuantityDocumentListener() {
        manipulator.getBox().getDocument().addDocumentListener(new DocumentListener() {
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

    private void productListener() {
        manipulator.getProductId().addItemListener(e -> {
            if(manipulator.getProductId().getSelectedItem() == null) return;
            String id = manipulator.getProductId().getSelectedItem().toString();
            String price = facade.findPriceById(id);
            String pieces = facade.findPiecesPerBoxById(id);
            SwingUtilities.invokeLater(() -> {
                manipulator.getPrice().setText(price);
                manipulator.getPieces().setText(pieces);
                manipulator.clear();
            });
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

    private void autoUpdateReportId() {
        Runnable runnable = () -> {
            String id = manipulator.getReportId().getText();
            if(id.equals("0") || facade.isReportIdExist(id)) {
                SwingUtilities.invokeLater(() -> manipulator.getReportId().setText(facade.generateId()));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,1,1, TimeUnit.SECONDS);
    }
}
