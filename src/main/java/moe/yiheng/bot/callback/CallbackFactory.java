package moe.yiheng.bot.callback;

import moe.yiheng.exceptions.CallbackNotFoundException;
import moe.yiheng.pojo.User;
import org.telegram.telegrambots.api.objects.CallbackQuery;

import java.util.Arrays;

public class CallbackFactory {

    public static Callback getCallback(CallbackQuery callbackQuery, User user) throws CallbackNotFoundException {
        String[] s = callbackQuery.getData().split("\\|");
        switch (s[0]) {
            case "edit":
                return new EditCallback(callbackQuery, user);
            case "delete":
                return new DeleteCallback(callbackQuery, user);
            case "confirmDelete":
                return new ConfirmDeleteCallback(callbackQuery, user);
            default:
                throw new CallbackNotFoundException();
        }
    }
}
