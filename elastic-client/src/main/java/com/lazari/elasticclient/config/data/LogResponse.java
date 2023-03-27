package com.lazari.elasticclient.config.data;

import java.time.LocalDateTime;

public class LogResponse {
    private String message;
    private String level;
    private LocalDateTime time;

    public LogResponse(String message, String level, LocalDateTime time) {
        this.message = message;
        this.level = level;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public String getLevel() {
        return level;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
