package com.iccd.storage.server.modules.user.controller;

import com.iccd.storage.server.common.annotation.LoginIgnore;
import com.iccd.storage.server.common.utils.UserIdUtil;
import com.iccd.storage.core.response.R;
import com.iccd.storage.core.utils.IdUtil;
import com.iccd.storage.server.modules.user.context.UserLoginContext;
import com.iccd.storage.server.modules.user.context.UserRegisterContext;
import com.iccd.storage.server.modules.user.converter.UserConverter;
import com.iccd.storage.server.modules.user.po.UserLoginPO;
import com.iccd.storage.server.modules.user.po.UserRegisterPO;
import com.iccd.storage.server.modules.user.service.IUserService;
import com.iccd.storage.server.modules.user.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Api("用户模块")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    UserConverter userConverter;

    @ApiOperation(
            value = "用户注册",
            notes = "该接口提供用户注册功能，保证了接口的幂等性，可以并发调用",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PostMapping("register")
    @LoginIgnore
    public R register(@Validated @RequestBody UserRegisterPO userRegisterPO) {
        UserRegisterContext userRegisterContext = userConverter.userRegisterPO2UserRegisterContext(userRegisterPO);
        Long userId = userService.register(userRegisterContext);
        return R.data(IdUtil.encrypt(userId));
    }

    @ApiOperation(
            value = "用户登录",
            notes = "该接口提供用户登录功能，成功登录后，会返回有时效性的accessToken供后续使用",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PostMapping("login")
    @LoginIgnore
    public R login(@Validated @RequestBody UserLoginPO userLoginPO) {
        UserLoginContext userLoginContext = userConverter.userLoginPO2UserLoginContext(userLoginPO);
        String token = userService.login(userLoginContext);
        return R.data(token);
    }

    @ApiOperation(
            value = "用户登出",
            notes = "该接口提供用户注销登录功能",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PostMapping("logout")
    public R logout() {
        Long userId = UserIdUtil.get();
        userService.logout(userId);

        return R.success();
    }

    @ApiOperation(
            value = "查询登录用户的基本信息",
            notes = "该接口提供了查询登录用户的基本信息的功能",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @GetMapping("/")
    public R<UserInfoVO> info() {
        UserInfoVO userInfoVO = userService.info(UserIdUtil.get());
        return R.data(userInfoVO);
    }
}
