package com.iccd.storage.server.modules.user.context;

import com.iccd.storage.server.modules.user.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录业务上下文对象
 */
@Data
public class UserLoginContext implements Serializable {


    private static final long serialVersionUID = 1618921555255671829L;
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
     * 登录验证token
     */
    private String accessToken;

    /**
     * 用户实体对象
     */
    private User entity;

}
