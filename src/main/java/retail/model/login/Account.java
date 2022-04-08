package retail.model.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private static String[] USERDATA = new String[2];

    public static void setData(String id,String lastName) {
        USERDATA[0] = id;
        USERDATA[1] = lastName;
    }
}
