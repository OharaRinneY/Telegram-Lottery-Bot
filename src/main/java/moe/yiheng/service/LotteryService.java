package moe.yiheng.service;

import moe.yiheng.pojo.Lottery;

public interface LotteryService {
    void save(Lottery lottery);

    Lottery findByUuid(String uuid);

    void update(Lottery lottery);
}
