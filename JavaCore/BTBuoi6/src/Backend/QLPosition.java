package Backend;

import Entity.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLPosition implements IQLPosition{
    private List<Position> positions = new ArrayList<>();



    @Override
    public void hienThiPosition() {
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
            String sql = "SELECT * FROM Position;";
// đây là đối tượng hỗ trợ thực thi câu lệnh sql tĩnh và trả về kết quả
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);// resultSet chứa dữ liệu khi chạy câu sql

// lấy dữ liệu  từ ResultSet
            while (resultSet.next()) {
                int positionId =  resultSet.getInt("position_id");
                String positionName = resultSet.getString("position_name");
                Position position = new Position(positionId, positionName);
                positions.add(position);
            }
        } catch (SQLException e) {
            System.out.println("Kết nối DB thất bại!");
            e.printStackTrace();
        }

        System.out.println("+---------------+-------------------------+");
        System.out.printf("|%15s|%25s|\n", "Ma chuc vu", "Ten chuc vu");
        System.out.println("+---------------+-------------------------+");
        if (positions.size() > 0) {
            for (Position position : positions) {
                System.out.printf("|%15s|%25s|\n", position.getPositionId(), position.getPositionName());
            }
        } else {
            System.out.printf("|%77s|\n", "Khong co thong tin");
        }
        System.out.println("+---------------+-------------------------+");
    }

    public static void main(String[] args) throws SQLException {
        QLPosition qlDepartment = new QLPosition();
        qlDepartment.hienThiPosition();
    }

}
