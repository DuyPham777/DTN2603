package Backend;

import Entity.Department;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLDepartment implements IQLDepartment {

    Scanner scanner = new Scanner(System.in);

    public QLDepartment() {
    }

    @Override
    public void hienThiDepartment(){
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM Department;";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int departmentId = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");
                departments.add(new Department(departmentId, departmentName));
            }
        } catch (SQLException e) {
            System.out.println("Ket noi DB that bai!");
            e.printStackTrace();
            return;
        }

        System.out.println("+---------------+-------------------------+");
        System.out.printf("|%15s|%25s|\n", "Ma phong ban", "Ten phong ban");
        System.out.println("+---------------+-------------------------+");
        if (!departments.isEmpty()) {
            for (Department department : departments) {
                System.out.printf("|%15d|%25s|\n", department.getDepartmentId(), department.getDepartmentName());
            }
        } else {
            System.out.printf("|%40s|\n", "Khong co thong tin");
        }
        System.out.println("+---------------+-------------------------+");
    }

    @Override
    public void themDepartment() {
        System.out.println("Nhap ten phong ban: ");
        String departmentName = scanner.nextLine();

        String sql = "INSERT INTO `department` (department_name) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, departmentName);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Them phong ban thanh cong!");
            } else {
                System.out.println("Them phong ban khong thanh cong!");
            }
        } catch (SQLException e) {
            System.out.println("Them phong ban that bai.");
            e.printStackTrace();
        }
    }

    @Override
    public void xoaDepartmentTheoId() {
        System.out.println("Nhap ma phong ban can xoa: ");
        int departmentId = Integer.parseInt(scanner.nextLine().trim());

        String sql = "DELETE FROM `department` WHERE department_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, departmentId);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Xoa phong ban thanh cong!");
            } else {
                System.out.println("Xoa phong ban khong thanh cong!");
            }

        } catch (SQLException e) {
            System.out.println("Xoa phong ban that bai!");
            e.printStackTrace();
        }
    }

    @Override
    public void suaDepartmentTheoId() {
        System.out.println("Nhap ma phong ban can sua: ");
        int departmentId = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Nhap ten phong ban moi: ");
        String departmentName = scanner.nextLine();

        String sql = "UPDATE `department` SET department_name = ? WHERE department_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, departmentName);
            preparedStatement.setInt(2, departmentId);

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
    }

    public static void main(String[] args) throws SQLException {
        QLDepartment qlDepartment = new QLDepartment();
        qlDepartment.hienThiDepartment();
        qlDepartment.themDepartment();
        qlDepartment.xoaDepartmentTheoId();
        qlDepartment.suaDepartmentTheoId();
    }

}
