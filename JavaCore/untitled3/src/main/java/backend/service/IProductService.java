package backend.service;

import entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    boolean isProductIdExist(int productId);

    boolean suaProductNameTheoId(int productId, String productName);

    boolean themProduct(Product product);

    boolean xoaProductTheoId(int productId);


    boolean isProductNameExist(String name);
}
