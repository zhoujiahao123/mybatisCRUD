package com.jacob.dao;

import com.jacob.domain.QureyVo;
import com.jacob.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    User findById(Integer id);

    List<User> findByName(String username);

    int findTotal();

    List<User> findByVo(QureyVo vo);
}
