package moe.yiheng.dao;

import moe.yiheng.pojo.Lottery;

public interface LotteryDao {
    void save(Lottery lottery);

    Lottery findByUuid(String uuid);

    void update(Lottery lottery);
}
