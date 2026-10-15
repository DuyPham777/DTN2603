package backend.repository.impl;

import backend.repository.IManufacturerRepository;
import entity.Manufacturer;
import entity.ManufacturerName;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerRepository implements IManufacturerRepository {

    @Override
    public List<Manufacturer> getManufacturers() {
        List<Manufacturer> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT ManufacturerId, ManufacturerName FROM Manufacturer ORDER BY ManufacturerId";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    list.add(new Manufacturer(rs.getInt("ManufacturerId"), ManufacturerName.valueOf(rs.getString("ManufacturerName"))));
                }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return list;
    }

    @Override
    public Manufacturer findManufacturerById(int manufacturerId) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT ManufacturerId, ManufacturerName FROM Manufacturer WHERE ManufacturerId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, manufacturerId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    manufacturerId = rs.getInt("ManufacturerId");
                    ManufacturerName name = ManufacturerName.valueOf(rs.getString("ManufacturerName"));
                    return new Manufacturer(manufacturerId, name);
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi tim Manufacturer theo id: " + e.getMessage());
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return null;
    }
}
