package ru.ksenideni.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    public void create(UserDTO user) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, enabled) VALUES (?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, passwordEncoder.encode(user.getPassword()));
            statement.setBoolean(3, true);
            statement.execute();

            statement = connection.prepareStatement("INSERT INTO authorities (username, authority) VALUES (?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, "ROLE_USER");
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
