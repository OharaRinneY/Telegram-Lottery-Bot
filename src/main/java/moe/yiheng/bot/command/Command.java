package moe.yiheng.bot.command;

import moe.yiheng.bot.MyBot;
import moe.yiheng.pojo.User;
import moe.yiheng.service.LotteryService;
import moe.yiheng.service.UserService;
import moe.yiheng.utils.SpringUtils;
import org.telegram.telegrambots.api.objects.Message;

public abstract class Command {
    protected Message message;
    protected User user;
    protected MyBot bot;
    protected UserService userService;
    protected LotteryService lotteryService;

    public abstract void handle();

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Command(Message message, User user) {
        this.message = message;
        this.user = user;
        bot = (MyBot) SpringUtils.getBean("myBot");
        userService = (UserService) SpringUtils.getBean("userService");
        lotteryService = (LotteryService) SpringUtils.getBean("lotteryService");
    }
}
