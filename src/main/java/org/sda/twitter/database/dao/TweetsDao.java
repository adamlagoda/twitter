package org.sda.twitter.database.dao;

import org.sda.twitter.database.configuration.DatasourceConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class TweetsDao {

    private DatasourceConfiguration datasourceConfiguration;

    public TweetsDao() {
        datasourceConfiguration = DatasourceConfiguration.getInstance();
    }

    public boolean publishTweet(String login, String tweet, LocalDateTime publishDateTime) {
        try (Connection connection = datasourceConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "insert into tweets(author_id, message, published) values ((select id from users where login=?), ?, ?);")) {
            statement.setString(1, login);
            statement.setString(2, tweet);
            statement.setTimestamp(3, Timestamp.valueOf(publishDateTime));
            int updatedRows = statement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
