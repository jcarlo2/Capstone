package retail.controller.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.transaction.add.ReturnTransactionFacade;
import retail.shared.constant.ConstantDialog;
import retail.shared.custom.jdialog.ReturnJDialog;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;
import retail.view.main.tab.bot.transaction.center.add.ReturnedTransactionCenter;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.ReturnedTransactionManipulator;
import retail.view.main.tab.top.UserPanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static retail.shared.constant.ConstantDialog.ADD_ALL;
import static retail.shared.constant.ConstantDialog.DELETE_ALL;

public class ReturnTransactionController {
    private final ReturnTransactionFacade facade;
    private final ReturnedTransactionManipulator manipulator;
    private final ReturnedTransactionCenter center;
    private final UserPanel userPanel;
    private final ReturnJDialog returnDialog;

    public ReturnTransactionController(UserPanel userPanel,
                                       @NotNull ReturnedTransactionManipulator manipulator,
                                       @NotNull ReturnedTransactionCenter center,
                                       @NotNull ReturnJDialog returnDialog,
                                       ReturnTransactionFacade facade) {
        this.facade = facade;
        this.center = center;
        this.manipulator = manipulator;
        this.userPanel = userPanel;
        this.returnDialog = returnDialog;

        autoUpdateList();
        listDocumentListener();
        listMouseListener();
        topTableMouseListener();
        botTableMouseListener();
        botTableModelListener();

        returnDialogComponentListener();
        productCountDocumentListener();
        soldDocumentListener();
        returnDialog.getSave().addActionListener(e -> returnSaveEvent());
        returnDialog.getCancel().addActionListener( e -> returnDialog.setVisible(false));

        manipulator.getAdd().addActionListener(e -> addEvent());
        manipulator.getAddAll().addActionListener(e -> addAllEvent());
        manipulator.getDelete().addActionListener(e -> deleteEvent());
        manipulator.getDeleteAll().addActionListener(e -> deleteAllEvent());
        manipulator.getSave().addActionListener(e -> saveEvent());
    }

    private void autoUpdateList() {
        Runnable runnable = () -> {
            if(!manipulator.getSearch().getText().equals("")) return;
            if(manipulator.getList().isNotSameTransactionList(facade.getAllValidReport())) {
                manipulator.getList().populateTransactionList(facade.getAllValidReport());
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }

    private void addAllEvent() {
        if(ADD_ALL() != 0) return;
        String[][] dataList = center.getTopTable().tableGrabber();
        for(TransactionItemDetail item : facade.getRowDataByNoneReason(dataList)) {
            center.getBotTable().addItemWithCount(item,"0");
        }
        center.getTopModel().setRowCount(0);
        for(TransactionItemDetail item : facade.removeRowWithNoneReason(dataList)) {
            center.getTopTable().addItemWithReason(item,"Exp/Dam");
        }
        center.fixTableColumn();
    }

    private void addEvent() {
        int row = center.getTopTable().getSelectedRow();
        if(row == -1) return;
        String[] data =  center.getTopTable().rowGrabber();
        if(Objects.requireNonNull(data)[7].equals("--")) {
            TransactionItemDetail item = facade.createItem(data);
            center.getBotTable().addItemWithCount(item, "0");
            center.getTopTable().removeRow(row);
            center.fixTableColumn();
        }else {
            returnDialog.setVisible(true);
        }
    }

    private void deleteAllEvent() {
        if(DELETE_ALL() != 0) return;
        String id = facade.reverseConvertId(manipulator.getNewReportId().getText());
        center.getBotModel().setRowCount(0);
        center.getTopModel().setRowCount(0);
        center.getTopTable().addAllItem(facade.getAllReportItem(id));
        center.getTopTable().fixNumberColumn();
    }

    private void deleteEvent() {
        int row = center.getBotTable().getSelectedRow();
        if(row == -1) return;
        String[] data = center.getBotTable().rowGrabber();
        String reportId = facade.reverseConvertId(manipulator.getNewReportId().getText());
        center.getTopTable().addItemWithCount(facade.recoverItem(Objects.requireNonNull(data),reportId), "0");
        center.getBotModel().removeRow(row);
        center.fixTableColumn();
    }

    private void saveEvent() {
        if(center.getTopTable().getRowCount() > 0) {
            ConstantDialog.SAVE_FAILED();
            return;
        }
        if(facade.verifyTableForSaving(center.getBotTable().tableGrabber())) {
            String[][] dataList = center.getBotTable().tableGrabber();
            String id = manipulator.getNewReportId().getText();
            String user = userPanel.getLastName().getText();
            String newTotal = center.getNewTotal().getText();
            String credit = center.getCredit().getText();
            facade.saveReport(dataList,id,user,newTotal,credit);
            center.getTopModel().setRowCount(0);
            center.getBotModel().setRowCount(0);
            ConstantDialog.SAVED_REPORT();
        }
    }

    private void returnSaveEvent() {
        if(facade.verifyReturnedItemDetails(returnDialog.getProductData())) {
            int row = center.getTopTable().getSelectedRow();
            String add = facade.addition(returnDialog.getSold().getText(),returnDialog.getProductCount().getText());
            if(facade.lessThanComparison(add,returnDialog.getCount().getText()) || row == -1) {
                Toolkit.getDefaultToolkit().beep();
                return;
            }

            TransactionItemDetail item = facade.createItem(center.getTopTable().rowGrabber());
            item.setSold(returnDialog.getSold().getText());
            item.setTotalAmount(returnDialog.getTotalAmount().getText());
            item.setSoldTotal(returnDialog.getSoldTotal().getText());
            center.getBotTable().addItemWithCount(item,returnDialog.getProductCount().getText());
            center.getTopTable().removeRow(row);
            center.fixTableColumn();
            returnDialog.setVisible(false);
        }
    }

    private void soldEvent() {
        if(!returnDialog.getSold().isFocusOwner()) return;
        String sold = returnDialog.getSold().getText();
        if(facade.isValidNumber(sold)) {
            String price = returnDialog.getPrice().getText();
            String percent = returnDialog.getDiscountPercentage().getText();
            String total = facade.calculateSoldTotal(new BigDecimal(price), new BigDecimal(sold));
            String discount = facade.calculateDiscountAmount(new BigDecimal(total),new BigDecimal(percent));
            String totalAmount = facade.calculateTotalAmount(total,discount);

            returnDialog.getTotalAmount().setText(totalAmount);
            returnDialog.getSoldTotal().setText(total);
            returnDialog.getDiscountTotal().setText(discount);
        }
    }

    private void productCountEvent(boolean isUpdate) {
        String productCount = returnDialog.getProductCount().getText();
        if(facade.isValidNumber(productCount)) {
            String totalCount = returnDialog.getCount().getText();
            double prodCount = Double.parseDouble(productCount);
            double count = Double.parseDouble(totalCount);
            if(prodCount <= count) {
                autoCalculate(count,prodCount);
            }else {
                if(isUpdate) Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    private void autoCalculate(double count, double prodCount) {
        String sold = facade.subtraction(count,prodCount);
        String price = returnDialog.getPrice().getText();
        String soldTotal = facade.calculateSoldTotal(new BigDecimal(price),new BigDecimal(sold));
        String percent = returnDialog.getDiscountPercentage().getText();
        String discountTotal = facade.calculateDiscountAmount(new BigDecimal(soldTotal),new BigDecimal(percent));
        returnDialog.getSold().setText(sold);
        returnDialog.getSoldTotal().setText(soldTotal);
        returnDialog.getDiscountTotal().setText(discountTotal);
        String total = facade.calculateTotalAmount(soldTotal,discountTotal);
        returnDialog.getTotalAmount().setText(total);
    }

    private void topTableMouseListener() {
        center.getTopTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    addEvent();
                }else if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
                    addAllEvent();
                }
            }
        });
    }

