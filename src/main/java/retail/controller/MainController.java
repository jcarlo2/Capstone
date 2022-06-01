package retail.controller;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import org.jetbrains.annotations.NotNull;
import retail.controller.tab.inventory.InventoryController;
import retail.controller.tab.login.LogInController;
import retail.controller.tab.northbutton.NorthButtonController;
import retail.controller.tab.transaction.TransactionController;
import retail.model.User;
import retail.view.BuildGUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainController {
    private final BuildGUI buildGUI;
    private final JButton option;
    private final JRadioButton light;
    private final JRadioButton dark;

    public  MainController(@NotNull BuildGUI buildGUI, User user) {
        this.buildGUI = buildGUI;
        this.option = buildGUI.getMainFrame().getMain().getTopBorderPanel().getUserPanel().getOption();
        this.light = buildGUI.getOptionFrame().getOption().getLight();
        this.dark = buildGUI.getOptionFrame().getOption().getDark();

        new LogInController(buildGUI.getLogIn(), buildGUI.getMainFrame(),buildGUI.getTopBorderPanel().getUserPanel(), user);
        new NorthButtonController(buildGUI.getTopBorderPanel(), buildGUI.getBottomPanel());
        new InventoryController(buildGUI.getBottomPanel(),buildGUI.getTopBorderPanel());

        // REFACTORED COMPONENT
        new TransactionController(buildGUI.getBottomPanel(),user);

        setOption();
        setUIStyle();
        hideOptionWhenMinimized();
    }

    private void setUIStyle() {
        light.addActionListener(e -> {
            if(e.getSource() == light) {
                try{
                    UIManager.setLookAndFeel(new FlatIntelliJLaf());
                    SwingUtilities.updateComponentTreeUI(buildGUI.getMainFrame());
                    SwingUtilities.updateComponentTreeUI(buildGUI.getOptionFrame());
                }catch (Exception a) {
                    a.printStackTrace();
                }
            }

        });

        dark.addActionListener(e -> {
            if(e.getSource() == dark) {
                try{
                    UIManager.setLookAndFeel(new FlatDarculaLaf());
                    SwingUtilities.updateComponentTreeUI(buildGUI.getMainFrame());
                    SwingUtilities.updateComponentTreeUI(buildGUI.getOptionFrame());
                }catch (Exception a) {
                    a.printStackTrace();
                }
            }

        });
    }

    private void hideOptionWhenMinimized() {
        buildGUI.getOptionFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                super.windowIconified(e);
                buildGUI.getOptionFrame().setVisible(false);
            }
        });
    }

    private void setOption() {
        option.addActionListener(e -> {
            if(e.getSource() == option) {
                buildGUI.getOptionFrame().setVisible(true);
                buildGUI.getOptionFrame().toFront();
            }
        });
    }
}
