package net.kegui.server.ai.function;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;
import net.kegui.server.ai.entity.MessageEntity;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Description("edit username")
@Service
public class EditUserNameFunction implements Function<EditUserNameFunction.Request, EditUserNameFunction.Response> {



    @Data
    public static class Request {
        @JsonProperty(required = true, value = "newName")
        @JsonPropertyDescription(value = "newName")
        String newName;
    }

    public record Response(String message) {
    }



    @Override
    public Response apply(Request request) {
        return new Response("{\"name\": \"" + request.newName + "\"}");
    }

}
