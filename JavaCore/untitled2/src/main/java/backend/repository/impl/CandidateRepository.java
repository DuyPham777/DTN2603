package backend.repository.impl;

import backend.repository.ICandidateRepository;
import entity.*;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateRepository implements ICandidateRepository {
    @Override
    public boolean registerExperience(String firstName, String lastName, String phone, String email, int expInYear, String proSkill, String password) {

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO candidate (first_name, last_name, phone, email, `password`, " +
                    "`role`, exp_in_year, pro_skill) VALUES (?, ?, ?, ?, ?, 'EXPERIENCECANDIDATE', ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phone);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.setInt(6, expInYear);
            statement.setString(7, proSkill);

            int c = statement.executeUpdate();
            return c > 0;

        } catch (SQLException e) {
            System.out.println("Dang ky that bai!");
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean registerFresher(String firstName, String lastName, String phone, String email, GraduationRank graduationRank, String password) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO candidate (first_name, last_name, phone, email, `password`, " +
                    "`role`, graduation_rank) VALUES (?, ?, ?, ?, ?, 'FRESHERCANDIDATE', ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phone);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.setString(6, graduationRank.name());
            int c = statement.executeUpdate();
            return c > 0;

        } catch (SQLException e) {
            System.out.println("Dang ky that bai!");
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Candidate login(String email, String password) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from candidate where email = ? and `password` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            // Step 3: Execute SQL query
            ResultSet resultSet = statement.executeQuery();

            // Step 4: Handling Result Set
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String phone = resultSet.getString(4);

                String roleString = resultSet.getString(10);// string
                // chuyen tu string về enum
                Role role = Role.valueOf(roleString);

                if (Role.EXPERIENCECANDIDATE.equals(role)) {
                    int expInYear = resultSet.getInt(7);
                    String proSkill = resultSet.getString(8);

                    Candidate e = new ExperienceCandidate(id, firstName, lastName, phone, email , password, role, expInYear, proSkill);
                    return e;
                } else {
                    // chuyển rank từ String >>> enum
                    String g = resultSet.getString(9);
                    GraduationRank graduationRank = GraduationRank.valueOf(g);

                    Candidate f = new FresherCandidate(id, firstName, lastName, phone, email, password, role,
                            graduationRank);
                    return f;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Dang nhap that bai!");
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public boolean checkEmailExist(String email) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM candidate WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;// tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // cho dùng thực thi try hay catch() thì luôn luôn chạy finally
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }
}