    private void botTableMouseListener() {
        center.getBotTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    deleteEvent();
                }else if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
                    deleteAllEvent();
                }
            }
        });
    }

    private void botTableModelListener() {
        center.getBotTable().getModel().addTableModelListener(e -> {
            String[][] dataList = center.getBotTable().tableGrabber();
            String newTotal = facade.calculateNewTotal(dataList);
            center.getNewTotal().setText(newTotal);
            String credit = facade.calculateNewCredit(center.getTopTotal().getText(),center.getNewTotal().getText());
            center.getCredit().setText(credit);
        });
    }

    private void soldDocumentListener() {
        returnDialog.getSold().getDocument().addDocumentListener(new DocumentListener() {
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

    private void productCountDocumentListener() {
        returnDialog.getProductCount().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                productCountEvent(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                productCountEvent(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void listDocumentListener() {
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

    private void listMouseListener() {
        manipulator.getList().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    String id = manipulator.getList().getSelectedValue().substring(19,37);
                    if(facade.isReportExist(id)) {
                        setReportItem(id);
                        setTopTotalAmount();
                        setCredit();
                    }
                }
            }
        });
    }

    private void returnDialogComponentListener() {
        returnDialog.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                int row = center.getTopTable().getSelectedRow();
                if(row == -1) return;
                TransactionItemDetail item = facade.createItem(center.getTopTable().rowGrabber());
                returnDialog.getProductId().setText(item.getProductId());
                returnDialog.getPrice().setText(item.getPrice());
                returnDialog.getCount().setText(item.getSold());
                returnDialog.getDiscountPercentage().setText(item.getDiscountPercentage());
                returnDialog.setLocationRelativeTo(null);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                returnDialog.clear();
            }
        });
    }

    private void setReportItem(String id) {
        ArrayList<TransactionItemDetail> itemList = facade.getAllReportItem(id);
        center.getTopTable().addAllItem(itemList);
        manipulator.getNewReportId().setText(facade.convertId(id));
        center.getBotModel().setRowCount(0);
        center.fixTableColumn();
    }

    private void setTopTotalAmount() {
        String newId = manipulator.getNewReportId().getText();
        if(newId.equals("")) return;
        center.getTopTotal().setText(facade.getReportTotalAmount(newId));
    }

    private void setCredit() {
        String total = center.getTopTotal().getText();
        center.getCredit().setText(facade.negation(total));
    }

    private void search() {
        ArrayList<TransactionDetail> reportList = facade.findAllReportByString(manipulator.getSearch().getText());
        manipulator.getList().populateTransactionList(reportList);
    }
}
