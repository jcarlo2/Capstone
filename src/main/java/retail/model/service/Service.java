package retail.model.service;

import retail.model.repository.implementation.*;
import retail.model.repository.implementer.*;

public interface Service {
    Product product = new ProductRepository();
    NullProduct nullProduct = new NullProductRepository();
    Delivery delivery = new DeliveryRepository();
    TransactionReport transaction = new TransactionRepository();
    User user = new UserRepository();
}
