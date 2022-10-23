package com.botanicalNameTest.repository.dao;

import com.botanicalNameTest.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoJdbc implements UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> get(String userId) {
        if (userId == null) {
            return Optional.empty();
        }
        User user = jdbcTemplate.queryForObject("select * from user where userId = ?", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                String userId = rs.getString("userId");
                String userPassword = rs.getString("userPassword");
                System.out.println(userId);
                return new User(userId, userPassword);
            }
        }, userId);
        if (user == null) {
            return Optional.empty();
        } else {
            return Optional.of(user);
        }
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("select * from user", (rs, rowNum) -> {
            return new User(rs.getString(1), rs.getString(2));
        });
    }

    @Override
    public boolean add(User user) {
        int count = jdbcTemplate.queryForObject("select count(*) from user where userId = ?", Integer.class, user.getUserId());
        if (count != 0) {
            return false;
        }
        jdbcTemplate.update("insert into user(userId, userPassword) values(?, ?)", user.getUserId(), user.getUserPassword());
        return true;
    }

    @Override
    public int update(String userId, User user) {
        int count = jdbcTemplate.queryForObject("select count(*) from user where userId = ?", Integer.class, user.getUserId());
        if (userId != user.getUserId() && count != 0) {
            throw new UserAlreadyExistsException(user.getUserId());
        }
        count = jdbcTemplate.queryForObject("select count(*) from user where userId = ?", Integer.class, userId);
        if (count == 0) {
            return count;
        } else {
            jdbcTemplate.update("update user set userId = ?, userPassword = ? where userId = ?", user.getUserId(), user.getUserPassword(), userId);
            return count;
        }
    }

    @Override
    public int delete(String userId) {
        int count = jdbcTemplate.queryForObject("select count(*) from user where userId = ?", Integer.class, userId);

        if (count == 0) {
            return 0;
        }

        jdbcTemplate.update("delete from user where userId = ?", userId);

        return count;
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from user");
    }
}
