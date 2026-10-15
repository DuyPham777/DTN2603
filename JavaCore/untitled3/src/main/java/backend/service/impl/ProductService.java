package backend.service.impl;

import backend.repository.IProductRepository;
import backend.repository.impl.ProductRepository;
import backend.service.IProductService;
import entity.Product;

import java.util.List;

public class ProductService implements IProductService {
    private final IProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public boolean isProductIdExist(int productId) {
        return productRepository.isProductIdExist(productId);
    }

    @Override
    public boolean isProductNameExist(String productName) {
        return productRepository.isProductNameExist(productName);
    }

    @Override
    public boolean xoaProductTheoId(int productId) {
        return productRepository.xoaProductTheoId(productId);
    }

    @Override
    public boolean suaProductNameTheoId(int productId, String productName) {
        if (productRepository.isProductNameExist(productName)) {
            return false;
        }
        return productRepository.suaProductNameTheoId(productId, productName);
    }

    @Override
    public boolean themProduct(Product product) {
        if (productRepository.isProductNameExist(product.getProductName())) {
            return false;
        }
        return productRepository.themProduct(product);
    }
}
