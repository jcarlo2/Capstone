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

    public String calculateDiscountAmount(String soldTotal, @NotNull String discountPercent) {
        BigDecimal percent =  new BigDecimal(discountPercent).divide(BigDecimal.valueOf(100),4,RoundingMode.HALF_EVEN);
        return percent.multiply(new BigDecimal(soldTotal)).setScale(2,RoundingMode.HALF_EVEN).toString();
    }

    public String calculateDiscountPercentage(@NotNull String soldTotal, String amount) {
        if(soldTotal.equals("0")) return "0";
        amount = new BigDecimal(amount).divide(new BigDecimal(soldTotal),4,RoundingMode.HALF_EVEN).toString();
        return new BigDecimal(amount).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_EVEN).toString();
    }

    public String calculateSoldTotal(@NotNull String price, String sold) {
        return new BigDecimal(price).multiply(new BigDecimal(sold)).setScale(2, RoundingMode.HALF_EVEN).toString();
    }

    public String divide(String a, String b) {
        BigDecimal total = new BigDecimal(a).divide(new BigDecimal(b),2,RoundingMode.HALF_EVEN);
        return total.toString();
    }

    public String multiply(String a, String b) {
        BigDecimal total = new BigDecimal(a).multiply(new BigDecimal(b)).setScale(2,RoundingMode.HALF_EVEN);
        return total.toString();
    }

    public String subtract(String a, String b) {
        BigDecimal total = new BigDecimal(a).subtract(new BigDecimal(b)).setScale(2,RoundingMode.HALF_EVEN);
        return total.toString();
    }

    public String add(String a, String b) {
        BigDecimal total = new BigDecimal(a).add(new BigDecimal(b)).setScale(2,RoundingMode.HALF_EVEN);
        return total.toString();
    }

    public boolean isWholeNumber(String a) {
        return Double.parseDouble(a)%1 == 0;
    }


    public String negation(String num) {
        return new BigDecimal(num).multiply(BigDecimal.valueOf(-1)).toString();
    }

    public String getTotal(String @NotNull[] @NotNull [] dataList, int i) {
        BigDecimal total = new BigDecimal("0");
        for(String[] data : dataList) {
            total = total.add(new BigDecimal(data[i]));
        }
        return total.setScale(2, RoundingMode.HALF_EVEN).toString();
    }
}
