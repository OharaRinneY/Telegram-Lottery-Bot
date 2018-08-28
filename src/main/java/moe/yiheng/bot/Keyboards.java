package moe.yiheng.bot;

import moe.yiheng.pojo.Lottery;
import moe.yiheng.pojo.User;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Keyboards {
    public static InlineKeyboardMarkup getSelectLotteryKeyboard(Set<Lottery> lotteries) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        lotteries.forEach(lottery -> {
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            rowInline.add(new InlineKeyboardButton().setText(lottery.getName()).setCallbackData("edit|" + lottery.getUuid()));
            rowsInline.add(rowInline);
        });
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public static InlineKeyboardMarkup getEditKeyboard(Lottery lottery){
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("开奖").setCallbackData("startLottery|" + lottery.getUuid()));
        rowInline.add(new InlineKeyboardButton().setText("取消此次抽奖").setCallbackData("delete|" + lottery.getUuid()));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public static InlineKeyboardMarkup getConfirmDeleteKeyboard(Lottery lottery) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("确认").setCallbackData("confirmDelete|" + lottery.getUuid()));
        rowInline.add(new InlineKeyboardButton().setText("取消").setCallbackData("cancel|" + lottery.getUuid()));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public static InlineKeyboardMarkup getConfirmStartKeyboard(Lottery lottery) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("确认").setCallbackData("confirmStart|" + lottery.getUuid()));
        rowInline.add(new InlineKeyboardButton().setText("取消").setCallbackData("cancel|" + lottery.getUuid()));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
}
