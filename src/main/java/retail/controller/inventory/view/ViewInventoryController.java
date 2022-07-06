package retail.controller.inventory.view;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import retail.model.facade.inventory.view.ViewInventoryFacade;
import retail.shared.custom.jpanel.ViewManipulator;
import retail.shared.pojo.InventoryItem;
import retail.view.main.tab.bot.inventory.center.view.InventoryViewCenter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ViewInventoryController {
    private final InventoryViewCenter center;
    private final ViewManipulator manipulator;
    private final ViewInventoryFacade facade;

    public ViewInventoryController(InventoryViewCenter center, ViewManipulator manipulator, ViewInventoryFacade facade) {
        this.center = center;
        this.manipulator = manipulator;
        this.facade = facade;

        autoUpdateList();
        listMouseListener();
    }

    private void autoUpdateList() {
        Runnable runnable = () -> {
            if(!manipulator.getSearch().getText().equals("")) return;
            String search = Objects.requireNonNull(manipulator.getSearchType().getSelectedItem()).toString();
            String type = Objects.requireNonNull(manipulator.getType().getSelectedItem()).toString();
            if(search.equals("Auto")) {
                if(type.equals("Null")) reportListOption("Auto","Null");
                else reportListOption("Auto","Delivery");
            }else {
                if(type.equals("Null")) reportListOption("Date","Null");
                else reportListOption("Date","Delivery");
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }

    @Contract(pure = true)
    private void reportListOption(@NotNull String search, String type) {
        if(search.equals("Auto")) {
            if(type.equals("Null")) {
                if(manipulator.getList().isNotSameNullList(facade.getAllNullReport()))
                    SwingUtilities.invokeLater(() -> manipulator.getList().populateNullList(facade.getAllNullReport()));
            } else if(manipulator.getList().isNotSameDeliveryList(facade.getAllDeliveryReport()))
                    SwingUtilities.invokeLater(() -> manipulator.getList().populateDeliveryList(facade.getAllDeliveryReport()));
        }else {
            String start = new SimpleDateFormat("yyyy/MM/dd HH:mm:00").format(manipulator.getStart().getValue());
            String end = new SimpleDateFormat("yyyy/MM/dd HH:mm:00").format(manipulator.getEnd().getValue());
            if(type.equals("Null")) {
                if(manipulator.getList().isNotSameNullList(facade.getAllNullReportByDate(start,end)))
                    SwingUtilities.invokeLater(() -> manipulator.getList().populateNullList(facade.getAllNullReportByDate(start, end)));
            }else if(manipulator.getList().isNotSameDeliveryList(facade.getAllDeliveryReportByDate(start,end)))
                SwingUtilities.invokeLater(() -> manipulator.getList().populateDeliveryList(facade.getAllDeliveryReportByDate(start, end)));
        }
    }

    private void listMouseListener() {
        manipulator.getList().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    String id = manipulator.getList().getSelectedValue();
                    if(id == null) return;
                    id = id.substring(19,37);
                    ArrayList<InventoryItem> itemList;
                    if(id.charAt(0) == 'I') itemList = facade.findDeliveryReportById(id);
                    else itemList = facade.findNullReportById(id);
                    System.out.println(itemList.size() + " // " + id);
                }
            }
        });
    }
}
