package retail.model.service;

import retail.model.repository.implementation.NullProduct;
import retail.model.repository.implementation.Product;
import retail.model.repository.implementation.TransactionReport;
import retail.model.repository.implementation.User;
import retail.model.repository.implementer.NullProductRepository;
import retail.model.repository.implementer.ProductRepository;
import retail.model.repository.implementer.TransactionRepository;
import retail.model.repository.implementer.UserRepository;

public interface Service {
    Product product = new ProductRepository();
    NullProduct nullProduct = new NullProductRepository();
    TransactionReport transaction = new TransactionRepository();
    User user = new UserRepository();
}
