package net.kegui.server.ai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

@Data
public class MessageEntity {

	@JsonPropertyDescription("如果没有使用function,这里则是正常的响应消息,可以是markdown格式的")
	private String message;

	@JsonPropertyDescription("是否使用了function call")
	private Boolean isUseFunctionCall;

	@JsonPropertyDescription("如果使用了function 这里则是名字")
	private String useFunction;

	@JsonPropertyDescription("如果使用了function 这里是运行完function后function的返回结果")
	private String functionResponse;

}
