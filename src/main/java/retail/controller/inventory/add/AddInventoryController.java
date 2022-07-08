package retail.controller.inventory.add;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.inventory.add.AddInventoryFacade;
import retail.view.main.tab.bot.inventory.center.add.AddInventoryCenter;
import retail.view.main.tab.bot.inventory.manipulator.add.AddInventoryManipulator;
import retail.view.main.tab.top.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static retail.shared.constant.ConstantDialog.*;

public class AddInventoryController {
    private final AddInventoryCenter center;
    private final AddInventoryManipulator manipulator;
    private final AddInventoryFacade facade;
    private final User user;

    public AddInventoryController(AddInventoryCenter center, @NotNull
                                  AddInventoryManipulator manipulator,
                                  AddInventoryFacade facade,
                                  User user) {
        this.center = center;
        this.manipulator = manipulator;
        this.facade = facade;
        this.user = user;

        autoUpdateProductList();
        autoUpdateReportId();
        calculateTotalAmount();
        tableListener();
        productItemListener();

        quantityDocumentListener();
        boxQuantityDocumentListener();
        priceDocumentListener();
        discountPercentageDocumentListener();
        discountTotalDocumentListener();

        manipulator.getAdd().addActionListener(e -> addEvent());
        manipulator.getDelete().addActionListener(e -> center.getTable().removeSelectedRow());
        manipulator.getClear().addActionListener(e -> manipulator.clear());
        manipulator.getSave().addActionListener(e -> saveEvent());
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
        data[0] = "0";
        if(!facade.isValidNumber(data) ||  !facade.isWholeNumber(data[1]) || !(Double.parseDouble(data[4]) > 0)) {
            INVALID_INPUT();
        } else {
            String total = facade.subtraction(data[4],data[6]);
            center.getTable().addItem(manipulator.getData(),total);
        }
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
        service.scheduleAtFixedRate(runnable,1,1,TimeUnit.SECONDS);
    }

    private void productItemListener() {
        manipulator.getProductId().addItemListener(e -> {
            if(manipulator.getProductId().getSelectedItem() == null) return;
            String id = manipulator.getProductId().getSelectedItem().toString();
            manipulator.getPieces().setText(facade.getPiecesPerBoxById(id));
            manipulator.clear();
        });
    }

    private void quantityEvent() {
        if(!manipulator.getQuantity().isFocusOwner()) return;
        String quantity = manipulator.getQuantity().getText();
        String pieces = manipulator.getPieces().getText();
        if(facade.isValidNumber(quantity)) {
            String box = facade.division(quantity,pieces);
            manipulator.getBox().setText(box);
        }
    }

    private void boxQuantityEvent() {
        if(!manipulator.getBox().isFocusOwner()) return;
        String box = manipulator.getBox().getText();
        String pieces = manipulator.getPieces().getText();
        if(facade.isValidNumber(box)) {
            String quantity = facade.multiplication(box,pieces);
            manipulator.getQuantity().setText(quantity);
        }
    }

    private void calculateTotalAmount() {
        String price = manipulator.getPrice().getText();
        String discount = manipulator.getDiscountTotal().getText();
        if(facade.isValidNumber(price,discount)) {
            String total = facade.subtract(price,discount);
            manipulator.getTotalAmount().setText(total);
        }else manipulator.getTotalAmount().setText("0");
    }

    private void discountPercentageEvent() {
        String price = manipulator.getPrice().getText();
        String percentage = manipulator.getDiscountPercentage().getText();
        if(facade.isValidNumber(price,percentage)) {
            String total = facade.calculateDiscountAmount(price,percentage);
            manipulator.getDiscountTotal().setText(total);
        }else manipulator.getTotalAmount().setText("0");
    }

    private void discountTotalEvent() {
        if(!manipulator.getDiscountTotal().isFocusOwner()) return;
        String price = manipulator.getPrice().getText();
        String total = manipulator.getDiscountTotal().getText();
        if(facade.isValidNumber(price,total)) {
            String discount = facade.calculateDiscountPercentage(price,total);
            manipulator.getDiscountPercentage().setText(discount);
        }else manipulator.getTotalAmount().setText("0");
    }

    private void discountPercentageDocumentListener() {
        manipulator.getDiscountPercentage().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(!manipulator.getDiscountPercentage().isFocusOwner()) return;
                discountPercentageEvent();
                calculateTotalAmount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(!manipulator.getDiscountPercentage().isFocusOwner()) return;
                discountPercentageEvent();
                calculateTotalAmount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void discountTotalDocumentListener() {
        manipulator.getDiscountTotal().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                discountTotalEvent();
                calculateTotalAmount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                discountTotalEvent();
                calculateTotalAmount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void priceDocumentListener() {
        manipulator.getPrice().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                discountPercentageEvent();
                calculateTotalAmount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                discountPercentageEvent();
                calculateTotalAmount();
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
}




































