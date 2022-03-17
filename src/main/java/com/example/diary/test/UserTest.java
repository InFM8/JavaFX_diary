package com.example.diary.test;

import com.example.diary.utils.BCryptPassword;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.example.diary.model.User;
import com.example.diary.model.UserDAO;

public class UserTest {
    UserDAO userDAO = new UserDAO();

    public User userLocalPositive;
    public User userLocalNegative;
    public User userDB;

    @Before
    public void setup() {
        userLocalPositive = new User("test@test.com", "test1");
        userLocalNegative = new User("testneg@test.com", "test1");
    }

    @Test
    public void createUserPositiveTest() {
        userDAO.insert(userLocalPositive);
        userDB = userDAO.searchAllByEmail("test@test.com");
        compareUsersEquals(userLocalPositive, userDB);
    }

    @Test
    public void createUserNegativeTest() {
        userDAO.insert(userLocalNegative);
        userDB = userDAO.searchAllByEmail("testneg@test.com");
        compareUsersNotEquals(userLocalNegative, userDB);
    }

    @Test
    public void searchUserPositiveTest() {
        userDB = userDAO.searchAllByEmail("test@test.com");
        compareUsersEquals(userLocalPositive, userDB);
    }

    @Test
    public void searchUserNegativeTest() {
        userDB = userDAO.searchAllByEmail("test@test.com");
        compareUsersNotEquals(userLocalNegative, userDB);
    }

    private void compareUsersEquals(User userLocal, User userDB) {
        boolean pass = BCryptPassword.checkPassword(userLocal.getPassword(), userDB.getPassword());

        Assert.assertEquals(userLocal.getEmail(), userDB.getEmail());
        Assert.assertEquals(true, pass);
    }

    private void compareUsersNotEquals(User userLocal, User userDB) {
        Assert.assertNotEquals(userLocal.getEmail(), userDB.getEmail());
        Assert.assertNotEquals(userLocal.getPassword(), userDB.getPassword());
    }
}
