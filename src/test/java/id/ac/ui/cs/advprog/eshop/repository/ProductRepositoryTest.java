package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// Tidak perlu Mockito lagi untuk In-Memory Repository
// import org.mockito.InjectMocks;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

// @ExtendWith(MockitoExtension.class) // Hapus ini
class ProductRepositoryTest { // Hapus 'public' sesuai standar JUnit 5

    // Gunakan Interface sebagai tipe, tetapi instansiasi dengan Impl-nya
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        // Instansiasi manual karena ini In-Memory
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateProductWithoutId() {
        Product product = new Product();
        product.setProductName("Sampo Tanpa ID");
        product.setProductQuantity(10);

        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertNotNull(savedProduct.getProductId());
        assertEquals("Sampo Tanpa ID", savedProduct.getProductName());
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundProduct);
        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
    }

    @Test
    void testFindProductByIdIfNotFound() {
        Product foundProduct = productRepository.findById("non-existent-id");
        assertNull(foundProduct);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Sampo Cap Bambang Baru");
        updatedProduct.setProductQuantity(200);

        // PERHATIKAN: Ini sesuai dengan parameter update kita yang baru yaitu (ID, Entity)
        Product result = productRepository.update(updatedProduct.getProductId(), updatedProduct);

        assertNotNull(result);
        assertEquals("Sampo Cap Bambang Baru", result.getProductName());
        assertEquals(200, result.getProductQuantity());

        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals("Sampo Cap Bambang Baru", foundProduct.getProductName());
        assertEquals(200, foundProduct.getProductQuantity());
    }

    @Test
    void testUpdateProductIfNotFound() {
        Product product = new Product();
        product.setProductId("non-existent-id");
        product.setProductName("Sampo Gaib");
        product.setProductQuantity(0);

        // Menyesuaikan parameter
        Product result = productRepository.update(product.getProductId(), product);
        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProductIfNotFound() {
        productRepository.delete("non-existent-id");

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
}