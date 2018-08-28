package moe.yiheng.bot.callback;

import moe.yiheng.pojo.User;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;

public class CancelCallback extends Callback {

    public CancelCallback(CallbackQuery callbackQuery, User user) {
        super(callbackQuery, user);
    }

    @Override
    public void handle() {
        bot.executeWithoutException(new EditMessageText()
                .setChatId(callbackQuery.getMessage().getChatId())
                .setMessageId(callbackQuery.getMessage().getMessageId())
                .setText("操作已取消"));
    }
}
