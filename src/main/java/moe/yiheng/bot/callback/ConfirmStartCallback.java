package moe.yiheng.bot.callback;

import moe.yiheng.bot.Keyboards;
import moe.yiheng.pojo.Lottery;
import moe.yiheng.pojo.LotteryStatus;
import moe.yiheng.pojo.User;
import moe.yiheng.utils.CommonUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;

import java.util.Random;
import java.util.Set;

public class ConfirmStartCallback extends Callback {
    public ConfirmStartCallback(CallbackQuery callbackQuery, User user) {
        super(callbackQuery, user);
    }

    @Override
    public void handle() {
        String[] s = callbackQuery.getData().split("\\|");
        Lottery lottery = lotteryService.findByUuid(s[1]);
        if (lottery == null) {
            bot.executeWithoutException(new SendMessage(callbackQuery.getMessage().getChatId(), "出现错误,无法找到该抽奖"));
            return;
        }
        Set<User> joinedUsers = lottery.getJoinedUsers();
        Object[] users = joinedUsers.toArray();

        int i = new Random().nextInt(users.length);
        User luckyUser = (User) users[i]; // 幸运用户
        lottery.setStatus(LotteryStatus.FINISHED.getIndex());
        lotteryService.update(lottery);
        String text = new StringBuilder("对 @")
                .append(lottery.getCreatedByUser().getUsername())
                .append(" 的抽奖 ")
                .append(lottery.getName())
                .append(" 的开奖结果如下\n幸运用户为 @")
                .append(luckyUser.getUsername())
                .append("\nid:<strong>")
                .append(luckyUser.getId())
                .append("</strong>\nfirstname:")
                .append(luckyUser.getFirstname())
                .append("\n此次抽奖唯一抽奖ID为<strong>")
                .append(CommonUtils.generateId(lottery.getUuid()))
                .append("</strong>,\n可与参与时ID进行比对确保公平公正")
                .toString();
        bot.executeWithoutException(new SendMessage()
                .setText(text)
                .setParseMode("html")
                .setChatId(callbackQuery.getMessage().getChatId()));
        bot.executeWithoutException(new EditMessageText()
                .setChatId(callbackQuery.getMessage().getChatId())
                .setMessageId(callbackQuery.getMessage().getMessageId())
                .setText("已完成开奖"));
    }
}
