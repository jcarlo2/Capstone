package retail.controller.tab.login;

import org.jetbrains.annotations.NotNull;
import retail.model.User;
import retail.shared.constant.ConstantDialog;
import retail.dao.UserDAO;
import retail.view.login.LogIn;
import retail.view.main.MainFrame;
import retail.view.main.tab.top.UserPanel;

import javax.swing.*;

public class LogInController {
    private final UserDAO controller = new UserDAO();
    private final LogIn logIn;
    private final MainFrame mainFrame;
    private final UserPanel userPanel;
    private final User user;
    public LogInController(LogIn logIn, MainFrame mainFrame, UserPanel userPanel, User user) {
        this.logIn = logIn;
        this.mainFrame = mainFrame;
        this.userPanel = userPanel;
        this.user = user;
        addActionListener();
    }

    public void validateLogIn() {
        String password = String.valueOf(logIn.getPassword().getPassword());
        String id = logIn.getId().getText();
        if(!isValidId(id)) {
            ConstantDialog.INVALID_INPUT();
            System.out.println("INVALID");
            return;
        }
        if(password.equals("")) {
            ConstantDialog.EMPTY_FIELD();
            System.out.println("password");
            return;
        }

        if(!controller.checkIdAndPasswordInDatabase(Long.parseLong(id),password)) {
            ConstantDialog.INCORRECT_ID_PASSWORD();
            return;
        }
        saveUser(id);
        userPanel.getEmployeeID().setText(id);
        userPanel.getEmployeeLastName().setText(user.getLastName());
        disposeLogInAndCreateMainFrame();
    }

    private void saveUser(String id) {
        user.setId(id);
        user.setLastName(controller.getLastName(id));
    }
    public boolean isValidId(@NotNull String employeeID) {
        if(employeeID.equals("")) return false;
        return isIdNumerical(employeeID);
    }

    private boolean isIdNumerical(@NotNull String id) {
        for(int i=0;i<id.length();i++) {
            if(!(id.charAt(i) >= '0' && id.charAt(i) <= '9')) return false;
        }
        return true;
    }

    public void disposeLogInAndCreateMainFrame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(logIn);
        mainFrame.setVisible(true);
        frame.dispose();
    }

    public void addActionListener() {
        logIn.getLogIn().addActionListener(e -> {
            if(e.getSource() == logIn.getLogIn()) {
                validateLogIn();
            }
        });
    }
}
