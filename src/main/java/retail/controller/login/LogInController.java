package retail.controller.login;

import retail.constant.ConstantDialog;
import retail.model.login.LogInModel;
import retail.view.login.LogIn;
import retail.view.main.MainFrame;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static retail.constant.ConstantString.*;

public class LogInController {
    private final LogIn logIn;
    private final LogInModel logInModel;
    private final MainFrame mainFrame;
    public LogInController(LogIn logIn, LogInModel logInModel,MainFrame mainFrame) {
        this.logIn = logIn;
        this.logInModel = logInModel;
        this.mainFrame = mainFrame;

        addActionListener();
    }

    public void addActionListener() {
        logIn.getLogIn().addActionListener(e -> {
            if(e.getSource() == logIn.getLogIn()) {
                validateLogIn();
            }
        });
    }

    public void validateLogIn() {
        String idStr = logIn.getId().getText();
            if(!logInModel.checkValidID(idStr)) {
                ConstantDialog.INVALID_INPUT_DIALOG();
                return;
            }

        Long id = Long.parseLong(idStr);
        String password = String.valueOf(logIn.getPassword().getPassword());
            if(idStr.equals("") || password.equals("")) {
                ConstantDialog.EMPTY_FIELD_DIALOG();
                return;
            }
            if(checkIdAndPasswordInDatabase(id,password)) {
                disposeLogInAndCreateMainFrame();
            }else {
                ConstantDialog.CHECK_ID_OR_PASSWORD();
            }
    }

    public boolean checkIdAndPasswordInDatabase(Long id, String password) {
        String query = "SELECT EXISTS (SELECT last_name FROM employee WHERE id = ? and password = ?)";
        boolean flag = false;
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = resultSet.getInt(1) == 1;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void disposeLogInAndCreateMainFrame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(logIn);
        mainFrame.setVisible(true);
        frame.dispose();
    }
}
