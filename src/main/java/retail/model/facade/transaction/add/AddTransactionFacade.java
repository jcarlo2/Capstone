package retail.model.facade.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.model.service.ReportCreator;
import retail.model.service.Calculate;
import retail.model.service.TableUtil;
import retail.model.service.transaction.AddTransactionService;
import retail.shared.entity.Merchandise;
import retail.shared.entity.TransactionDetail;
import retail.shared.entity.TransactionItemDetail;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.AddTransactionManipulator;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class AddTransactionFacade {
    private final AddTransactionService addService = new AddTransactionService();
    private final Calculate calculate = new Calculate();
    private final ReportCreator reportCreator = new ReportCreator();
    private final TableUtil grabber = new TableUtil();

    public ArrayList<Merchandise> getAllProduct() {
        return addService.getAllProduct();
    }

    public String findPriceById(String productId) {
        return addService.findPriceById(productId);
    }

    public String calculateSoldTotal(BigDecimal price, BigDecimal sold) {
        return calculate.calculateSoldTotal(price, sold);
    }

    public String calculateDiscountAmount(BigDecimal soldTotal, BigDecimal discountPercent) {
        return calculate.calculateDiscountAmount(soldTotal,discountPercent);
    }

    public boolean isValidNumber(String ... input) {
        return calculate.isValidNumber(input);
    }

    public String calculateDiscountPercentage(BigDecimal soldTotal, BigDecimal amount) {
        return calculate.calculateDiscountPercentage(soldTotal,amount);
    }

    public TransactionItemDetail createReportItem(String[] data) {
        return reportCreator.createReportItem(data);
    }

    public void fixNumberColumn(@NotNull JTable table) {
        for(int i = 0; i< table.getRowCount(); i++) {
            table.setValueAt(i+1, i,0);
        }
    }

    public void clear(@NotNull AddTransactionManipulator addManipulator) {
        addManipulator.getSold().setText("0");
        addManipulator.getSoldTotal().setText("0");
        addManipulator.getDiscountPercent().setText("0");
        addManipulator.getDiscountAmount().setText("0");
    }

    public String generateId() {
        return addService.generateId();
    }

    public String[][] tableGrabber(JTable table) {
        return grabber.tableGrabber(table);
    }

    public TransactionDetail createTransactionDetail(String[] data) {
        return reportCreator.createTransactionDetail(data);
    }

    public void saveReport(TransactionDetail report, ArrayList<TransactionItemDetail> itemList) {
        addService.addReport(report,itemList);
        addService.reflectToInventory(itemList);
    }

    public ArrayList<TransactionItemDetail> convertDataToItems(JTable table) {
        ArrayList<TransactionItemDetail> itemList = new ArrayList<>();
        String[][] dataList = tableGrabber(table);
        for(String[] data : dataList) {
            itemList.add(createReportItem(data));
        }
        return itemList;
    }

    public String calculateReportAmount(String[][] dataList) {
        return addService.calculateReportAmount(dataList);
    }

    public String calculateItemAmount(String soldTotal, String discountAmount) {
        return calculate.calculateItemAmount(soldTotal,discountAmount);
    }
}


















