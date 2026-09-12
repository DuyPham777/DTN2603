package Backend;

import Entity.Account;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLAccount implements IQLAccount{
    private Scanner scanner = new Scanner(System.in);

    public QLAccount() {
    }

    @Override
    public void hienThiAccount() {
        List<Account> accounts = new ArrayList<>(); // local, luôn "sạch" mỗi lần gọi
        String sql = "SELECT * FROM `account`";

        try (Connection connection = DatabaseConnection.getConnection();
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
                accounts.add(new Account(accountId, email, userName, fullName, departmentId, positionId, createDate));
            }

        } catch (SQLException e) {
            System.out.println("Ket noi DB that bai!");
            e.printStackTrace();
            return;
        }

        System.out.println("+---------------+-------------------------+---------------+-------------------------+---------------+---------------+---------------+");
        System.out.printf("|%15s|%25s|%15s|%25s|%15s|%15s|%15s|\n", "Ma dinh danh", "Email", "Ten", "Ten day du", "Ma phong ban", "Ma chuc vu", "Ngay tao");
        System.out.println("+---------------+-------------------------+---------------+-------------------------+---------------+---------------+---------------+");
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                System.out.printf("|%15d|%25s|%15s|%25s|%15d|%15d|%15s|\n",
                        account.getAccountId(), account.getEmail(), account.getUsername(),
                        account.getFullName(), account.getDepartmentId(), account.getPositionId(),
                        account.getCreateDate());
            }
        } else {
            System.out.printf("|%77s|\n", "Khong co thong tin");
        }
        System.out.println("+---------------+-------------------------+---------------+-------------------------+---------------+---------------+---------------+");
    }

    @Override
    public void themAccount() {
        System.out.println("Nhap email: ");
        String email = scanner.nextLine();
        System.out.println("Nhap username: ");
        String userName = scanner.nextLine();
        System.out.println("Nhap ho ten: ");
        String fullName = scanner.nextLine();
        System.out.println("Nhap ma phong ban: ");
        int departmentId = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Nhap ma chuc vu: ");
        int positionId = Integer.parseInt(scanner.nextLine().trim());

        String sql = "INSERT INTO `account` (email, username, fullname, department_id, position_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, fullName);
            preparedStatement.setInt(4, departmentId);
            preparedStatement.setInt(5, positionId);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Them tai khoan thanh cong!");
            } else {
                System.out.println("Them tai khoan khong thanh cong!");
            }
        } catch (SQLException e) {
            System.out.println("Them phong ban that bai.");
            e.printStackTrace();
        }
    }

    @Override
    public void xoaAccountTheoId() {
        System.out.println("Nhap ma tai lieu can xoa: ");
        int accountId = Integer.parseInt(scanner.nextLine().trim());

        String sql = "DELETE FROM `account` WHERE account_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, accountId);
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Xoa tai khoan thanh cong!");
            } else {
                System.out.println("Xoa tai khoan khong thanh cong!");
            }
        } catch (SQLException e) {
            System.out.println("Xoa phong ban that bai!");
            e.printStackTrace();
        }
    }

    @Override
    public void suaAccountTheoId() {
        System.out.println("Nhap ma tai lieu can sua: ");
        int accountId = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Nhap username moi: ");
        String userName = scanner.nextLine();
        System.out.println("Nhap ma phong ban moi: ");
        int departmentId = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Nhap ma chuc vu moi: ");
        int positionId = Integer.parseInt(scanner.nextLine().trim());
        String sql = "UPDATE `account` SET username = ?, department_id = ?, position_id = ? WHERE account_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

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
    }

    public static void main(String[] args) throws SQLException {
        QLAccount qlAccount = new QLAccount();
        qlAccount.hienThiAccount();
        qlAccount.themAccount();
        qlAccount.xoaAccountTheoId();
        qlAccount.suaAccountTheoId();
    }
}
