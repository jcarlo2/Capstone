package retail;

import com.formdev.flatlaf.FlatDarculaLaf;
import retail.controller.MainController;
import retail.model.MainModel;
import retail.view.BuildGUI;

import javax.swing.*;
import java.awt.*;

public class RetailApplication {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            EventQueue.invokeLater(() -> {
                BuildGUI buildGUI = new BuildGUI();
                MainModel mainModel = new MainModel();
                new MainController(mainModel,buildGUI);
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
