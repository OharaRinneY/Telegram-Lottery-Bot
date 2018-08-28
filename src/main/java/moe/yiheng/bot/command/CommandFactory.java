package moe.yiheng.bot.command;

import moe.yiheng.exceptions.CommandNotFoundException;
import moe.yiheng.pojo.User;
import moe.yiheng.utils.CommandUtils;
import org.telegram.telegrambots.api.objects.Message;

public class CommandFactory {
    public static Command getCommand(Message message, User user) throws CommandNotFoundException {
        switch (CommandUtils.getCommand(message.getText())) {
            case "/new":
                return new NewCommand(message, user);
            case "/start":
                return new StartCommand(message, user);
            case "/my":
                return new MyCommand(message, user);
            default:
                throw new CommandNotFoundException();
        }
    }
}
