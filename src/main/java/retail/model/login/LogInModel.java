package retail.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class LogInModel {
    private String employeeID;
    private String password;
    private String lastName;
    public LogInModel() {

    }

    public void setIdentity(String employeeID, String password, String lastName) {
        this.employeeID = employeeID;
        this.password = password;
        this.lastName = lastName;
    }

    public boolean checkValidID(@NotNull String employeeID) {
        if(employeeID.equals("")) return false;
        return isIdNumerical(employeeID);
    }

    private boolean isIdNumerical(@NotNull String id) {
        for(int i=0;i<id.length();i++) {
            if(!(id.charAt(i) >= '0' && id.charAt(i) <= '9')) return false;
        }
        return true;
    }
}
