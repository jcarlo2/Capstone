package retail.model.service;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Calculate {

    public boolean isValidNumber(@NotNull String @NotNull ... inputList) {
        for(String input : inputList) {
            if(input.equals("")) return false;
            if(input.charAt(0) == '.') return false;
            int flag = 0;
            for(int i=0;i<input.length();i++) {
                if(flag >= 2) return false;
                if(input.charAt(i) == '.') {
                    flag++;
                    continue;
                }
                if(!Character.isDigit(input.charAt(i))) return false;
            }
        }
        return true;
    }

    public String calculateItemAmount(String soldTotal, String discountAmount) {
        return new BigDecimal(soldTotal).subtract(new BigDecimal(discountAmount)).setScale(2, RoundingMode.HALF_EVEN).toString();
    }

    public String calculateDiscountAmount(BigDecimal soldTotal, @NotNull BigDecimal discountPercent) {
        BigDecimal percent =  discountPercent.divide(BigDecimal.valueOf(100),4,RoundingMode.HALF_EVEN);
        return percent.multiply(soldTotal).setScale(2,RoundingMode.HALF_EVEN).toString();
    }

    public String calculateDiscountPercentage(BigDecimal soldTotal, BigDecimal amount) {
        amount = amount.divide(soldTotal,4,RoundingMode.HALF_EVEN);
        return amount.multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_EVEN).toString();
    }

    public String calculateSoldTotal(@NotNull BigDecimal price, BigDecimal sold) {
        return price.multiply(sold).setScale(2, RoundingMode.HALF_EVEN).toString();
    }
}
