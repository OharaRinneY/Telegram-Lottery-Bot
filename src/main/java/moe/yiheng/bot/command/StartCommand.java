package moe.yiheng.bot.command;

import moe.yiheng.bot.MyBot;
import moe.yiheng.pojo.Lottery;
import moe.yiheng.pojo.User;
import moe.yiheng.service.LotteryService;
import moe.yiheng.service.UserService;
import moe.yiheng.utils.CommandUtils;
import moe.yiheng.utils.SpringUtils;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

import java.util.Set;

public class StartCommand extends Command {

    private MyBot bot;
    private UserService userService;
    private LotteryService lotteryService;

    @Override
    public void handle() {
        if (!StringUtils.isEmpty(CommandUtils.getParameter(message.getText()))) { // 即 参与抽奖
            Lottery lottery = lotteryService.findByUuid(CommandUtils.getParameter(message.getText()));
            Set<Lottery> joinedLotteries = user.getJoinedLotteries();
            if (joinedLotteries.contains(lottery)) { // 已经参加
                bot.executeWithoutException(new SendMessage(message.getChatId(), "您已经参与了这个抽奖,不用重新参与啦"));
            } else {
                joinedLotteries.add(lottery);
                userService.update(user);
                String s = new StringBuilder("成功参与 @")
                        .append(lottery.getCreatedByUser().getUsername())
                        .append(" 发起的抽奖 ")
                        .append(lottery.getName())
                        .append("\n目前参与人数为:")
                        .append(lottery.getJoinedUsers().size()+1) // 这里查出来没有带自己 所以+1
                        .toString();
                bot.executeWithoutException(new SendMessage(message.getChatId(), s));
            }
        }
    }

    public StartCommand(Message message, User user) {
        super(message, user);
        bot = (MyBot) SpringUtils.getBean("myBot");
        userService = (UserService) SpringUtils.getBean("userService");
        lotteryService = (LotteryService) SpringUtils.getBean("lotteryService");
    }
}
