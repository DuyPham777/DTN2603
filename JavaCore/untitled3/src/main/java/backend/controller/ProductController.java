package backend.controller;

import backend.service.IProductService;
import backend.service.impl.ProductService;
import entity.Product;

import java.util.List;

public class ProductController {
    private final IProductService service;

    public ProductController() {
        service = new ProductService();
    }

    public List<Product> getProducts() {
        return service.getProducts();
    }

    public boolean isProductIdExist(int productId) {
        return service.isProductIdExist(productId);
    }

    public boolean isProductNameExist(String productName) {
        return service.isProductNameExist(productName);
    }

    public boolean xoaProductTheoId(int productId) {
        return service.xoaProductTheoId(productId);
    }

    public boolean suaProductNameTheoId(int productId, String productName) {
        return service.suaProductNameTheoId(productId, productName);
    }

    public boolean themProduct(Product product) {
        return service.themProduct(product);
    }
}
