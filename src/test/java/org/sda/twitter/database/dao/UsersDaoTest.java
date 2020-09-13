package org.sda.twitter.database.dao;

import org.junit.Assert;
import org.junit.Test;

public class UsersDaoTest {

    @Test
    public void shouldReturnTrueWhenUserExists() {
        // given
        UsersDao underTest = new UsersDao();

        // when
        boolean found = underTest.hasAdmin("admin", "admin123");

        // then
        Assert.assertTrue(found);
    }
}
