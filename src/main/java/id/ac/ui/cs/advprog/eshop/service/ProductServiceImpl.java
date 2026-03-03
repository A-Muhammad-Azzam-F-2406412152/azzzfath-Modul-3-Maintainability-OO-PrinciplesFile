package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.BaseRepository;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends AbstractBaseServiceImpl<Product> implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    protected BaseRepository<Product, String> getRepository() {
        return productRepository;
    }
}