package retail.constant;

public interface ConstantString {

    // DATABASE CONNECTION
    String URL = "jdbc:mysql://localhost:3306/retail_management";
    String USER = "root";
    String PASS = "09212440633a";
    // ******
    String RETAIL_MANAGEMENT = "Retail Management";
    String INCORRECT_ID_PASSWORD = "Error! Incorrect ID or Password";
    // LOGIN
    String EMPLOYEE_ID = "Employee ID";
    String PASSWORD = "Password";
    String LOGIN = "Log In";
    // INVENTORY
    String CHECK_EMPTY_FIELD = "Check Empty Field!!";
    String ID_DOES_NOT_EXIST = "Id does not exist! Check id ...";
    String INVALID_INPUT = "Invalid Input!!";
    String DUPLICATE_ID = "Duplicate product id";
}
