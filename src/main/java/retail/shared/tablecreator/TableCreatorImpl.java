package retail.shared.tablecreator;

public class TableCreatorImpl implements CreateUserTable, CreateProductTable,CreateTransactionTable {
    public TableCreatorImpl() {
        createEmployeeTableAndAdminAccount();
        createProductTable();
        createProductReport();
        createProductReportItem();
        createTransactionReport();
        createTransactionReportItem();
        createNullReport();
        createNullReportItem();
    }
}
