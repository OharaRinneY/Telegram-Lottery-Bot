package moe.yiheng.bot.command;

import moe.yiheng.bot.MyBot;
import moe.yiheng.pojo.Lottery;
import moe.yiheng.pojo.LotteryStatus;
import moe.yiheng.pojo.User;
import moe.yiheng.pojo.UserStatus;
import moe.yiheng.service.LotteryService;
import moe.yiheng.service.UserService;
import moe.yiheng.utils.SpringUtils;
import org.apache.http.impl.bootstrap.HttpServer;
import org.hibernate.Hibernate;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

import java.util.Set;

public class NewCommand extends Command{

    private Message message = getMessage();
    private User user = getUser();
    private MyBot bot;
    private UserService userService;
    private LotteryService lotteryService;
    @Override
    public void handle() {
        System.out.println(message);
        if (message.getText().equals("/new")) {
            user.setStatus(UserStatus.CREATING.getIndex());
            userService.update(user);
            bot.executeWithoutException(new SendMessage(message.getChatId(), "正在创建抽奖,请发送抽奖的名称"));
        } else if (message.hasText()) { // 收到的是抽奖名称
            if (message.getText().length() >= 3 && message.getText().length() <= 200) {
                Lottery lottery = new Lottery();
                lottery.setName(message.getText());
                lottery.setStatus(LotteryStatus.ACTIVE.getIndex());
                lottery.setCreatedByUser(user);
                lotteryService.save(lottery);
                user.setStatus(UserStatus.NOTHING.getIndex());
                userService.update(user);
                bot.executeWithoutException(new SendMessage(message.getChatId(), "创建成功"));
            } else {
                bot.executeWithoutException(new SendMessage(message.getChatId(), "名称长度必须为3~200个字符"));
            }

        }

    }

    public NewCommand(Message message, User user) {
        super(message, user);
        bot = (MyBot) SpringUtils.getBean("myBot");
        userService = (UserService) SpringUtils.getBean("userService");
        lotteryService = (LotteryService) SpringUtils.getBean("lotteryService");
    }

}
