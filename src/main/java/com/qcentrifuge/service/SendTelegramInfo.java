package com.qcentrifuge.service;

import com.qcentrifuge.telegram.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendTelegramInfo {

    @Qualifier("getBotAdmin")
    @Autowired
    private Bot bot;

    @Value("${adminChatId}")
    private String adminChat;

    public void sendToAdmin(String info){
        sendMsg(adminChat, info);
    }


    private synchronized void sendMsg(String chatId, String info) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(info);
        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
