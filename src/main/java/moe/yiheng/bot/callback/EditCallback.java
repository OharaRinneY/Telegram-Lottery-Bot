package moe.yiheng.bot.callback;

import moe.yiheng.bot.Keyboards;
import moe.yiheng.pojo.Lottery;
import moe.yiheng.pojo.User;
import moe.yiheng.utils.CommonUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;

public class EditCallback extends Callback {

    public EditCallback(CallbackQuery callbackQuery, User user) {
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
                .setText("已选择 " + lottery.getName() + ",\n目前参与人数为 " + lottery.getJoinedUsers().size()+"\n邀请链接:"+ CommonUtils.generateInviteLink(bot.getBotUsername(),lottery.getUuid()))
                .setReplyMarkup(Keyboards.getEditKeyboard(lottery)));
    }
}
