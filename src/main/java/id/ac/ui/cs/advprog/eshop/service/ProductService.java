package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public Product edit(String productId, Product updatedProduct);
    public Product getProductById (String productId);
    public String delete(String productId);
    public List<Product> findAll();
}