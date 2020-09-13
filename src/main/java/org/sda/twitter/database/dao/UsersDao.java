package org.sda.twitter.database.dao;

import org.sda.twitter.database.configuration.DatasourceConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
