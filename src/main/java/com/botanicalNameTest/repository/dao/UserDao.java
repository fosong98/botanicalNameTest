package com.botanicalNameTest.repository.dao;

import com.botanicalNameTest.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    public Optional<User> get(String userId);
    public List<User> getAll();
    public boolean add(User user);
    public int update(String userId, User user);
    public int delete(String userId);
    public void deleteAll();
}
