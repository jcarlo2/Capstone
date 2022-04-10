package retail.view;

import lombok.Getter;
import retail.constant.Constant;
import retail.view.option.OptionFrame;
import retail.view.login.LogIn;
import retail.view.login.LogInFrame;
import retail.view.main.MainFrame;
import retail.view.main.panel.bot.BottomBorderPanel;
import retail.view.main.panel.top.TopBorderPanel;

@Getter
public class BuildGUI {
    private final LogInFrame logInFrame;
    private final MainFrame mainFrame;
    private final OptionFrame optionFrame;

    public BuildGUI() {
        logInFrame = new LogInFrame(Constant.RETAIL_MANAGEMENT);
        mainFrame = new MainFrame(Constant.RETAIL_MANAGEMENT);
        optionFrame = new OptionFrame(Constant.RETAIL_MANAGEMENT);
    }

    public TopBorderPanel getTopBorderPanel() {
        return mainFrame.getMain().getTopBorderPanel();
    }

    public BottomBorderPanel getBottomBorderPanel() {
        return mainFrame.getMain().getBottomBorderPanel();
    }

    public LogIn getLogIn() {
        return  logInFrame.getLogIn();
    }
}
