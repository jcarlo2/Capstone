package retail.controller.login;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.login.LogInFacade;
import retail.shared.constant.ConstantDialog;
import retail.view.login.LogIn;
import retail.view.main.MainFrame;
import retail.view.main.tab.top.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInController implements ActionListener {
    private final LogIn logIn;
    private final LogInFacade logInFacade;
    private final User user;
    private final MainFrame mainFrame;

    public LogInController(@NotNull LogIn logIn,
                                    User user,
                                    MainFrame mainFrame,
                                    LogInFacade logInFacade) {
        this.logIn = logIn;
        this.logInFacade = logInFacade;
        this.user = user;
        this.mainFrame = mainFrame;

        logIn.logInActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String id = logIn.getId();
        String password = logIn.getPassword();

        if(!logInFacade.checkIfExist(id,password)) {
            ConstantDialog.INCORRECT_ID_PASSWORD();
            return;
        }
        user.getId().setText(id);
        user.getLastName().setText(logInFacade.getLastName(id));
        disposeLogInAndCreateMainFrame();
    }

    public void disposeLogInAndCreateMainFrame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(logIn);
        mainFrame.setVisible(true);
        frame.dispose();
    }
}
