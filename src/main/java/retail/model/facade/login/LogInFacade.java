package retail.model.facade.login;

import retail.model.service.login.UserService;

public class LogInFacade {
    private final UserService service = new UserService();

    public boolean checkIfExist(String id, String password) {
        return service.checkIfExist(id,password);
    }

    public String getLastName(String id) {
        return service.getLastName(id);
    }
}
