package moe.yiheng.bot;

import moe.yiheng.bot.callback.Callback;
import moe.yiheng.bot.callback.CallbackFactory;
import moe.yiheng.bot.command.Command;
import moe.yiheng.bot.command.CommandFactory;
import moe.yiheng.bot.command.NewCommand;
import moe.yiheng.exceptions.CallbackNotFoundException;
import moe.yiheng.exceptions.CommandNotFoundException;
import moe.yiheng.pojo.User;
import moe.yiheng.pojo.UserStatus;
import moe.yiheng.service.UserService;
import moe.yiheng.utils.BotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.Serializable;

public class MyBot extends TelegramLongPollingBot {

    @Autowired
    private UserService userService;
    @Autowired
    private BotUtils botUtils;
    private String botUsername;
    private String botToken;

    public void onUpdateReceived(Update update) {
        if (update == null) return;
        Message message = null;
        if (update.hasCallbackQuery()) {
            message = update.getCallbackQuery().getMessage();
        } else if (update.hasMessage()) {
            message = update.getMessage();
        } else {
            return;
        }
        if (message.getChat().isGroupChat() || message.getChat().isSuperGroupChat())
            botUtils.leaveGroup(message);
        User user = userService.findById(message.getFrom().getId());
        if (user == null) { // user 不存在
            user = new User();
            user.setFirstname(message.getFrom().getFirstName());
            user.setId(message.getFrom().getId());
            user.setUsername(message.getFrom().getUserName());
            user.setStatus(UserStatus.NOTHING.getIndex());
            userService.add(user);
            user = userService.findById(message.getFrom().getId());
        }
        if (update.hasCallbackQuery()) {
            try {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                Callback callback = CallbackFactory.getCallback(callbackQuery, user);
                callback.handle();
            } catch (CallbackNotFoundException e) {
                executeWithoutException(new SendMessage(message.getChatId(), "无法识别的指令"));
            }
            return;
        }
        if (message.isCommand()) {
            try {
                Command command = CommandFactory.getCommand(message, user);
                command.handle();
            } catch (CommandNotFoundException e) {
                executeWithoutException(new SendMessage(message.getChatId(), "无法识别的指令"));
            }
            return;
        }
        switch (UserStatus.getByIndex(user.getStatus())) {
            case NOTHING:
                executeWithoutException(new SendMessage(message.getChatId(), "无法识别您的命令"));
                return;
            case CREATING:
                new NewCommand(message, user).handle();

        }
    }

    public <T extends Serializable, Method extends BotApiMethod<T>> T executeWithoutException(Method method) {
        try {
            return execute(method);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
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
