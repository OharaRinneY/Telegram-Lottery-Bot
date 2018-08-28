package moe.yiheng.bot;

import moe.yiheng.pojo.User;
import moe.yiheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.groupadministration.LeaveChat;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {

    @Autowired
    private UserService userService;
    private String botUsername;
    private String botToken;

    public void onUpdateReceived(Update update) {
        if (update == null) return;
        if (!update.hasMessage()) return;
        Message message = update.getMessage();
        if (message.getChat().isGroupChat() || message.getChat().isSuperGroupChat())
            leaveGroup(message);
        User user = userService.findById(message.getFrom().getId());
        if (user == null) { // user 不存在
            user = new User();
            user.setFirstname(message.getFrom().getFirstName());
            user.setId(message.getFrom().getId());
            user.setUsername(message.getFrom().getUserName());
            userService.add(user);
        }
    }

    private void leaveGroup(Message message) {
        try {
            execute(new SendMessage(message.getChatId(), "该 bot 不能在群组中使用 即将退出该群组"));
            execute(new LeaveChat().setChatId(message.getChatId()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}
