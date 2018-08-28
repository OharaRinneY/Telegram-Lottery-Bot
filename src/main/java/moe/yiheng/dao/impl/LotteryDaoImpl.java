package moe.yiheng.dao.impl;

import moe.yiheng.dao.LotteryDao;
import moe.yiheng.pojo.Lottery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository("lotteryDao")
public class LotteryDaoImpl implements LotteryDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public void save(Lottery lottery) {
        hibernateTemplate.save(lottery);
    }

    @Override
    public Lottery findByUuid(String uuid) {
        return hibernateTemplate.get(Lottery.class, uuid);
    }

    @Override
    public void update(Lottery lottery) {
        hibernateTemplate.update(lottery);
    }
}
