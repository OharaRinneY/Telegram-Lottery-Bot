package moe.yiheng.dao.impl;

import moe.yiheng.dao.UserDao;
import moe.yiheng.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public User findById(Integer id) {
        return hibernateTemplate.get(User.class, id);
    }

    public void add(User user) {
        hibernateTemplate.save(user);
    }

    @Override
    public void update(User user) {
        hibernateTemplate.update(user);
    }
}
