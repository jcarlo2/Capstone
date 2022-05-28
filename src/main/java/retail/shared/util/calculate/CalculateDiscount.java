package retail.shared.util.calculate;

import org.jetbrains.annotations.NotNull;
import retail.shared.customcomponent.jtextfield.CustomJTextField;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface CalculateDiscount extends ValidNumberChecker{

    default void setDiscountAmount(@NotNull CustomJTextField discountTotal, @NotNull CustomJTextField discountPercentage, CustomJTextField soldTotal) {
        final BigDecimal discount = isValidNumber(discountPercentage.getText()) ? calculateDiscountPercentage(soldTotal, discountPercentage) : BigDecimal.ZERO;
        discountTotal.setText(discount.toString());
    }

    default @NotNull BigDecimal calculateDiscountPercentage(@NotNull CustomJTextField soldTotal, @NotNull CustomJTextField discountPercentage ) {
        if(soldTotal.getText().equals("")) return BigDecimal.ZERO;
        final BigDecimal percentage = new BigDecimal(discountPercentage.getText());
        BigDecimal ONE_HUNDRED = new BigDecimal("100");
        BigDecimal total;
        try {
            total = percentage.divide(ONE_HUNDRED,4, RoundingMode.HALF_EVEN);
        }
        catch (ArithmeticException e) {
            return BigDecimal.ZERO;
        }
        return total.multiply(new BigDecimal(soldTotal.getText())).setScale(2,RoundingMode.HALF_EVEN);
    }

    default @NotNull BigDecimal calculateDiscountAmount(@NotNull CustomJTextField discountTotal, @NotNull CustomJTextField soldTotal) {
        final BigDecimal amount = new BigDecimal(discountTotal.getText());
        BigDecimal ONE_HUNDRED = new BigDecimal("100");
        BigDecimal total;
        try {
            total = amount.divide(new BigDecimal(soldTotal.getText()),4,RoundingMode.HALF_EVEN );
        }catch (ArithmeticException e) {
            return BigDecimal.ZERO;
        }
        return total.multiply(ONE_HUNDRED).setScale(2,RoundingMode.HALF_EVEN);
    }
}