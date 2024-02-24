package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService <T>{
    public T create(T product);
    public T edit(T product);
    public T delete(T product);
    public T getProductById (String productId);
    public List<T> findAll();
}