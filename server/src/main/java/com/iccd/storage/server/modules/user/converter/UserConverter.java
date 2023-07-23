package com.iccd.storage.server.modules.user.converter;

import com.iccd.storage.server.modules.file.entity.UserFile;
import com.iccd.storage.server.modules.user.context.UserLoginContext;
import com.iccd.storage.server.modules.user.context.UserRegisterContext;
import com.iccd.storage.server.modules.user.entity.User;
import com.iccd.storage.server.modules.user.po.UserLoginPO;
import com.iccd.storage.server.modules.user.po.UserRegisterPO;
import com.iccd.storage.server.modules.user.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 用户模块实体转换工具类
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    UserRegisterContext userRegisterPO2UserRegisterContext(UserRegisterPO userRegisterPO);

    @Mapping(target = "password", ignore = true)
    User userRegisterContext2User(UserRegisterContext userRegisterContext);


    UserLoginContext userLoginPO2UserLoginContext(UserLoginPO userLoginPO);

    /**
     * 拼装用户基本信息返回实体
     *
     * @param rPanUser
     * @param rPanUserFile
     * @return
     */
    @Mapping(source = "rPanUser.username", target = "username")
    @Mapping(source = "rPanUserFile.fileId", target = "rootFileId")
    @Mapping(source = "rPanUserFile.filename", target = "rootFilename")
    UserInfoVO assembleUserInfoVO(User rPanUser, UserFile rPanUserFile);
}
