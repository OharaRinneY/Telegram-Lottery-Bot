package moe.yiheng.bot.command;

import moe.yiheng.bot.MyBot;
import moe.yiheng.pojo.Lottery;
import moe.yiheng.pojo.LotteryStatus;
import moe.yiheng.pojo.User;
import moe.yiheng.pojo.UserStatus;
import moe.yiheng.service.LotteryService;
import moe.yiheng.service.UserService;
import moe.yiheng.utils.CommonUtils;
import moe.yiheng.utils.SpringUtils;
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
        if (message.getText().equals("/new")) {
            Set<Lottery> createdLotteries = user.getCreatedLotteries();
            int count = 0;
            for (Lottery lottery : createdLotteries) {
                if (lottery.getStatus() == LotteryStatus.ACTIVE.getIndex()) count++;
            }
            if (count >= 5) {
                bot.executeWithoutException(new SendMessage(message.getChatId(), "每位用户只能创建5个抽奖"));
                return;
            }
            user.setStatus(UserStatus.CREATING.getIndex());
            userService.update(user);
            bot.executeWithoutException(new SendMessage(message.getChatId(), "正在创建抽奖,请发送抽奖的名称"));
        } else if (message.hasText()) { // 收到的是抽奖名称
            if (message.getText().length() >= 3 && message.getText().length() <= 200) {
                Lottery lottery = new Lottery();
                lottery.setUuid(CommonUtils.randomUuid());
                lottery.setName(message.getText());
                lottery.setStatus(LotteryStatus.ACTIVE.getIndex());
                lottery.setCreatedByUser(user);
                lotteryService.save(lottery);
                user.setStatus(UserStatus.NOTHING.getIndex());
                userService.update(user);
                bot.executeWithoutException(new SendMessage(message.getChatId(), "创建成功!您可以转发下一条消息到别处邀请他人加入抽奖"));
                String msg = new StringBuilder("欢迎加入 @")
                        .append(message.getFrom().getUserName())
                        .append(" 创建的抽奖\n")
                        .append("唯一抽奖ID(可与开奖时比对):")
                        .append(lottery.getUuid().hashCode())
                        .append("\n点击以下链接即可加入\nhttps://t.me/")
                        .append(bot.getBotUsername())
                        .append("?start=")
                        .append(lottery.getUuid())
                        .toString();
                bot.executeWithoutException(new SendMessage(message.getChatId(),msg));
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
