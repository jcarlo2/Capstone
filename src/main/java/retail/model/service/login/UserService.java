package retail.model.service.login;

import retail.model.service.Service;

public class UserService implements Service {

    public boolean checkIfExist(String id, String password) {
        return user.checkIfExist(id, password);
    }

    public String getLastName(String id) {
        return user.getLastName(id);
    }
}
