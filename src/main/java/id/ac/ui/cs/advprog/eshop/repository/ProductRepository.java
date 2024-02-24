package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository implements IRepository<Product>{
    private final List<Product> productData = new ArrayList<>();
    public Product create(Product product) {

        if (product.getProductQuantity() < 0){
            throw new IllegalArgumentException("Invalid Product Quantity");
        }

        if (product.getProductId() == null) product.setProductId(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    public Product edit(Product product) {

        if (product.getProductQuantity() < 0){
            throw new IllegalArgumentException("Invalid Product Quantity");
        }

        Product existingProduct = getProductById(product.getProductId());

        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductQuantity(product.getProductQuantity());

        return existingProduct;
    }

    public Product delete(Product product) {

        String productId = product.getProductId();

        for (Product item : productData) {
            if (item.getProductId().equals(productId)) {
                productData.remove(product);
                return product;
            }
        }

        throw new ArrayIndexOutOfBoundsException("Product Doesn't Exist");
    }

    public Product getProductById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }
}