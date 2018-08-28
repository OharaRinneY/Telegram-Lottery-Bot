package moe.yiheng;

import moe.yiheng.bot.MyBot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

//@SpringBootApplication
public class Launcher implements CommandLineRunner {


    public static void main(String[] args) throws TelegramApiRequestException {
        ApiContextInitializer.init();
        SpringApplication.run(Launcher.class, args);
    }

    public void run(String... args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        TelegramBotsApi api = new TelegramBotsApi();
        MyBot bot = (MyBot) applicationContext.getBean("myBot");
        api.registerBot(bot);
        System.out.println("bot started");
    }
}
