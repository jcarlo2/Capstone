package retail.model.repository.implementer;

import retail.model.repository.implementation.Product;


public class ProductRepository implements Product {

    public String findPriceById(String productId) {
        return findProductById(productId).getPrice();
    }
}
