package retail.model.service.login;

import retail.model.repository.implementer.UserRepository;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public boolean checkIfExist(String id, String password) {
        return userRepository.checkIfExist(id, password);
    }

    public String getLastName(String id) {
        return userRepository.getLastName(id);
    }
}
