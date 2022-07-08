package retail.controller.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.transaction.add.AddTransactionFacade;
import retail.shared.entity.TransactionItemDetail;
import retail.view.main.tab.bot.transaction.center.add.AddTransactionCenter;
import retail.view.main.tab.bot.transaction.manipulator.add.AddTransactionManipulator;
import retail.view.main.tab.top.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static retail.shared.constant.ConstantDialog.*;

public class AddTransactionController {
    private final AddTransactionManipulator manipulator;
    private final AddTransactionCenter center;
    private final AddTransactionFacade facade;
    private final User user;

    public AddTransactionController(@NotNull User user,
                                    @NotNull AddTransactionManipulator manipulator,
                                    AddTransactionCenter center,
                                    @NotNull AddTransactionFacade facade) {
        this.manipulator = manipulator;
        this.center = center;
        this.facade = facade;
        this.user = user;

        autoUpdateProductList();
        productItemListener();
        autoCalculateTotalAmount();
        autoUpdateReportId();

        soldDocumentListener();
        discountPercentageDocumentListener();
        discountAmountDocumentListener();

        saveEvent();
        addEvent();
        deleteEvent();
        clear();

        manipulator.getProduct().addItemListener(e -> manipulator.clear());
    }

    private void saveEvent() {
        manipulator.getSave().addActionListener(e -> {
            if(center.getTable().getRowCount() == 0) {
                EMPTY_TABLE();
                return;
            }
            String[][] dataList = center.getTable().tableGrabber();
            facade.saveEvent(dataList, gatherReportData());
            ((DefaultTableModel)center.getTable().getModel()).setRowCount(0);
            SAVED_REPORT();
        });
    }

    private void deleteEvent() {
        manipulator.getDelete().addActionListener(e -> {
            center.getTable().removeSelectedRow();
            manipulator.clear();
        });
    }

    private void addEvent() {
        manipulator.getAdd().addActionListener(e -> {
            TransactionItemDetail item = facade.addEvent(manipulator.getAllData());
            if(item == null) return;
            if(!facade.isWholeNumber(item.getSold())) {
                INVALID_INPUT();
                return;
            }
            center.getTable().addItemWithCount(item, "0");
            center.getTable().fixNumberColumn();
            manipulator.clear();
        });
    }

    private void clear() {
        manipulator.getClear().addActionListener(e -> manipulator.clear());
    }

    private void soldEvent() {
        if(!manipulator.getSold().isFocusOwner()) return;
        String soldTotal = facade.soldEvent(manipulator.getAllData());
        String discountAmount = facade.discountPercentageEvent(manipulator.getAllData());
        if(soldTotal == null) return;
        manipulator.getSoldTotal().setText(soldTotal);
        manipulator.getDiscountAmount().setText(discountAmount);
    }

    private void discountPercentageEvent() {
        if(!manipulator.getDiscountPercent().isFocusOwner()) return;
        String discount = facade.discountPercentageEvent(manipulator.getAllData());
        manipulator.getDiscountAmount().setText(discount);
    }

    private void discountAmountEvent() {
        if(!manipulator.getDiscountAmount().isFocusOwner()) return;
        String percent = facade.discountAmountEvent(manipulator.getAllData());
        manipulator.getDiscountPercent().setText(percent);
    }

    private void discountPercentageDocumentListener() {
        manipulator.getDiscountPercent().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                discountPercentageEvent();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                discountPercentageEvent();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void discountAmountDocumentListener() {
        manipulator.getDiscountAmount().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                discountAmountEvent();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                discountAmountEvent();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void soldDocumentListener() {
        manipulator.getSold().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                soldEvent();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                soldEvent();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
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

    private void autoCalculateTotalAmount() {
        center.getTable().getModel().addTableModelListener(e -> {
            String[][] dataList = center.getTable().tableGrabber();
            center.setTotal(facade.calculateReportAmount(dataList));
        });
    }

    private void autoUpdateProductList() {
        Runnable runnable = () -> {
            if(manipulator.getProduct().isNotSameData(facade.getAllProduct())) {
                SwingUtilities.invokeLater(() -> manipulator.getProduct().setProductIdList(facade.getAllProduct()));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }

    private void productItemListener() {
        manipulator.getProduct().addItemListener(e -> {
            if(manipulator.getProduct().getSelectedItem() == null) return;
            String id = manipulator.getProduct().getSelectedItem().toString();
            manipulator.getPrice().setText(facade.findPriceById(id));
            manipulator.clear();
        });
    }

    private String @NotNull [] gatherReportData() {
        String[] data = new String[8];
        data[0] = manipulator.getReportId().getText();
        data[1] = "";
        data[2] = "";
        data[3] = user.getLastName().getText();
        data[4] = center.getTotalAmountText();
        data[5] = "";
        data[6] = "";
        data[7] = "";
        return data;
    }
}
