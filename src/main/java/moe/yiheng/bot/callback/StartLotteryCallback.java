package moe.yiheng.bot.callback;

import moe.yiheng.bot.Keyboards;
import moe.yiheng.pojo.Lottery;
import moe.yiheng.pojo.User;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;

public class StartLotteryCallback extends Callback {

    public StartLotteryCallback(CallbackQuery callbackQuery, User user) {
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
        if (lottery.getJoinedUsers().size() <= 1) {
            bot.executeWithoutException(new EditMessageText()
                    .setChatId(callbackQuery.getMessage().getChatId())
                    .setMessageId(callbackQuery.getMessage().getMessageId())
                    .setText("参与人数未满两人,无法开奖"));
            return;
        }
        bot.executeWithoutException(new EditMessageText()
                .setChatId(callbackQuery.getMessage().getChatId())
                .setMessageId(callbackQuery.getMessage().getMessageId())
                .setText("确认要对 " + lottery.getName() + " 执行开奖操作吗\n目前参与人数为 " + lottery.getJoinedUsers().size())
                .setReplyMarkup(Keyboards.getConfirmStartKeyboard(lottery)));
    }
}
