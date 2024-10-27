package net.kegui.server.ai.dto;

import java.util.List;

public class MessageDto {
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    // 消息的文字
    private Content content;
    // role  分为system user 和assistant
    private String role;



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
