package com.corundumstudio.socketio.spring.boot.handler;

public class MessageInfo {
	
	private String from;
    private String to;
    private String content;
     
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
     
    @Override
    public String toString() {
        return "Msg [from=" + from + ", to=" + to + ", content=" + content + "]";
    }
    
}