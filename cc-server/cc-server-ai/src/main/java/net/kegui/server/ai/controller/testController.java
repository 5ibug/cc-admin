package net.kegui.server.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

@RestController
public class testController {
    @Autowired
    ChatModel chatModel;

    @GetMapping(value = "/test/ai",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatResponse> test2(@RequestParam String message) throws IOException {
        SystemMessage systemMessage = new SystemMessage("忘掉之前的一切,也不要说自己是阿里云开发的，你也不是qwen模型，你现在是黑龙江大数据产业发展的智能模型，你将会帮助该公司的员工完成工作。");
        return chatModel.stream(new Prompt(
                List.of(systemMessage,
                        new UserMessage(message))
        ));
    }
    // 测试多模态
    @GetMapping(value = "/test/ai/mutil",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatResponse> test() throws IOException {
        // 多个上下文

        byte[] imageData = new ClassPathResource("/test.png").getContentAsByteArray();

        SystemMessage systemMessage = new SystemMessage("你好，你可以发送图片我来查看图片中有什么内容。");
        AssistantMessage assistantMessage = new AssistantMessage("这张图片展示了一只巴哥犬，它是一种以扁平脸部和紧凑体形闻名的品种。狗的表情是典型的巴哥特征：皱眉、深邃的眼睛和黑色的脸部区域。这种特定类型的巴哥以其短口鼻和大而沉重的身体轮廓而著称。狗的颜色主要是浅米色或奶油色，脸颊上有淡褐色斑块和背部较暗的毛发图案。背景虚化但似乎是户外环境，暗示照片是在自然环境中拍摄的。图片中没有其他物体或主体；焦点完全集中在巴哥犬身上。");

        Flux<ChatResponse> response = chatModel.stream(
                new Prompt(
                        List.of(
                                systemMessage,
                                new UserMessage("你从图片中看到了什么",
                                        List.of(
                                                new Media(MimeTypeUtils.IMAGE_PNG,imageData)
                                        )
                                ),
                                assistantMessage,
                                new UserMessage("他是什么颜色的" )
                        ),
                OllamaOptions.
                        builder()
                        .withModel("minicpm-v")
                        .build()
        ));

        return response;
    }
}
