package moe.yiheng.bot;

import moe.yiheng.pojo.Lottery;
import moe.yiheng.pojo.User;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Keyboards {
    public static InlineKeyboardMarkup getSelectLotteryKeyboard(User user) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        Set<Lottery> createdLotteries = user.getCreatedLotteries();
        createdLotteries.forEach(lottery -> {
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            rowInline.add(new InlineKeyboardButton().setText(lottery.getName()).setCallbackData("edit|" + lottery.getUuid()));
            rowsInline.add(rowInline);
        });
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
}
