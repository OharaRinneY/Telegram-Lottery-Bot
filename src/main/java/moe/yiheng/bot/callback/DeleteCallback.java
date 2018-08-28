package moe.yiheng.bot.callback;

import moe.yiheng.bot.Keyboards;
import moe.yiheng.pojo.Lottery;
import moe.yiheng.pojo.User;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;

public class DeleteCallback extends Callback {
    public DeleteCallback(CallbackQuery callbackQuery, User user) {
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
        bot.executeWithoutException(new EditMessageText()
                .setChatId(callbackQuery.getMessage().getChatId())
                .setMessageId(callbackQuery.getMessage().getMessageId())
                .setText("确认取消抽奖 " + lottery.getName()+" 吗")
                .setReplyMarkup(Keyboards.getConfirmDeleteKeyboard(lottery)));
    }
}
