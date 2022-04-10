package retail;

import com.formdev.flatlaf.*;
import retail.controller.MainController;
import retail.view.BuildGUI;

import javax.swing.*;

public class RetailApplication {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            BuildGUI buildGUI = new BuildGUI();
            new MainController(buildGUI);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
