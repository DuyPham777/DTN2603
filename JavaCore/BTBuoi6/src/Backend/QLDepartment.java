package Backend;

import Entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLDepartment implements IQLDepartment {
    private List<Department> departments = new ArrayList<>();

    public QLDepartment() {
    }

    @Override
    public void hienThiDepartment(){
        String url = "jdbc:mysql://localhost:3306/testing_system";

        String username = "root";

        String password = "123456";

        try{

            Connection connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {

                System.out.println("Ket noi DB thanh cong");

            } else {

                System.out.println("Ket noi DB khong thanh cong");

            }
            String sql = "SELECT * FROM Department;";
// đây là đối tượng hỗ trợ thực thi câu lệnh sql tĩnh và trả về kết quả
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);// resultSet chứa dữ liệu khi chạy câu sql

// lấy dữ liệu  từ ResultSet
            while (resultSet.next()) {
                int departmentId =  resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");
                Department department = new Department(departmentId, departmentName);
                departments.add(department);
            }
    } catch (SQLException e) {
            System.out.println("Ket noi DB that bai!");
            e.printStackTrace();
        }

        System.out.println("+---------------+-------------------------+");
        System.out.printf("|%15s|%25s|\n", "Ma phong ban", "Ten phong ban");
        System.out.println("+---------------+-------------------------+");
        if (departments.size() > 0) {
            for (Department department : departments) {
                System.out.printf("|%15s|%25s|\n", department.getDepartmentId(), department.getDepartmentName());
            }
        } else {
            System.out.printf("|%77s|\n", "Khong co thong tin");
        }
        System.out.println("+---------------+-------------------------+");
    }

    public static void main(String[] args) throws SQLException {
        QLDepartment qlDepartment = new QLDepartment();
        qlDepartment.hienThiDepartment();
    }

}
