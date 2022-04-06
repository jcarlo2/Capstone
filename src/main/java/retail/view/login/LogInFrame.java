package retail.view.login;

import lombok.Getter;

import javax.swing.*;

@Getter
public class LogInFrame extends JFrame implements CreateSchemaAndEmployeeTable,
                                                  CreateProductTable,
                                                  CreateSalesReportTable{
    private final LogIn logIn = new LogIn();
    public LogInFrame(String TITLE) {
        setTitle(TITLE);
        createSchemaAndAdminAccount();
        createProductTable();
        createSalesReportTable();
        createSalesReportTableItem();

        ImageIcon img = new ImageIcon("src/main/resources/images/rmlogo.png");
        setIconImage(img.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(logIn);
        pack();
        setLocationRelativeTo(null);
    }
}
