package com.botanicalNameTest.dao;

import com.botanicalNameTest.BotanicalNameTestApplication;
import com.botanicalNameTest.model.domain.User;
import com.botanicalNameTest.repository.dao.UserDao;
import com.botanicalNameTest.repository.dao.UserDaoJdbc;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserDaoTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    UserDaoJdbc userDao;


    private static List<User> userList = List.of(
            new User("fosong", "1356"),
            new User("JaeDoo", "1999"),
            new User("test", "ttest")
    );

    @BeforeEach
    public void databaseSetting() {
        userDao = new UserDaoJdbc(jdbcTemplate);
        userDao.deleteAll();
    }

    @Test
    public void addUserTest() {
        userDao.add(userList.get(0));
        userDao.add(userList.get(1));
        userDao.add(userList.get(2));

        List<User> list = userDao.getAll();

        assertEquals(3, list.size());

        assertTrue(userList.stream().anyMatch((user) -> user.equals(list.get(0))));
        assertTrue(userList.stream().anyMatch((user) -> user.equals(list.get(1))));;
        assertTrue(userList.stream().anyMatch((user) -> user.equals(list.get(2))));;
    }

    @Test
    public void getUserTest() {
        userDao.add(userList.get(0));
        userDao.add(userList.get(1));
        userDao.add(userList.get(2));

        Optional<User> optionalUser = userDao.get(userList.get(0).getUserId());
        assertEquals(userList.get(0), optionalUser.get());
    }

    @Test
    public void deleteUserTest() {
        userDao.add(userList.get(0));
        userDao.add(userList.get(1));
        userDao.add(userList.get(2));

        userDao.delete(userList.get(0).getUserId());

        List<User> list = userDao.getAll();


        assertEquals(2, list.size());

        assertTrue(list.stream().noneMatch((user) -> user.equals(userList.get(0))));
        assertTrue(list.stream().anyMatch((user) -> user.equals(userList.get(1))));
        assertTrue(list.stream().anyMatch((user) -> user.equals(userList.get(2))));
    }
}
