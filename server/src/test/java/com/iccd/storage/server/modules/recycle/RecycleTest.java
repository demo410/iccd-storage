package com.iccd.storage.server.modules.recycle;

import cn.hutool.core.lang.Assert;
import com.iccd.storage.server.StorageServerLauncher;
import com.iccd.storage.server.modules.file.context.CreateFolderContext;
import com.iccd.storage.server.modules.file.context.DeleteFileContext;
import com.iccd.storage.server.modules.file.service.IUserFileService;
import com.iccd.storage.server.modules.file.vo.UserFileVO;
import com.iccd.storage.server.modules.recycle.context.QueryRecycleFileListContext;
import com.iccd.storage.server.modules.recycle.context.RestoreContext;
import com.iccd.storage.server.modules.recycle.service.IRecycleService;
import com.iccd.storage.server.modules.user.context.UserRegisterContext;
import com.iccd.storage.server.modules.user.service.IUserService;
import com.iccd.storage.server.modules.user.vo.UserInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections.CollectionUtils;
import com.google.common.collect.Lists;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StorageServerLauncher.class)
@Transactional
public class RecycleTest {


    @Autowired
    private IUserFileService iUserFileService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IRecycleService iRecycleService;

    /**
     * 测试查询回收站文件列表成功
     */
    @Test
    public void testQueryRecyclesSuccess() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        // 创建一个文件夹
        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name-old");

        Long fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        // 删掉该文件夹
        DeleteFileContext deleteFileContext = new DeleteFileContext();
        List<Long> fileIdList = Lists.newArrayList();
        fileIdList.add(fileId);
        deleteFileContext.setFileIdList(fileIdList);
        deleteFileContext.setUserId(userId);
        iUserFileService.deleteFile(deleteFileContext);

        // 查询回收站列表，校验列表的长度为1
        QueryRecycleFileListContext queryRecycleFileListContext = new QueryRecycleFileListContext();
        queryRecycleFileListContext.setUserId(userId);
        List<UserFileVO> recycles = iRecycleService.recycles(queryRecycleFileListContext);

        Assert.isTrue(CollectionUtils.isNotEmpty(recycles) && recycles.size() == 1);
    }

    /**
     * 测试文件还原成功
     */
    @Test
    public void testFileRestoreSuccess() {

        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        // 创建一个文件夹
        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name-old");

        Long fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        // 删掉该文件夹
        DeleteFileContext deleteFileContext = new DeleteFileContext();
        List<Long> fileIdList = Lists.newArrayList();
        fileIdList.add(fileId);
        deleteFileContext.setFileIdList(fileIdList);
        deleteFileContext.setUserId(userId);
        iUserFileService.deleteFile(deleteFileContext);

        // 文件还原
        RestoreContext restoreContext = new RestoreContext();
        restoreContext.setUserId(userId);
        restoreContext.setFileIdList(Lists.newArrayList(fileId));
        iRecycleService.restore(restoreContext);
    }

    /**
     * 测试文件还原失败-错误的用户ID
     */
    @Test
    public void testFileRestoreFailByWrongUserId() {

        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        // 创建一个文件夹
        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name-old");

        Long fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        // 删掉该文件夹
        DeleteFileContext deleteFileContext = new DeleteFileContext();
        List<Long> fileIdList = Lists.newArrayList();
        fileIdList.add(fileId);
        deleteFileContext.setFileIdList(fileIdList);
        deleteFileContext.setUserId(userId);
        iUserFileService.deleteFile(deleteFileContext);

        // 文件还原
        RestoreContext restoreContext = new RestoreContext();
        restoreContext.setUserId(userId + 1);
        restoreContext.setFileIdList(Lists.newArrayList(fileId));
        iRecycleService.restore(restoreContext);
    }

    /**
     * 测试文件还原失败-错误的文件名称
     */
    @Test
    public void testFileRestoreFailByWrongFilename1() {

        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        // 创建一个文件夹
        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name-1");

        Long fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        // 删掉该文件夹
        DeleteFileContext deleteFileContext = new DeleteFileContext();
        List<Long> fileIdList = Lists.newArrayList();
        fileIdList.add(fileId);
        deleteFileContext.setFileIdList(fileIdList);
        deleteFileContext.setUserId(userId);
        iUserFileService.deleteFile(deleteFileContext);

        context.setFolderName("folder-name-1");
        fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        // 文件还原
        RestoreContext restoreContext = new RestoreContext();
        restoreContext.setUserId(userId);
        restoreContext.setFileIdList(Lists.newArrayList(fileId));
        iRecycleService.restore(restoreContext);
    }



    /**
     * 用户注册
     *
     * @return 新用户的ID
     */
    private Long register() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register.longValue() > 0L);
        return register;
    }

    /**
     * 查询登录用户的基本信息
     *
     * @param userId
     * @return
     */
    private UserInfoVO info(Long userId) {
        UserInfoVO userInfoVO = iUserService.info(userId);
        Assert.notNull(userInfoVO);
        return userInfoVO;
    }

    private final static String USERNAME = "imooc";
    private final static String PASSWORD = "123456789";
    private final static String QUESTION = "question";
    private final static String ANSWER = "answer";

    /**
     * 构建注册用户上下文信息
     *
     * @return
     */
    private UserRegisterContext createUserRegisterContext() {
        UserRegisterContext context = new UserRegisterContext();
        context.setUsername(USERNAME);
        context.setPassword(PASSWORD);
        context.setQuestion(QUESTION);
        context.setAnswer(ANSWER);
        return context;
    }
}
