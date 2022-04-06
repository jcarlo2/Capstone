package retail;

import com.formdev.flatlaf.FlatDarculaLaf;
import retail.controller.MainController;
import retail.model.MainModel;
import retail.view.BuildGUI;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class RetailApplication {
    public static void main(String[] args) {
        String date = LocalDate.now().toString();
        System.out.println(date);



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
