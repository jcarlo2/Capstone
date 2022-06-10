package retail.model.repository.implementation;

public interface User {
    boolean checkIfExist(String id, String password);
    String getLastName(String id);
}
