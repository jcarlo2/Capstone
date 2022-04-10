package retail.controller;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import org.jetbrains.annotations.NotNull;
import retail.controller.component.inventory.InventoryController;
import retail.controller.component.login.LogInController;
import retail.controller.component.northbutton.NorthButtonController;
import retail.controller.component.sales.SalesMainController;
import retail.view.BuildGUI;

import javax.swing.*;

public class MainController {
    private final BuildGUI buildGUI;
    private final JButton option;
    private final JRadioButton light;
    private final JRadioButton dark;

    public MainController(@NotNull BuildGUI buildGUI) {
        this.buildGUI = buildGUI;
        this.option = buildGUI.getMainFrame().getMain().getTopBorderPanel().getUserPanel().getOption();
        this.light = buildGUI.getOptionFrame().getOption().getLight();
        this.dark = buildGUI.getOptionFrame().getOption().getDark();
        new LogInController(buildGUI.getLogIn(), buildGUI.getMainFrame(),buildGUI.getMainFrame().getMain().getTopBorderPanel().getUserPanel());

        new NorthButtonController(buildGUI.getTopBorderPanel(), buildGUI.getBottomBorderPanel());

        new InventoryController(buildGUI.getBottomBorderPanel());

        new SalesMainController(buildGUI.getTopBorderPanel(),buildGUI.getBottomBorderPanel());

        setOption();
        setUIStyle();
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

    private void setOption() {
        option.addActionListener(e -> {
            if(e.getSource() == option) {
                buildGUI.getOptionFrame().setVisible(true);
            }
        });
    }
}
