package backend.repository;

import entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getProducts();

    boolean isProductIdExist(int productId);
    boolean isProductNameExist(String productName);
    boolean xoaProductTheoId(int productId);
    boolean themProduct(Product product);
    boolean suaProductNameTheoId(int productId, String productName);
}
