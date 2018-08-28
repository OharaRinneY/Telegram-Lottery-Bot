package moe.yiheng.bot.command;

import moe.yiheng.pojo.User;
import moe.yiheng.pojo.UserStatus;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class ResetCommand extends Command {

    public ResetCommand(Message message, User user) {
        super(message, user);
    }

    @Override
    public void handle() {
        if (user.getStatus().equals(UserStatus.NOTHING.getIndex())) {
            bot.executeWithoutException(new SendMessage(message.getChatId(),"目前没有进行任何操作"));
            return;
        }
        user.setStatus(UserStatus.NOTHING.getIndex());
        userService.update(user);
        bot.executeWithoutException(new SendMessage(message.getChatId(),"会话状态已清空"));
    }
}
