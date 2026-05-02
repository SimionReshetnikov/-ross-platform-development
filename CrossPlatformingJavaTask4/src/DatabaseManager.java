import java.sql.*;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://db:3306/testdb";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void createTable() throws SQLException {
        String sql = """
                 CREATE TABLE IF NOT EXISTS users (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(100)
                )
                """;

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createUser(String name) throws SQLException {
        String sql = "INSERT INTO users (name) VALUES (?)";

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
        }
    }

    public void readUsers() throws SQLException {
        String sql = "SELECT * FROM users";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("id") + " - " +
                                resultSet.getString("name")
                );
            }
        }
    }

    public void updateUser(int id, String name) throws SQLException {
        String sql = "UPDATE users SET name = ? WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
