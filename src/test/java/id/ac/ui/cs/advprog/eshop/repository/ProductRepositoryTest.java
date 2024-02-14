package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setup() {

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
        product2.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd7");
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
    void testFindById() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product savedProduct = productRepository.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindById_IfProductNotFound() {
        productRepository.create(new Product());

        Product savedProduct = productRepository.getProductById("randomProductId");
        assertNull(savedProduct);
    }

    @Test
    void testEditProduct(){
        // Create Product
        Product productInitial = new Product();
        productInitial.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productInitial.setProductName("Sampo Cap Bambang");
        productInitial.setProductQuantity(100);
        productRepository.create(productInitial);

        // Edit Product
        Product productModified = new Product();
        productModified.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productModified.setProductName("Sampo Cap Blimbing");
        productModified.setProductQuantity(101);
        productRepository.edit(productModified);

        // Check whether Product correctly changed
        Product testProduct = productRepository.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", testProduct.getProductId());
        assertEquals("Sampo Cap Blimbing", testProduct.getProductName());
        assertEquals(101, testProduct.getProductQuantity());
    }

    @Test
    void testEditProduct_IfProductQuantityIsNegative(){
        // Create Product
        Product productInitial = new Product();
        productInitial.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productInitial.setProductName("Sampo Cap Bambang");
        productInitial.setProductQuantity(100);
        productRepository.create(productInitial);

        // Insert Negative Quantity
        Product productModified = new Product();
        productModified.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productModified.setProductName("Sampo Cap Blimbing");
        productModified.setProductQuantity(-100);
        assertThrows(IllegalArgumentException.class, () -> productRepository.edit(productModified));
    }

    @Test
    void testCreateProduct_IfProductQuantityIsNegative(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(-100);
        assertThrows(IllegalArgumentException.class, () -> productRepository.create(product));
    }

    @Test
    void testCreateProduct_IfProductIdIsNull(){
        Product product = new Product();
        product.setProductId(null);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        assertNotNull(product.getProductId());
    }

    @Test
    void testDeleteProduct(){
        // Create Product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Delete Product
        assertEquals(
                "eb558e9f-1c39-460e-8860-71af6af63bd6",
                productRepository.delete(product).getProductId()
        );

        // Create Random Product
        productRepository.create(new Product());

        // Check whether the deleted Product still exist
        Product testProduct = productRepository.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(testProduct);
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> productRepository.delete(product));
    }

    @Test
    void testDeleteProduct_IfProductNotFound(){

        // Create Random Product Without Inserting it to Repository
        Product product = new Product();

        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> productRepository.delete(product));
    }
}