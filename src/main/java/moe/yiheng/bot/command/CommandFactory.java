package moe.yiheng.bot.command;

import moe.yiheng.bot.MyBot;
import moe.yiheng.exceptions.CommandNotFoundException;
import moe.yiheng.pojo.User;
import org.telegram.telegrambots.api.objects.Message;

public class CommandFactory {
    public static Command getCommand(Message message, User user) throws CommandNotFoundException {
        switch (message.getText()) {
            case "/new":
                return new NewCommand(message,user);
            default:
                throw new CommandNotFoundException();
        }
    }
}
