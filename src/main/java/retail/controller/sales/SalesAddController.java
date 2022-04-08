package retail.controller.sales;

import org.jetbrains.annotations.NotNull;
import retail.component.jlist.CustomJListViewReport;
import retail.component.jtable.CustomJTableSalesReport;
import retail.constant.Constant;
import retail.constant.ConstantDialog;
import retail.controller.database.ProductController;
import retail.controller.database.SalesReportController;
import retail.model.SalesReportItemObject;
import retail.model.SalesReportObject;
import retail.view.main.panel.leftpanel.LeftBorderPanel;
import retail.view.main.panel.leftpanel.UserPanel;
import retail.view.main.panel.leftpanel.reportmanipulator.panel.AddPanel;
import retail.view.main.panel.rightpanel.RightCenterPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class SalesAddController {
    private final SalesReportController salesReportController = new SalesReportController();
    private final ProductController productController = new ProductController();
    private final CustomJTableSalesReport salesTable;
    private final AddPanel addPanel;
    private final UserPanel userPanel;
    private final CustomJListViewReport viewReport;

    public SalesAddController(RightCenterPanel rightCenterPanel,LeftBorderPanel leftBorderPanel) {
        salesTable = rightCenterPanel.getSalesReport().getAddSalesReport().getCenterTable();

        addPanel = leftBorderPanel.getLeftCenterPanel().getSalesReportManipulator().getReportCardPanel().getAddPanel();
        userPanel = leftBorderPanel.getUserPanel();
        viewReport = leftBorderPanel.getLeftCenterPanel().getSalesReportManipulator().getReportCardPanel().getViewPanel().getViewReport();



        clear();
        addItemToSalesTable();
        deleteRowAddPanel();
        setAutomaticAmountAddPanel();
        documentListenerOfAddPanelComponent();
        generateReportIdActionListener();
        saveReportInDatabase();
        addPanel.getReportId().setText(generateReportId()); // Generate initial report id
    }

    private void saveReportInDatabase() {
        addPanel.getSave().addActionListener(e -> {
            if(e.getSource() == addPanel.getSave()) {
                if(salesTable.getRowCount() == 0) {
                    ConstantDialog.EMPTY_REPORT_TABLE();
                    return;
                }
                if(salesReportController.ifReportExist(addPanel.getReportId().getText())) {
                    ConstantDialog.GENERATE_NEW_REPORT_ID();
                    return;
                }
                salesReportController.addReport(createSalesReport(),getAllItemReportAtSalesTable());
                viewReport.populateSalesReportList();
            }
        });
    }

    private @NotNull ArrayList<SalesReportItemObject> getAllItemReportAtSalesTable() {
        ArrayList<SalesReportItemObject> itemReport = new ArrayList<>();
        int ROW = salesTable.getRowCount();
        int COLUMN = 8;
        for(int i=0;i<ROW;i++) {
            String[] data = new String[8];
            for(int j=0;j<COLUMN;j++) {
                data[j] = (String) salesTable.getModel().getValueAt(i,j);
            }
            itemReport.add(new SalesReportItemObject(data[1],new BigDecimal(data[2]),new BigDecimal(data[3])
                    ,new BigDecimal(data[4]),new BigDecimal(data[5])
                    , new BigDecimal(data[6]),new BigDecimal(data[7])));
        }
        return itemReport;
    }

    private @NotNull SalesReportObject createSalesReport() {
        String reportId = addPanel.getReportId().getText();
        String lastName = userPanel.getEmployeeLastName().getText();
        Date date = Date.valueOf(LocalDate.now());

        return new SalesReportObject(reportId, date,lastName);
    }

    private void deleteRowAddPanel() {
        addPanel.getDelete().addActionListener(e -> {
            if(e.getSource() == addPanel.getDelete()) {
                int row = salesTable.getSelectedRow();
                if(row > -1) {
                    salesTable.getModel().removeRow(row);
                }
            }
        });
    }

    private void addItemToSalesTable() {
        addPanel.getAdd().addActionListener(e -> {
            if(isAllAddFieldValid()) {
                String[] data = getDataAddPanelField();
                salesTable.addRow(new SalesReportItemObject(data[0],new BigDecimal(data[1]),
                        new BigDecimal(data[2]),new BigDecimal(data[3]),new BigDecimal(data[4])
                        ,new BigDecimal(data[5]),new BigDecimal(data[6])));
            } else {
                ConstantDialog.INVALID_INPUT_DIALOG();
            }
        });
    }

    private String @NotNull [] getDataAddPanelField() {
        String[] data = new String[7];
        data[0] = (String) addPanel.getId().getSelectedItem();
        data[1] = addPanel.getPrice().getText();
        data[2] = addPanel.getSold().getText();
        data[3] = addPanel.getSoldTotal().getText();
        data[4] = addPanel.getDamage().getText();
        data[5] = addPanel.getDamageTotal().getText();
        data[6] = addPanel.getTotal().getText();
        return data;
    }

    private boolean isAllAddFieldValid() {
        if(addPanel.getSold().getText().equals("")) return false;
        if(addPanel.getSoldTotal().getText().equals("")) return false;
        if(addPanel.getDamageTotal().getText().equals("")) return false;
        if(addPanel.getDamage().getText().equals("")) return false;
        if(!isValidChar(addPanel.getSold().getText().toCharArray())) return false;
        if(!isValidChar(addPanel.getSoldTotal().getText().toCharArray())) return false;
        if(!isValidChar(addPanel.getDamage().getText().toCharArray())) return false;
        return isValidChar(addPanel.getDamageTotal().getText().toCharArray());
    }

    private boolean isValidChar(char @NotNull [] a) {
        for(char b : a) {
            if(!((b >= Constant.ZERO && b <= Constant.NINE) || b == '.')) {
                return false;
            }
        }
        return true;
    }

    private void documentListenerOfAddPanelComponent() {
        documentListenerOfSold();
        documentListenerOfSoldTotal();
        documentListenerOfDamagedExpired();
        documentListenerOfDamagedExpiredTotal();
    }

    private void generateReportIdActionListener() {
        addPanel.getReset().addActionListener(e -> addPanel.getReportId().setText(generateReportId()));
    }

    private String generateReportId() {
        long id;
        String formatId = "";
        boolean flag = true;
        while(flag) {
            id = (long) (Math.random() * 1000000000000L);
            formatId = String.format("%013d",id);
            if(!salesReportController.ifReportExist(formatId)) flag = false;
        }
        return formatId;
    }

    private void clear() {
        addPanel.getClear().addActionListener(e -> {
            if(e.getSource() == addPanel.getClear()) {
                addPanel.getSold().setText("0");
                addPanel.getSoldTotal().setText("0");
                addPanel.getDamageTotal().setText("0");
                addPanel.getDamage().setText("0");
                addPanel.getTotal().setText("0");
            }
        });
    }

    private void setTotalSalesAmountAddPanel() {
        try {
            String soldTotal = addPanel.getSoldTotal().getText();
            String damExpTotal = addPanel.getDamageTotal().getText();
            if(soldTotal.equals("")) soldTotal = "0";
            if(damExpTotal.equals("")) damExpTotal = "0";
            BigDecimal total = new BigDecimal(soldTotal).subtract(new BigDecimal(damExpTotal));
            addPanel.getTotal().setText(total.toString());
        }catch (NumberFormatException e) {
            ConstantDialog.INVALID_INPUT_DIALOG();
        }
    }

    private void setAutomaticAmountAddPanel() {
        setPriceAddPanel();
        setIdAddPanelItemListener();
    }

    private void setPriceAddPanel() {
        String productID = (String) addPanel.getId().getSelectedItem();
        String price = String.valueOf(productController.get(productID).getPrice());
        addPanel.getPrice().setText(price);
    }

    private void setIdAddPanelItemListener() {
        addPanel.getId().addItemListener(e -> {
            setPriceAddPanel();
            addPanel.getSold().setText("0");
            addPanel.getSoldTotal().setText("0");
            addPanel.getDamage().setText("0");
            addPanel.getDamageTotal().setText("0");
            addPanel.getTotal().setText("0");
        });
    }

    private void documentListenerOfSold() {
        addPanel.getSold().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(addPanel.getSold().isFocusOwner()) {
                    autoCalculate(addPanel.getSoldTotal(),addPanel.getSold(),true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(addPanel.getSold().isFocusOwner()) {
                    autoCalculate(addPanel.getSoldTotal(),addPanel.getSold(),true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void documentListenerOfSoldTotal() {
        addPanel.getSoldTotal().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(addPanel.getSoldTotal().isFocusOwner()) {
                    autoCalculate(addPanel.getSold(),addPanel.getSoldTotal(),false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(addPanel.getSoldTotal().isFocusOwner()) {
                    autoCalculate(addPanel.getSold(),addPanel.getSoldTotal(),false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void documentListenerOfDamagedExpired(){
        addPanel.getDamage().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(addPanel.getDamage().isFocusOwner()) {
                    autoCalculate(addPanel.getDamageTotal(),addPanel.getDamage(),true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(addPanel.getDamage().isFocusOwner()) {
                    autoCalculate(addPanel.getDamageTotal(),addPanel.getDamage(),true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void documentListenerOfDamagedExpiredTotal(){
        addPanel.getDamageTotal().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(addPanel.getDamageTotal().isFocusOwner()) {
                    autoCalculate(addPanel.getDamage(),addPanel.getDamageTotal(),false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(addPanel.getDamageTotal().isFocusOwner()) {
                    autoCalculate(addPanel.getDamage(),addPanel.getDamageTotal(),false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private @NotNull BigDecimal multiplyToGetTotal(BigDecimal price, String input, @NotNull JTextField textField)  {
        if (isValidNumber(input)) return new BigDecimal(textField.getText()).multiply(price);
        return new BigDecimal("0");
    }

    private @NotNull BigDecimal divideToGetTotal(BigDecimal price, String input, @NotNull JTextField textField)  {
        int SCALE = 4;
        if (isValidNumber(input)) return new BigDecimal(textField.getText())
                .divide(price,SCALE, RoundingMode.CEILING);
        return new BigDecimal("0");
    }

    private void autoCalculate(@NotNull JTextField textChange, JTextField textGet,boolean isMultiply) {
        try {
            String productID = (String) addPanel.getId().getSelectedItem();
            BigDecimal price = productController.get(productID).getPrice();
            if(isMultiply){
                textChange.setText(String.valueOf(multiplyToGetTotal(price,textGet.getText(),textGet)));
            }else {
                textChange.setText(String.valueOf(divideToGetTotal(price,textGet.getText(),textGet)));
            }
            setTotalSalesAmountAddPanel();
        }catch (NumberFormatException e) {
            ConstantDialog.INVALID_INPUT_DIALOG();
        }
    }

    private boolean isValidNumber(@NotNull String input) {
        if(input.equals("")) return false;
        int SOLD_LENGTH_TEXT = input.length();
        for(int i=0;i<SOLD_LENGTH_TEXT;i++) {
            if(input.charAt(i) < Constant.ZERO && input.charAt(i) > Constant.NINE) {
                addPanel.getSoldTotal().setText("");
                return false;
            }
        }
        return true;
    }
}
