package com.qcentrifuge.configs;


import com.qcentrifuge.telegram.Bot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Configuration
public class TelegramConfig {

    @Value("${tgbot.token}")
    private String token;

    @Bean
    public Bot getBotAdmin(){
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
        String name = "SpringAdminInfo_bot";
        Bot bot = new Bot(options);
        bot.setName(name);
        bot.setToken(token);
        return bot;
    }

}
