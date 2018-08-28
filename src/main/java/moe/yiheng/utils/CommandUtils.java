package moe.yiheng.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandUtils {

    public static String getCommand(String text) {
        Matcher matcher = Pattern.compile("/([a-zA-z0-9_]+)").matcher(text);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    public static List<String> getParameters(String text) {
        String regex = "/[a-zA-z0-9_]+\\s(.+)";
        Matcher matcher = Pattern.compile(regex).matcher(text);
        List<String> list = new ArrayList<>();
        if (matcher.find()) {
            String s = matcher.group(1);
            if (!s.contains(" ")) {
                list.add(s);
            }else{
                String[] args = s.split(" +");
                for (String arg : args) {
                    list.add(arg);
                }
            }
        }
        return list;
    }

    public static String getParameter(String text){
        String regex = "/[a-zA-z0-9_]+\\s(.+)";
        Matcher matcher = Pattern.compile(regex).matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
