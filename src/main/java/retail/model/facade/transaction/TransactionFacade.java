package retail.model.facade.transaction;

import lombok.Getter;
import retail.model.facade.transaction.add.AddTransactionFacade;
import retail.model.facade.transaction.add.ReturnTransactionFacade;
import retail.model.facade.transaction.view.ViewTransactionFacade;

@Getter
public class TransactionFacade {
    private final AddTransactionFacade addTransactionFacade = new AddTransactionFacade();
    private final ReturnTransactionFacade returnTransactionFacade = new ReturnTransactionFacade();
    private final ViewTransactionFacade viewTransactionFacade = new ViewTransactionFacade();
}
