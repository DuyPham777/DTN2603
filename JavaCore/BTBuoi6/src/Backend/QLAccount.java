package Backend;

import Entity.Account;
import Entity.Department;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QLAccount implements IQLAccount{
    private List<Account> accounts = new ArrayList<>();

    public QLAccount() {
    }

    @Override
    public void hienThiAccount() {
        String url = "jdbc:mysql://localhost:3306/testing_system";

        String username = "root";

        String password = "123456";

        try{

            Connection connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {

                System.out.println("Kết nối DB thành công");

            } else {

                System.out.println("Kết nối DB không thành công");

            }
            String sql = "SELECT * FROM `Account`;";
// đây là đối tượng hỗ trợ thực thi câu lệnh sql tĩnh và trả về kết quả
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);// resultSet chứa dữ liệu khi chạy câu sql

// lấy dữ liệu  từ ResultSet
            while (resultSet.next()) {
                int accountId =  resultSet.getInt("account_id");
                String email = resultSet.getString("email");
                String userName = resultSet.getString("username");
                String fullName = resultSet.getString("fullname");
                LocalDate createDate = resultSet.getObject("create_date", LocalDate.class);
                int departmentId =  resultSet.getInt("department_id");
                int positionId =  resultSet.getInt("position_id");

                Account account = new Account(accountId, email, userName, fullName, departmentId, positionId, createDate);
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Kết nối DB thất bại!");
            e.printStackTrace();
        }

        System.out.println("+---------------+-------------------------+---------------+-------------------------+---------------+---------------+---------------+");
        System.out.printf("|%15s|%25s|%15s|%25s|%15s|%15s|%15s|\n", "Ma dinh danh", "Email", "Ten", "Ten day du", "Ma phong ban", "Ma chuc vu", "Ngay tao");
        System.out.println("+---------------+-------------------------+---------------+-------------------------+---------------+---------------+---------------+");
        if (accounts.size() > 0) {
            for (Account account: accounts) {
                System.out.printf("|%15s|%25s|%15s|%25s|%15s|%15s|%15s|\n", account.getAccountId(), account.getEmail(), account.getUsername(), account.getFullName(), account.getDepartmentId(), account.getPositionId(), account.getCreateDate());
            }
        } else {
            System.out.printf("|%77s|\n", "Khong co thong tin");
        }
        System.out.println("+---------------+-------------------------+---------------+-------------------------+---------------+---------------+---------------+");
    }

    public static void main(String[] args) throws SQLException {
        QLAccount qlAccount = new QLAccount();
        qlAccount.hienThiAccount();
    }
}
