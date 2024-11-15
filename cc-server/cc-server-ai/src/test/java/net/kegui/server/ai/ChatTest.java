package net.kegui.server.ai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatTest {

    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Test
    void chat() {
        String msg = "你好。";
        String aiMessage = ChatClient.create(openAiChatModel).prompt()
                .messages(new UserMessage(msg))
                .call()
                .content();
        System.out.printf("%s\n", aiMessage);
    }
}
