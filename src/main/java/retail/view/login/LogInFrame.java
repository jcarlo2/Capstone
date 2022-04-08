package retail.view.login;

import lombok.Getter;

import javax.swing.*;

@Getter
public class LogInFrame extends JFrame implements CreateEmployeeTable,
                                                  CreateProductTable,
                                                  CreateSalesReportTable {
    private final LogIn logIn = new LogIn();
    public LogInFrame(String TITLE) {
        setTitle(TITLE);
        createEmployeeTableAndAdminAccount();
        createProductTable();
        createSalesReportTable();
        createSalesReportTableItem();

        ImageIcon img = new ImageIcon("src/main/resources/images/rmlogo.png");
        setIconImage(img.getImage());
        add(logIn);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
