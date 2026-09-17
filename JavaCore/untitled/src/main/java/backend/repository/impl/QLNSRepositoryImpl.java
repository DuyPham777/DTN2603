package backend.repository.impl;

import Utils.JDBCUtils;
import backend.repository.IQLNSRepository;
import com.google.protobuf.StringValue;
import entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QLNSRepositoryImpl implements IQLNSRepository {


    @Override
    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql =  "select a.*, d.department_name, p.position_name from `account` a\n" +
                      "left join department d on a.department_id = d.department_id\n" +
                      "left join position p on a.position_id = p.position_id\n";

        try {
            Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                String email = resultSet.getString("email");
                String userName = resultSet.getString("username");
                String fullName = resultSet.getString("fullname");
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                LocalDate createDate = resultSet.getObject("create_date", LocalDate.class);
                int departmentId = resultSet.getInt("department_id");
                int positionId = resultSet.getInt("position_id");
                String positionNameStr = resultSet.getString("position_name");
                PositionName positionName = PositionName.valueOf(positionNameStr.trim().replace(" ", "_").toUpperCase());
                String departmentName = resultSet.getString("department_name");
                Department department = new Department();
                department.setDepartmentId(departmentId);
                department.setDepartmentName(departmentName);
                Position position = new Position();
                position.setPositionId(positionId);
                position.setPositionName(positionName);
                accounts.add(new Account(accountId, email, userName, fullName, gender, department, position, createDate));
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM `department`";

        try {
            Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public List<Position> getPositions() {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT * FROM `position`";

        try {
            Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Position position = new Position();
                position.setPositionId(resultSet.getInt("position_id"));
                String positionNameStr = resultSet.getString("position_name");
                PositionName positionName = PositionName.valueOf(positionNameStr.trim().replace(" ", "_").toUpperCase());
                position.setPositionName(positionName);
                positions.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positions;
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
    public boolean themAccount(Account account) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO `account` (email, username, fullname, gender, department_id, position_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getUserName());
            preparedStatement.setString(3, account.getFullName());
            preparedStatement.setString(4, account.getGender().name());
            preparedStatement.setInt(5, account.getDepartment().getDepartmentId());
            preparedStatement.setInt(6, account.getPosition().getPositionId());

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Them thanh cong!");
            } else {
                System.out.println("Them khong thanh cong!");
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("Them that bai!");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkEmailExist(String email) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM `account` WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;// tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // cho dùng thực thi try hay catch()   thì  luôn luôn chạy finally
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean checkUserNameExist(String userName) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM `account` WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;// tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // cho dùng thực thi try hay catch()   thì  luôn luôn chạy finally
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean checkAccountIdExist(int accountId) {
        Connection connection = null;
        try{
            connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM `account` WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,accountId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

}
