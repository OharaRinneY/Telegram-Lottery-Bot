package moe.yiheng.dao;

import moe.yiheng.pojo.User;

public interface UserDao {
    User findById(Integer id);

    void add(User user);

    void update(User user);
}
