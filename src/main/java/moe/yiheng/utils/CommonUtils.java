package moe.yiheng.utils;

import java.util.UUID;

public class CommonUtils {
    public static String randomUuid(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
