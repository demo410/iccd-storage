package com.iccd.storage.server.modules.user;

import cn.hutool.core.lang.Assert;
import com.iccd.storage.server.StorageServerLauncher;
import com.iccd.storage.core.utils.JwtUtil;
import com.iccd.storage.server.modules.user.constants.UserConstants;
import com.iccd.storage.server.modules.user.context.UserLoginContext;
import com.iccd.storage.server.modules.user.context.UserRegisterContext;
import com.iccd.storage.server.modules.user.service.IUserService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StorageServerLauncher.class)
@Transactional
class UserTest {
    private final static String USERNAME = "imooc";
    private final static String PASSWORD = "123456789";
    private final static String QUESTION = "question";
    private final static String ANSWER = "answer";
    @Autowired
    IUserService userService;
    @Test
    void register() {
        UserRegisterContext userRegisterContext = createUserRegisterContext();
        Long register = userService.register(userRegisterContext);

        Assert.isTrue(register > 0L);
    }

    @Test
    void registerWithDuplicateUsername() {
        UserRegisterContext userRegisterContext = createUserRegisterContext();
        Long register = userService.register(userRegisterContext);

        Assert.isTrue(register > 0L);
        userService.register(userRegisterContext);
    }

    @Test
    public void loginSuccess(){
        register();
        UserLoginContext userLoginContext = createUserLoginContext();
        String accessToken = userService.login(userLoginContext);

        Object userId = JwtUtil.analyzeToken(accessToken, UserConstants.LOGIN_USER_ID);
        Assert.isTrue(Objects.equals(userId, userLoginContext.getEntity().getUserId()));
    }

    @Test
    public void wrongUsername(){
        register();
        UserLoginContext userLoginContext = createUserLoginContext();
        userLoginContext.setUsername("");
        String accessToken = userService.login(userLoginContext);

        Object userId = JwtUtil.analyzeToken(accessToken, UserConstants.LOGIN_USER_ID);
        Assert.isTrue(Objects.equals(userId, userLoginContext.getEntity().getUserId()));
    }

    @Test
    public void wrongPassword(){
        register();
        UserLoginContext userLoginContext = createUserLoginContext();
        userLoginContext.setPassword("");
        String accessToken = userService.login(userLoginContext);

        Object userId = JwtUtil.analyzeToken(accessToken, UserConstants.LOGIN_USER_ID);
        Assert.isTrue(Objects.equals(userId, userLoginContext.getEntity().getUserId()));
    }

    @Test
    public void logout(){
        register();
        UserLoginContext userLoginContext = createUserLoginContext();
        String accessToken = userService.login(userLoginContext);

        Object userId = JwtUtil.analyzeToken(accessToken, UserConstants.LOGIN_USER_ID);

        userService.logout((Long) userId);
    }


    private UserRegisterContext createUserRegisterContext() {
        UserRegisterContext context = new UserRegisterContext();
        context.setUsername(USERNAME);
        context.setPassword(PASSWORD);
        context.setQuestion(QUESTION);
        context.setAnswer(ANSWER);
        return context;
    }

    /**
     * 构建用户登录上下文实体
     *
     * @return
     */
    private UserLoginContext createUserLoginContext() {
        UserLoginContext userLoginContext = new UserLoginContext();
        userLoginContext.setUsername(USERNAME);
        userLoginContext.setPassword(PASSWORD);
        return userLoginContext;
    }
}