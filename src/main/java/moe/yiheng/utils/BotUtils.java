package moe.yiheng.utils;

import moe.yiheng.bot.MyBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.groupadministration.LeaveChat;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Component
public class BotUtils {

    @Autowired
    MyBot bot;

    public void leaveGroup(Message message) {
        try {
            bot.execute(new SendMessage(message.getChatId(), "该 bot 不能在群组中使用 即将退出该群组"));
            bot.execute(new LeaveChat().setChatId(message.getChatId()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
