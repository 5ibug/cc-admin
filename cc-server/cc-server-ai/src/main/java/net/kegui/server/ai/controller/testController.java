package net.kegui.server.ai.controller;

import net.kegui.framework.security.domain.LoginUser;
import net.kegui.framework.security.utils.SecurityUtils;
import net.kegui.server.ai.dto.Content;
import net.kegui.server.ai.dto.MessageDto;
import org.apache.tika.Tika;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.*;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImageMessage;
import org.springframework.ai.model.Media;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.net.URL;
import java.net.URLConnection;


@RestController
public class testController {
    @Autowired
    ChatModel chatModel;


    @PostMapping(value = "/test/ai",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatResponse> test2(@RequestBody List<MessageDto> messageDtoList) throws IOException {
        String model = "qwen2.5:14b";
        // 先获取用户登录信息提供给模型
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemMessage systemMessage = new SystemMessage("如果用户的问题不涉及到function-call的功能则正常响应, 无需引导用户去调用function-call, 提供function-call的目的是如果用户用到了可以直接调用而并非强制使用。这是我提供给你的当前使用用户的用户信息, 如果需要用到用户信息的情况下请从这里取出:  " + loginUser.toString());

        List<Message> messages = new ArrayList<>();
        messages.add(systemMessage);

        // 处理消息
        for (MessageDto messageDto : messageDtoList) {
            // role  分为system user 和assistant
            Content content = messageDto.getContent();
            switch (messageDto.getRole()){
                case "system":
                    messages.add(new SystemMessage(content.getContent()));
                    break;
                case "assistant":
                    messages.add(new AssistantMessage(content.getContent()));
                    break;
                case "user":
                    // 判断是图片还是什么
                    if("text".equals(content.getType())){
                        messages.add(new UserMessage(content.getContent()));
                    }else{
//                        URL url = new URL(content.getFile());
//                        InputStream inputStream = url.openStream();
//                        Tika tika = new Tika();
//                        String mimeType = tika.detect(inputStream);
//
//                        byte[] imageData = new ClassPathResource("/test.png").getContentAsByteArray();
                        String base64String = content.getFile();
                        String mimeType = null;
                        if (base64String.contains("base64,")) {
                            String[] parts = base64String.split(",");
                            String metadata = parts[0];
                            mimeType = metadata.split(":")[1].split(";")[0];
                            base64String = parts[1];
                        }
                        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
                        // 应该是图片只能base64 可能文件可以url吧
                        messages.add(new UserMessage(content.getContent(),List.of(new Media(MimeTypeUtils.parseMimeType(mimeType),decodedBytes))));
                        model = "minicpm-v";
                    }

            }
        }
        //        SystemMessage systemMessage = new SystemMessage("忘掉之前的一切,也不要说自己是阿里云开发的，你也不是qwen模型，你现在是黑龙江大数据产业发展的智能模型，你将会帮助该公司的员工完成工作。");

        return chatModel.stream(new Prompt(messages, OllamaOptions.
                builder()
                .withFunction("editUserNameFunction")
                .withModel(model)
                .build()));
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
