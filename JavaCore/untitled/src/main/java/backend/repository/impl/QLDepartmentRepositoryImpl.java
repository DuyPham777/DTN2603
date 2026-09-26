package backend.repository.impl;

import Utils.JDBCUtils;
import backend.repository.IQLDepartmentRepository;
import entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLDepartmentRepositoryImpl implements IQLDepartmentRepository {
    @Override
    public List<Department> getDepartments() {
        List<Department> list = new ArrayList<>();
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM department;";
            // Statement đây là đối tượng hỗ trợ thực thi câu lệnh sql tĩnh và trả về kêt quá
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);// resultSet chứa dữ liệu khi chạy cau sql
            // lấy dữ liệu  từ ResultSet
            while (resultSet.next()) {
                int departmentId = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");
                Department department = new Department(departmentId,departmentName);
                list.add(department);
            }
            JDBCUtils.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean suaDepartmentNameTheoId(Integer departmentId, String departmentName) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE department SET department_name = ? WHERE department_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, departmentName);
            preparedStatement.setInt(2, departmentId);
            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            if (c > 0) {
                System.out.println("Cập nhật thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean xoaDepartmentTheoId(Integer departmentId) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM department WHERE department_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, departmentId);
            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            if (c > 0) {
                System.out.println("Xóa thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean themDepartment(String departmentName) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO department(department_name) VALUES(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, departmentName);
            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            if (c > 0) {
                System.out.println("Thêm phòng ban thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isDepartmentExist(int id) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM department WHERE department_id = ?";
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
