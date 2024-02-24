package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface IRepository<T>{
    public T create(T product);
    public T edit(T product);
    public T delete(T product);
    public T getProductById (String productId);
    public Iterator<T> findAll();
}