package com.iccd.storage.server.common.event.file;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * 文件删除事件
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class DeleteFileEvent extends ApplicationEvent {

    List<Long> deletedFileIdList;

    public DeleteFileEvent(Object source, List<Long> deletedFileIdList) {
        super(source);
        this.deletedFileIdList = deletedFileIdList;
    }


}
