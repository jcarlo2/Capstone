package retail.controller.login;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.login.LogInFacade;
import retail.shared.constant.ConstantDialog;
import retail.view.login.LogIn;
import retail.view.main.MainFrame;
import retail.view.main.tab.top.UserPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInController implements ActionListener {
    private final LogIn logIn;
    private final LogInFacade logInFacade;
    private final UserPanel userPanel;
    private final MainFrame mainFrame;

    public LogInController(@NotNull LogIn logIn,
                                    UserPanel userPanel,
                                    MainFrame mainFrame,
                                    LogInFacade logInFacade) {
        this.logIn = logIn;
        this.logInFacade = logInFacade;
        this.userPanel = userPanel;
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
        userPanel.setId(id);
        userPanel.setLastName(logInFacade.getLastName(id));
        disposeLogInAndCreateMainFrame();
    }

    public void disposeLogInAndCreateMainFrame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(logIn);
        mainFrame.setVisible(true);
        frame.dispose();
    }
}
