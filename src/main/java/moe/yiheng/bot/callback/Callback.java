package moe.yiheng.bot.callback;

import moe.yiheng.bot.MyBot;
import moe.yiheng.pojo.User;
import moe.yiheng.service.LotteryService;
import moe.yiheng.service.UserService;
import moe.yiheng.utils.SpringUtils;
import org.telegram.telegrambots.api.objects.CallbackQuery;

public abstract class Callback {
    protected CallbackQuery callbackQuery;
    protected User user;

    protected MyBot bot;
    protected UserService userService;
    protected LotteryService lotteryService;

    public abstract void handle();

    public Callback(CallbackQuery callbackQuery, User user) {
        this.callbackQuery = callbackQuery;
        this.user = user;
        bot = (MyBot) SpringUtils.getBean("myBot");
        userService = (UserService) SpringUtils.getBean("userService");
        lotteryService = (LotteryService) SpringUtils.getBean("lotteryService");
    }

    public CallbackQuery getCallbackQuery() {
        return callbackQuery;
    }

    public void setCallbackQuery(CallbackQuery callbackQuery) {
        this.callbackQuery = callbackQuery;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
