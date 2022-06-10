package retail.model.facade;

import lombok.Getter;
import retail.model.facade.login.LogInFacade;
import retail.model.facade.transaction.TransactionFacade;

@Getter
public class MainFacade {
    private final LogInFacade logInFacade = new LogInFacade();
    private final TransactionFacade transactionFacade = new TransactionFacade();
}
