package retail.shared.util.calculate;

import org.jetbrains.annotations.NotNull;
import retail.controller.database.ProductDatabase;
import retail.shared.customcomponent.jcombobox.JComboBoxProduct;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public interface CalculateSold extends ValidNumberChecker{
    default @NotNull BigDecimal multiplyToGetTotal(BigDecimal price, String input)  {
        if (isValidNumber(input)) return new BigDecimal(input).multiply(price);
        return new BigDecimal("0");
    }

    default @NotNull BigDecimal divideToGetTotal(BigDecimal price, String input)  {
        int SCALE = 4;
        if (isValidNumber(input)) return new BigDecimal(input).divide(price,SCALE, RoundingMode.CEILING);
        return new BigDecimal("0");
    }

    default void autoCalculate(@NotNull JTextField textChange,
                              JTextField textGet,
                              boolean isMultiply,
                              @NotNull ProductDatabase productDatabase,
                              @NotNull JComboBoxProduct id) {
        String productID = (String)id.getSelectedItem();
        BigDecimal price = productDatabase.get(productID).getPrice();
        String str = isMultiply ? String.valueOf(multiplyToGetTotal(price,textGet.getText()))
                                : String.valueOf(divideToGetTotal(price,textGet.getText()));
        textChange.setText(str);
    }
}
