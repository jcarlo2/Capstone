package retail.shared.util.tablecreator;

public class TableCreatorImpl implements CreateEmployeeTable, CreateProductTable,CreateTransactionTable {
    public TableCreatorImpl() {
        createEmployeeTableAndAdminAccount();
        createProductTable();
        createProductReport();
        createProductReportItem();
        createTransactionReport();
        createTransactionReportItem();
        createReturnedTransactionReport();
        createReturnedTransactionReportItem();
        createNullReport();
        createNullReportItem();
    }
}
