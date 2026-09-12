package Backend;

import Entity.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLPosition implements IQLPosition{

    Scanner scanner = new Scanner(System.in);
    private static final String[] VALID_POSITIONS = {
            "DEV", "TEST", "SCRUM_MASTER", "PM", "DEVOPS",
            "BUSINESS ANALYST", "QA LEAD", "TEAM LEAD", "INTERN", "UI/UX DESIGNER"
    };

    @Override
    public void hienThiPosition() {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT * FROM `position`";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int positionId = resultSet.getInt("position_id");
                String positionName = resultSet.getString("position_name");
                positions.add(new Position(positionId, positionName));
            }

        } catch (SQLException e) {
            System.out.println("Ket noi DB that bai!");
            e.printStackTrace();
            return;
        }

        System.out.println("+---------------+-------------------------+");
        System.out.printf("|%15s|%25s|\n", "Ma chuc vu", "Ten chuc vu");
        System.out.println("+---------------+-------------------------+");
        if (!positions.isEmpty()) {
            for (Position position : positions) {
                System.out.printf("|%15d|%25s|\n", position.getPositionId(), position.getPositionName());
            }
        } else {
            System.out.printf("|%40s|\n", "Khong co thong tin");
        }
        System.out.println("+---------------+-------------------------+");
    }

    private String chonTenChucVu() {
        System.out.println("Chon ten chuc vu:");
        for (int i = 0; i < VALID_POSITIONS.length; i++) {
            System.out.println((i + 1) + ". " + VALID_POSITIONS[i]);
        }
        int choice = Integer.parseInt(scanner.nextLine().trim());
        if (choice < 1 || choice > VALID_POSITIONS.length) {
            System.out.println("Lua chon khong hop le.");
            return "INTERN";
        }
        return VALID_POSITIONS[choice - 1];
    }

    @Override
    public void themPosition() {
        String positionName = chonTenChucVu();

        String sql = "INSERT INTO `position` (position_name) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, positionName);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Them chuc vu thanh cong!");
            } else {
                System.out.println("Them chuc vu khong thanh cong!");
            }

        } catch (SQLException e) {
            System.out.println("Them chuc vu that bai!");
            e.printStackTrace();
        }
    }

    @Override
    public void xoaPositionTheoId() {
        System.out.println("Nhập mã chức vụ cần xóa: ");
        int positionId = Integer.parseInt(scanner.nextLine().trim());
        String sql = "DELETE FROM `position` WHERE position_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, positionId);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Xoa chuc vu thanh cong!");
            } else {
                System.out.println("Xoa chuc vu khong thanh cong!");
            }
        } catch (SQLException e) {
            System.out.println("Xoa chuc vu that bai!");
            e.printStackTrace();
        }
    }

    @Override
    public void suaPositionTheoId() {
        System.out.println("Nhập mã chức vụ cần sửa: ");
        int positionId = Integer.parseInt(scanner.nextLine().trim());

        String positionName = chonTenChucVu();

        String sql = "UPDATE `position` SET position_name = ? WHERE position_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, positionName);
            preparedStatement.setInt(2, positionId);
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
        QLPosition qlDepartment = new QLPosition();
        qlDepartment.hienThiPosition();
        qlDepartment.themPosition();
        qlDepartment.xoaPositionTheoId();
        qlDepartment.suaPositionTheoId();
    }

}
