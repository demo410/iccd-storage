package com.iccd.storage.server.modules.user.context;

import com.iccd.storage.server.modules.user.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册业务上下文对象
 */
@Data
public class UserRegisterContext implements Serializable {

    private static final long serialVersionUID = -4835860208501507531L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 密保问题
     */
    private String question;

    /**
     * 密保答案
     */
    private String answer;

    /**
     * 用户实体对象
     */
    private User entity;

}
