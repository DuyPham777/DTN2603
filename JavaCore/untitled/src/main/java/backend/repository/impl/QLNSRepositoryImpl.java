package backend.repository.impl;

import Utils.JDBCUtils;
import backend.repository.IQLNSRepository;
import entity.Account;
import entity.Department;
import entity.Position;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QLNSRepositoryImpl implements IQLNSRepository {


    @Override
    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM `account`";

        try (Connection connection = JDBCUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                String email = resultSet.getString("email");
                String userName = resultSet.getString("username");
                String fullName = resultSet.getString("fullname");
                LocalDate createDate = resultSet.getObject("create_date", LocalDate.class);
                int departmentId = resultSet.getInt("department_id");
                int positionId = resultSet.getInt("position_id");
                Department department = new Department();
                department.setDepartmentId(departmentId);
                Position position = new Position();
                position.setPositionId(positionId);

                accounts.add(new Account(accountId, email, userName, fullName, department, position, createDate));
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public boolean xoaTheoAccId(int accountId) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM `account` WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Xoa phong ban that bai!");
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean suaTheoAccId(int accountId, String userName, int departmentId, int positionId) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE `account` SET username = ?, department_id = ?, position_id = ? WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, departmentId);
            preparedStatement.setInt(3, positionId);
            preparedStatement.setInt(4, accountId );

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Cap nhat thanh cong!");
            } else {
                System.out.println("Cap nhat khong thanh cong!");
            }
        } catch (SQLException e) {
            System.out.println("Cap nhat that bai!");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean themAccount(String email, String userName, String fullName, int departmentId, int positionId) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO `account` (email, username, fullname, department_id, position_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, fullName);
            preparedStatement.setInt(4, departmentId);
            preparedStatement.setInt(5, positionId);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Cap nhat thanh cong!");
            } else {
                System.out.println("Cap nhat khong thanh cong!");
            }
        } catch (SQLException e) {
            System.out.println("Cap nhat that bai!");
            e.printStackTrace();
        }
        return false;
    }
}
