package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends AbstractInMemoryRepository<Product> {

    @Override
    protected String getEntityId(Product entity) {
        return entity.getProductId();
    }

    @Override
    protected void setEntityId(Product entity, String id) {
        entity.setProductId(id);
    }
}