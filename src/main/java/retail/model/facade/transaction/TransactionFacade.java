package retail.model.facade.transaction;

import lombok.Getter;
import retail.model.facade.transaction.add.AddTransactionFacade;
import retail.model.facade.transaction.add.ReturnTransactionFacade;

@Getter
public class TransactionFacade {
    private final AddTransactionFacade addTransactionFacade = new AddTransactionFacade();
    private final ReturnTransactionFacade returnTransactionFacade = new ReturnTransactionFacade();
}
