package moe.yiheng.service.impl;

import moe.yiheng.dao.LotteryDao;
import moe.yiheng.pojo.Lottery;
import moe.yiheng.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("lotteryService")
@Transactional
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    LotteryDao dao;

    @Override
    public void save(Lottery lottery) {
        dao.save(lottery);
    }

    @Override
    public Lottery findByUuid(String uuid) {
        return dao.findByUuid(uuid);
    }

    @Override
    public void update(Lottery lottery) {
        dao.update(lottery);
    }
}
