package net.kegui.server.ai.dto;

public class Content {

	// 消息的文字
	private String content;

	// 类型
	private String type;

	// 图片资源等地址
	private String file;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
