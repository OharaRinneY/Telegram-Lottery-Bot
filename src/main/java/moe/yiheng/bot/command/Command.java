package moe.yiheng.bot.command;

import moe.yiheng.pojo.User;
import org.telegram.telegrambots.api.objects.Message;

public abstract class Command {
    protected Message message;
    protected User user;

    public abstract void handle();

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Command(Message message, User user) {
        this.message = message;
        this.user = user;
    }
}
