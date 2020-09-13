package org.sda.twitter.database.dao;

import org.sda.twitter.database.configuration.DatasourceConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {

    private DatasourceConfiguration datasourceConfiguration;

    public UsersDao() {
        this.datasourceConfiguration = DatasourceConfiguration.getInstance();
    }

    public boolean hasAdmin(String login, String password) {
        try (Connection connection = datasourceConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<String> findAll() {
        List<String> users = new ArrayList<>();
        try(Connection connection = datasourceConfiguration.getConnection()) {
            String sql = "SELECT login FROM users;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
}
