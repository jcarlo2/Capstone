package retail.model;

import lombok.Getter;
import retail.model.login.LogInModel;

@Getter
public class MainModel {
    private final LogInModel logInModel = new LogInModel();

    public MainModel() {

    }
}
