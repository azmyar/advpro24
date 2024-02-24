package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface IService<T>{
    public T create(T product);
    public T edit(T product);
    public T delete(T product);
    public T getProductById (String productId);
    public List<T> findAll();
}