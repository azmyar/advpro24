package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    ProductController ProductController;

    @Mock
    private ProductService <Product> productService;

    @Mock
    private Model model;

    @Test
    void testCreateProductPage() {
        String result = ProductController.createProductPage(model);
        assertEquals("createProduct", result);
    }

    @Test
    void createProductPost() {
        Product product = new Product();
        String result = ProductController.createProductPost(product);
        assertEquals("redirect:list", result);
    }

    @Test
    void testProductListPage() {
        String result = ProductController.productListPage(model);
        assertEquals("productList", result);
    }

    @Test
    void testEditProductPage() {
        String result = ProductController.editProductPage("some-product-id", model);
        assertEquals("editProduct", result);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        String result = ProductController.editProductPost(product);
        assertEquals("redirect:list", result);
    }

    @Test
    void testDeleteProduct() {
        String result = ProductController.deleteProductPost("some-product-id");
        assertEquals("redirect:list", result);
    }
}