package backend.repository.impl;

import backend.repository.IProductRepository;
import entity.Manufacturer;
import entity.ManufacturerName;
import entity.Product;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT p.ProductId, p.ProductName, p.ProductPrice, p.ProductDetail, "
                + "       p.RatingStar, m.ManufacturerId, m.ManufacturerName "
                + "FROM Product p "
                + "JOIN Manufacturer m ON p.ManufacturerId = m.ManufacturerId "
                + "ORDER BY p.ProductId";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductPrice(rs.getString("ProductPrice"));
                product.setProductDetail(rs.getString("ProductDetail"));

                int star = rs.getInt("RatingStar");
                product.setRatingStar(rs.wasNull() ? null : star);

                ManufacturerName manufacturerName =
                        ManufacturerName.valueOf(rs.getString("ManufacturerName"));
                Manufacturer manufacturer =
                        new Manufacturer(rs.getInt("ManufacturerId"), manufacturerName);
                product.setManufacturer(manufacturer);

                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Loi getProducts: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean isProductIdExist(int productId) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sqlExistsById = "SELECT COUNT(*) FROM Product WHERE ProductId = ?";
            PreparedStatement statement = connection.prepareStatement(sqlExistsById);

            statement.setInt(1, productId);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean isProductNameExist(String name) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sqlExistByName = "SELECT 1 FROM Product WHERE ProductName = ?";
            PreparedStatement ps = connection.prepareStatement(sqlExistByName);
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean themProduct(Product product) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sqlInsert = "INSERT INTO Product (ProductName, ProductPrice, ProductInfo, ProductDetail, "
                            + "RatingStar, ProductImageName, ManufacturerId, CategoryId) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlInsert);
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getProductPrice());
            statement.setString(3, product.getProductInfo());
            statement.setString(4, product.getProductDetail());
            statement.setInt(5, product.getRatingStar());
            statement.setString(6, product.getProductImageName());
            statement.setInt(7, product.getManufacturerId());
            statement.setInt(8, product.getCategory().getCategoryId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean xoaProductTheoId(int id) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sqlDeleteById =
                    "DELETE FROM Product WHERE ProductId = ?";
            PreparedStatement statement = connection.prepareStatement(sqlDeleteById);
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean suaProductNameTheoId(int id, String newName) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sqlUpdateByName = "UPDATE Product SET ProductName = ? WHERE ProductId = ?";
            PreparedStatement ps = connection.prepareStatement(sqlUpdateByName);
            ps.setString(1, newName);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }
}
