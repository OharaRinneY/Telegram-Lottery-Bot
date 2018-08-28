package moe.yiheng.service;

import moe.yiheng.pojo.User;

public interface UserService {
    User findById(Integer id);

    void add(User user);

    void update(User user);
}
