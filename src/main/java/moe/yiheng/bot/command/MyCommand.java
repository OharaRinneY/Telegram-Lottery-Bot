package moe.yiheng.bot.command;

import moe.yiheng.bot.Keyboards;
import moe.yiheng.bot.MyBot;
import moe.yiheng.pojo.Lottery;
import moe.yiheng.pojo.User;
import moe.yiheng.service.LotteryService;
import moe.yiheng.service.UserService;
import moe.yiheng.utils.SpringUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

import java.util.Set;

public class MyCommand extends Command {

    private MyBot bot;
    private UserService userService;
    private LotteryService lotteryService;

    @Override
    public void handle() {
        Set<Lottery> createdLotteries = user.getCreatedLotteries();
        if (createdLotteries.isEmpty()) {
            bot.executeWithoutException(new SendMessage(message.getChatId(), "您还没有创建抽奖"));
        } else {
            bot.executeWithoutException(new SendMessage()
                    .setChatId(message.getChatId())
                    .setText("您现在有以下几个抽奖,请选择一个进行操作")
                    .setReplyMarkup(Keyboards.getSelectLotteryKeyboard(user)));
        }
    }

    public MyCommand(Message message, User user) {
        super(message, user);
        bot = (MyBot) SpringUtils.getBean("myBot");
        userService = (UserService) SpringUtils.getBean("userService");
        lotteryService = (LotteryService) SpringUtils.getBean("lotteryService");
    }

}
