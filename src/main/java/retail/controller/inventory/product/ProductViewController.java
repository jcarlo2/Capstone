package retail.controller.inventory.product;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.inventory.product.ProductFacade;
import retail.shared.pojo.ProductDisplay;
import retail.view.main.tab.bot.inventory.center.product.ProductCenter;
import retail.view.main.tab.bot.inventory.manipulator.product.ProductManipulator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static retail.shared.constant.ConstantDialog.ADD_PRODUCT;
import static retail.shared.constant.ConstantDialog.DELETE;
import static retail.shared.constant.ConstantDialog.ID_DOES_NOT_EXIST;
import static retail.shared.constant.ConstantDialog.PRODUCT_UPDATE_SUCCESS;

public class ProductViewController {
    private final ProductCenter center;
    private final ProductManipulator manipulator;
    private final ProductFacade facade;

    public ProductViewController(@NotNull ProductCenter center, @NotNull ProductManipulator manipulator, ProductFacade facade) {
        this.center = center;
        this.manipulator = manipulator;
        this.facade = facade;

        autoUpdateTable();
        searchDocumentListener();
        tableMouseListener();
        productDialogComponentListener();

        addEvent();
        deleteEvent();
        updateEvent();
        center.getProductDialog().getCancel().addActionListener(e -> center.getProductDialog().setVisible(false));
        manipulator.getAdd().addActionListener(e -> center.getProductDialog().setVisible(true));
        manipulator.getClear().addActionListener(e -> manipulator.clear());
    }

    private void autoUpdateTable() {
        Runnable runnable = () -> {
            if(!center.getSearch().getText().equals("")) return;
            if(!center.getTable().isSameData(facade.getAllProduct())) {
                SwingUtilities.invokeLater(() -> center.getTable().populateProductTable(facade.getAllProduct()));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,1,1, TimeUnit.SECONDS);
    }

    private void searchDocumentListener() {
        center.getSearch().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchEvent();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchEvent();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void searchEvent() {
        if(!center.getSearch().isFocusOwner()) return;
        String str = center.getSearch().getText();
        SwingUtilities.invokeLater(() -> center.getTable().populateProductTable(facade.search(str)));
    }

    private void updateEvent() {
        manipulator.getUpdate().addActionListener(e -> {
            if(facade.update(manipulator.getData())) PRODUCT_UPDATE_SUCCESS();
            else ID_DOES_NOT_EXIST();
        });
    }

    private void addEvent() {
        center.getProductDialog().getSave().addActionListener(e -> {
            String[] data = center.getProductDialog().getData();
            if(facade.verifyProductDetail(data)) {
                facade.addProduct(data);
                center.getProductDialog().setVisible(false);
                ADD_PRODUCT();
            }else {
                Toolkit.getDefaultToolkit().beep();
            }
        });
    }

    private void deleteEvent() {
        manipulator.getDelete().addActionListener(e -> {
            String id = manipulator.getId().getText();
            if(!facade.isProductExist(id)) ID_DOES_NOT_EXIST();
            else if(DELETE(id) == 0) facade.delete(id);
        });
    }

    private void productDialogComponentListener() {
        center.getProductDialog().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                center.getProductDialog().clear();
            }
        });
    }

    private void tableMouseListener() {
        center.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    ProductDisplay display = facade.convertToDisplay(center.getTable().rowGrabber());
                    String price = new BigDecimal(display.getPrice()).setScale(2, RoundingMode.HALF_EVEN).toString();
                    manipulator.getId().setText(display.getId());
                    manipulator.getNewId().setText(display.getId());
                    manipulator.getDescription().setText(display.getDescription());
                    manipulator.getFullDescription().setText(display.getDescription());
                    manipulator.getPrice().setText(price);
                    manipulator.getPiecesPerBox().setText(display.getPiecesPerBox());
                }
            }
        });
    }
}
