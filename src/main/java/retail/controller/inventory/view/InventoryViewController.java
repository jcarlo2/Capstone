package retail.controller.inventory.view;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.inventory.view.InventoryViewFacade;
import retail.shared.custom.jpanel.ViewManipulator;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.NullProductReport;
import retail.view.main.tab.bot.inventory.center.view.InventoryViewCenter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InventoryViewController {
    private final InventoryViewCenter center;
    private final ViewManipulator manipulator;
    private final InventoryViewFacade facade;

    public InventoryViewController(InventoryViewCenter center, ViewManipulator manipulator, InventoryViewFacade facade) {
        this.center = center;
        this.manipulator = manipulator;
        this.facade = facade;

        autoUpdateList();
        reportTypeListener();
        searchDocumentListener();
        listMouseListener();
    }

    private void reportTypeListener() {
        manipulator.getReportType().addItemListener(e -> {
            if(manipulator.getReportType().getSelectedItem() == null) return;
            String report = manipulator.getReportType().getSelectedItem().toString();
            if(report.equals("Delivery")) SwingUtilities.invokeLater(() -> center.getCard().show(center.getWrapper3(),"delivery"));
            else SwingUtilities.invokeLater(() -> center.getCard().show(center.getWrapper3(),"null"));
        });
    }

    private void autoUpdateList() {
        Runnable runnable = () -> {
            if(!manipulator.getSearch().getText().equals("") || manipulator.getSearchType().getSelectedItem() == null ||
                    manipulator.getReportType().getSelectedItem() == null) return;
            String search = manipulator.getSearchType().getSelectedItem().toString();
            String report = manipulator.getReportType().getSelectedItem().toString();
            SwingUtilities.invokeLater(() -> findReportOption(search,report));
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,1,1, TimeUnit.SECONDS);
    }

    private void findReportOption(@NotNull String search, String report) {
        if(search.equals("Auto")) {
            if(report.equals("Delivery")) autoDeliveryReport();
            else autoNullReport();
        }else {
            String start = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(manipulator.getStart().getValue());
            String end = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(manipulator.getEnd().getValue());
            if(report.equals("Delivery")) dateDeliveryReport(start,end);
            else dateNullReport(start,end);
        }
    }

    private void dateNullReport(String start, String end) {
        if(manipulator.getList().isNotSameNullList(facade.findAllNullReportByDate(start,end))) {
            manipulator.getList().populateNullList(facade.findAllNullReportByDate(start,end));
        }
    }

    private void dateDeliveryReport(String start, String end) {
        if(manipulator.getList().isNotSameDeliveryList(facade.findAllDeliveryReportByDate(start,end))) {
            manipulator.getList().populateDeliveryList(facade.findAllDeliveryReportByDate(start,end));
        }
    }

    private void autoNullReport() {
        if(manipulator.getList().isNotSameNullList(facade.findAllNullReport())) {
            manipulator.getList().populateNullList(facade.findAllNullReport());
        }
    }

    private void autoDeliveryReport() {
        if(manipulator.getList().isNotSameDeliveryList(facade.findAllDeliveryReport())) {
            manipulator.getList().populateDeliveryList(facade.findAllDeliveryReport());
        }
    }

    private void searchDocumentListener() {
        manipulator.getSearch().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void search() {
        if(manipulator.getReportType().getSelectedItem() == null) return;
        String reportType = manipulator.getReportType().getSelectedItem().toString();
        String search = manipulator.getSearch().getText();
        if(reportType.equals("Delivery")) {
            ArrayList<DeliveryDetail> deliveryList = facade.findAllDeliveryReportById(search);
            manipulator.getList().populateDeliveryList(deliveryList);
        } else nullSearch(search);
    }

    private void nullSearch(@NotNull String search) {
        if(search.length() > 1 && search.charAt(0) == 'T') {
            ArrayList<NullProductReport> nullList = facade.findNullReportByLink(search);
            manipulator.getList().populateNullList(nullList);
            return;
        }
        ArrayList<NullProductReport> nullList = facade.findAllNullReportById(search);
        manipulator.getList().populateNullList(nullList);
    }

    private void listMouseListener() {
        manipulator.getList().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    if(manipulator.getReportType().getSelectedItem() == null) return;
                    String type = manipulator.getReportType().getSelectedItem().toString();
                    String id = manipulator.getList().getSelectedValue();
                    if(id == null) return;

                    id = facade.substringReportId(id);
                    center.getId().setText(id);
                    if(type.equals("Null")) {
                        center.getTotal().setText(facade.findNullReportTotal(id));
                        center.getTable2().addAllItem(facade.findAllNullItemById(id));
                    }else {
                        center.getTotal().setText(facade.findDeliveryReportTotal(id));
                        center.getTable1().addAllItem(facade.findAllDeliveryItemById(id));
                    }
                }
            }
        });
    }
}
