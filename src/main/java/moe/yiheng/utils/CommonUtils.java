package moe.yiheng.utils;

import moe.yiheng.pojo.Lottery;
import moe.yiheng.pojo.LotteryStatus;

import java.util.*;

public class CommonUtils {
    public static String randomUuid(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static Set<Lottery> selectActiveLotteries(Set<Lottery> set) {
        Set<Lottery> activeLotteries = new HashSet<>();
        set.forEach(lottery -> {
            if (lottery.getStatus().equals(LotteryStatus.ACTIVE.getIndex())) {
                activeLotteries.add(lottery);
            }
        });
        return activeLotteries;
    }
}
