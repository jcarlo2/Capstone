package retail.shared.util;

public class TableCreatorImpl implements CreateEmployeeTable, CreateProductTable,CreateTransactionTable {
    public TableCreatorImpl() {
        createEmployeeTableAndAdminAccount();
        createProductTable();
        createProductReport();
        createProductReportItem();
        createSalesReportTable();
        createSalesReportTableItem();
    }
}
