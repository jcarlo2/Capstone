package retail.controller.transaction.view;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.transaction.view.ViewTransactionFacade;
import retail.view.main.tab.bot.transaction.center.view.TransactionViewCenter;
import retail.shared.custom.jpanel.ViewManipulator;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static retail.shared.constant.ConstantDialog.DELETE_ALL;
import static retail.shared.constant.ConstantDialog.DELETE_ALL_OPTION;

public class ViewTransactionController {
    private final ViewManipulator manipulator;
    private final TransactionViewCenter center;
    private final ViewTransactionFacade facade;

    public ViewTransactionController(TransactionViewCenter center, ViewManipulator manipulator, ViewTransactionFacade facade) {
        this.manipulator = manipulator;
        this.center = center;
        this.facade = facade;

        autoUpdateList();

        deleteEvent();
        listMouseListener();
        searchDocumentListener();
    }

    private void autoUpdateList() {
        Runnable runnable = () -> {
            if(!manipulator.getSearch().getText().equals("")) return;
            String search = Objects.requireNonNull(manipulator.getSearchType().getSelectedItem()).toString();
            String type = Objects.requireNonNull(manipulator.getReportType().getSelectedItem()).toString();
            if(search.equals("Auto")) {
                if(type.equals("All")) reportListOption("Auto","All");
                else reportListOption("Auto","Valid");
            }else {
                if(type.equals("All")) reportListOption("Date","All");
                else reportListOption("Date","Valid");
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }

    private void reportListOption(@NotNull String search, String type) {
        if(search.equals("Auto")) {
            if(type.equals("All")) {
                if(manipulator.getList().isNotSameTransactionList(facade.getAllReport())) {
                    SwingUtilities.invokeLater(() -> manipulator.getList().populateTransactionList(facade.getAllReport()));
                }
            }else {
                if(manipulator.getList().isNotSameTransactionList(facade.getAllValidReport())) {
                    SwingUtilities.invokeLater(() -> manipulator.getList().populateTransactionList(facade.getAllValidReport()));
                }
            }
        }else {
            String start = new SimpleDateFormat("yyyy/MM/dd HH:mm:00").format(manipulator.getStart().getValue());
            String end = new SimpleDateFormat("yyyy/MM/dd HH:mm:00").format(manipulator.getEnd().getValue());
            if(type.equals("All")) {
                if(manipulator.getList().isNotSameTransactionList(facade.getAllReportByDate(start,end))) {
                    SwingUtilities.invokeLater(() -> manipulator.getList().populateTransactionList(facade.getAllReportByDate(start,end)));
                }
            }else {
                if(manipulator.getList().isNotSameTransactionList(facade.getAllValidReportByDate(start,end))) {
                    SwingUtilities.invokeLater(() -> manipulator.getList().populateTransactionList(facade.getAllValidReportByDate(start,end)));
                }
            }
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
        String valid = Objects.requireNonNull(manipulator.getReportType().getSelectedItem()).toString();
        String search = manipulator.getSearch().getText();
        ArrayList<TransactionDetail> reportList;

        if(valid.equals("Valid Only")) reportList = facade.findAllValidReportByString(search);
        else reportList = facade.findAllReportByString(search);

        manipulator.getList().populateTransactionList(reportList);
    }

    private void listMouseListener() {
        manipulator.getList().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    String id = manipulator.getList().getSelectedValue();
                    if(id == null) return;
                    id = facade.convertListSelectedItem(id);
                    String total = facade.getReportPrice(id);
                    ArrayList<TransactionItemDetail> itemList = facade.getAllReportItem(id);
                    center.getTable().addAllItem(itemList);
                    center.getTable().fixNumberColumn();
                    center.getId().setText(id);
                    center.getTotal().setText(total.substring(0,total.length() - 2));
                }
            }
        });
    }

    private void deleteEvent() {
        manipulator.getDelete().addActionListener(e -> {
            String id = center.getId().getText();
            if(id.equals("")) return;
            String[] idList = facade.getIdList(id);

            if(idList.length == 1 || !facade.isValid(id)) {
                if(DELETE_ALL() == 0) facade.delete(idList);
            }else {
                int check = DELETE_ALL_OPTION(idList);
                if(check == 0) {
                    facade.delete(id);
                    facade.revalidate(idList[1]);
                } else if(check == 1) facade.delete(idList);
            }
        });
    }
}
