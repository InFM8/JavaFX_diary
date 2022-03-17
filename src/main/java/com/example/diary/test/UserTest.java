package com.example.diary.test;

import org.junit.After;
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
         compareUsers(userLocalPositive, userDB);
    }

    private void compareUsers(User userLocal, User userDB) {
        Assert.assertEquals(userLocal.getEmail(), userDB.getEmail());
        Assert.assertEquals(userLocal.getPassword(), userDB.getPassword());

    }


}
