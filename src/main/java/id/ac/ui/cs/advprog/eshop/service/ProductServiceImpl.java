package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        productRepository.create(product);
        return product;
    }

    @Override
    public Product edit(String productId, Product updatedProduct) {
        Product existingProduct = getProductById(productId);

        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setProductQuantity(updatedProduct.getProductQuantity());

        return existingProduct;
    }

     @Override
     public String delete(String productId) {

        Product product = getProductById(productId);
        productRepository.delete(product);

        return productId;
    }

    @Override
    public Product getProductById (String productId) {
        for (Product product : findAll()){
            if (product.getProductId().equals(productId)){
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }
}
