package retail.view;

import lombok.Getter;
import retail.constant.Constant;
import retail.view.login.LogIn;
import retail.view.login.LogInFrame;
import retail.view.main.MainFrame;
import retail.view.main.panel.leftpanel.LeftBorderPanel;
import retail.view.main.panel.rightpanel.RightBorderPanel;

@Getter
public class BuildGUI {
    private final LogInFrame logInFrame;
    private final MainFrame mainFrame;

    public BuildGUI() {
        logInFrame = new LogInFrame(Constant.RETAIL_MANAGEMENT);
        mainFrame = new MainFrame(Constant.RETAIL_MANAGEMENT);
    }

    public RightBorderPanel getRightBorderPanel() {
        return mainFrame.getMain().getRightBorderPanel();
    }

    public LeftBorderPanel getLeftBorderPanel() {
        return  mainFrame.getMain().getLeftBorderPanel();
    }

    public LogIn getLogIn() {
        return  logInFrame.getLogIn();
    }
}
