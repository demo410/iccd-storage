package com.iccd.storage.server.common.event.log;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ErrorLogEvent extends ApplicationEvent {

    private String msg;

    private Long userId;

    public ErrorLogEvent(Object source, String msg, Long userId ) {
        super(source);
        this.msg = msg;
        this.userId = userId;
    }
}
