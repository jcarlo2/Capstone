package retail.shared.util.calculate;

import org.jetbrains.annotations.NotNull;

public interface ValidNumberChecker {
    default boolean isValidNumber(@NotNull String input) {
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
        return true;
    }
}
