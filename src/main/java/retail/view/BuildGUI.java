package retail.view;

import lombok.Getter;
import retail.shared.constant.Constant;
import retail.view.option.OptionFrame;
import retail.view.login.LogIn;
import retail.view.login.LogInFrame;
import retail.view.main.MainFrame;
import retail.view.main.tab.bot.BottomPanel;
import retail.view.main.tab.top.TopPanel;

@Getter
public class BuildGUI {
    private final LogInFrame logInFrame;
    private final MainFrame mainFrame;
    private final OptionFrame optionFrame;

    public BuildGUI() {
        logInFrame = new LogInFrame(Constant.SALES_AND_INVENTORY_SYSTEM);
        mainFrame = new MainFrame(Constant.SALES_AND_INVENTORY_SYSTEM);
        optionFrame = new OptionFrame(Constant.SALES_AND_INVENTORY_SYSTEM);
    }

    public TopPanel getTopBorderPanel() {
        return mainFrame.getMain().getTopPanel();
    }

    public BottomPanel getBottomPanel() {
        return mainFrame.getMain().getBottomPanel();
    }

    public LogIn getLogIn() {
        return  logInFrame.getLogIn();
    }
}
