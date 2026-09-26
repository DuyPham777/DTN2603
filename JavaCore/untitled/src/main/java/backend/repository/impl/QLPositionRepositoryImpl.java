package backend.repository.impl;

import Utils.JDBCUtils;
import backend.repository.IQLPositionRepository;
import entity.Position;
import entity.PositionName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLPositionRepositoryImpl implements IQLPositionRepository {
    @Override
    public List<Position> getPositions() {
        List<Position> list = new ArrayList<>();
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM `position`;";
            // Statement đây là đối tượng hỗ trợ thực thi câu lệnh sql tĩnh và trả về kêt quá
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);// resultSet chứa dữ liệu khi chạy cau sql
            // lấy dữ liệu  từ ResultSet
            while (resultSet.next()) {
                int positionId = resultSet.getInt("position_id");
                PositionName positionName = PositionName.valueOf(resultSet.getString("position_name"));
                Position position = new Position(positionId,positionName);
                // them vao ds TaiLieu để hiển thị ra
                list.add(position);
            }
            JDBCUtils.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean suaPositionNameTheoId(Integer positionId, PositionName positionName) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE `position` SET position_name = ? WHERE position_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, positionName.name());
            preparedStatement.setInt(2, positionId);
            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            if (c > 0) {
                System.out.println("Cập nhật Position thành công!");
            } else {
                System.out.println("Không tìm thấy Position!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean xoaPositionTheoId(Integer positionId) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM `position` WHERE position_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, positionId);
            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            if (c > 0) {
                System.out.println("Xóa Position thành công!");
            } else {
                System.out.println("Không tìm thấy Position!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean themPosition(PositionName positionName) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO `position`(position_name) VALUES(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, positionName.name());
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Thêm chức vụ thành công!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isPositionExist(int id) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM `position` WHERE position_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                JDBCUtils.closeConnection(connection);
                return count > 0;
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
