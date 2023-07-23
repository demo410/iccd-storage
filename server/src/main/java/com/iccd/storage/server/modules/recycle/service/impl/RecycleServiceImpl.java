package com.iccd.storage.server.modules.recycle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.iccd.storage.core.constants.StorageConstants;
import com.iccd.storage.core.exception.StorageBusinessException;
import com.iccd.storage.server.common.event.file.FilePhysicalDeleteEvent;
import com.iccd.storage.server.common.event.file.FileRestoreEvent;
import com.iccd.storage.server.modules.file.context.QueryFileListContext;
import com.iccd.storage.server.modules.file.entity.UserFile;
import com.iccd.storage.server.modules.file.enums.DelFlagEnum;
import com.iccd.storage.server.modules.file.service.IUserFileService;
import com.iccd.storage.server.modules.file.vo.UserFileVO;
import com.iccd.storage.server.modules.recycle.context.DeleteContext;
import com.iccd.storage.server.modules.recycle.context.QueryRecycleFileListContext;
import com.iccd.storage.server.modules.recycle.context.RestoreContext;
import com.iccd.storage.server.modules.recycle.service.IRecycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecycleServiceImpl implements IRecycleService {
    @Autowired
    IUserFileService iUserFileService;

    @Override
    public List<UserFileVO> recycles(QueryRecycleFileListContext context) {
        QueryFileListContext queryFileListContext = new QueryFileListContext();

        queryFileListContext.setUserId(context.getUserId());
        queryFileListContext.setDelFlag(DelFlagEnum.YES.getCode());
        return iUserFileService.getFileList(queryFileListContext);
    }

    /**
     * 文件还原
     * <p>
     * 1、检查操作权限
     * 2、检查是不是可以还原
     * 3、执行文件还原的操作
     * 4、执行文件还原的后置操作
     *
     * @param context
     */
    @Override
    public void restore(RestoreContext context) {
        checkRestorePermission(context);
        checkRestoreFilename(context);
        doRestore(context);
        afterRestore(context);
    }

    @Override
    public void delete(DeleteContext context) {
        checkFileDeletePermission(context);
        findAllFileRecords(context);
        doDelete(context);
        afterDelete(context);
    }

    private void afterDelete(DeleteContext context) {
//        FilePhysicalDeleteEvent event = new FilePhysicalDeleteEvent(context.getAllRecords());
    }

    /**
     * 执行文件删除的动作
     *
     * @param context
     */
    private void doDelete(DeleteContext context) {
        List<UserFile> allRecords = context.getAllRecords();
        List<Long> fileIdList = allRecords.stream().map(UserFile::getFileId).collect(Collectors.toList());
        if (!iUserFileService.removeByIds(fileIdList)) {
            throw new StorageBusinessException("文件删除失败");
        }
    }

    /**
     * 递归查询所有的子文件
     *
     * @param context
     */
    private void findAllFileRecords(DeleteContext context) {
        List<UserFile> records = context.getRecords();
        List<UserFile> allRecords = iUserFileService.findAllFileRecords(records);
        context.setAllRecords(allRecords);
    }

    /**
     * 校验文件删除的操作权限
     *
     * @param context
     */
    private void checkFileDeletePermission(DeleteContext context) {
        QueryWrapper queryWrapper = Wrappers.query();
        queryWrapper.eq("user_id", context.getUserId());
        queryWrapper.in("file_id", context.getFileIdList());
        List<UserFile> records = iUserFileService.list(queryWrapper);
        if (CollectionUtils.isEmpty(records) || records.size() != context.getFileIdList().size()) {
            throw new StorageBusinessException("您无权删除该文件");
        }
        context.setRecords(records);
    }



    private void afterRestore(RestoreContext context) {
        FileRestoreEvent event = new FileRestoreEvent(this, context.getFileIdList());
//        publish(event)
    }

    private void doRestore(RestoreContext context) {
        List<UserFile> records = context.getRecords();
        records.stream().forEach(record -> {
            record.setDelFlag(DelFlagEnum.NO.getCode());
            record.setUpdateUser(context.getUserId());
            record.setUpdateTime(new Date());
        });

        boolean updateFlag = iUserFileService.updateBatchById(records);
        if (!updateFlag) {
            throw new StorageBusinessException("文件还原失败");
        }
    }

    /**
     * 检查要还原的文件是否被占用
     * 1、要还原的文件列表中有同一个文件夹下面相同名称的文件 不允许还原
     * 2、要还原的文件当前的父文件夹下面存在同名文件，我们不允许还原
     *
     * @param context
     */
    private void checkRestoreFilename(RestoreContext context) {
        List<UserFile> records = context.getRecords();
        Set<String> filenameSet = records.stream()
                .map(record -> record.getFilename() + StorageConstants.COMMON_SEPARATOR + record.getParentId())
                .collect(Collectors.toSet());

        if (filenameSet.size() != records.size()) {
            throw new StorageBusinessException("文件还原失败，该还原文件中存在同名文件，请逐个还原并重命名");
        }

        for (UserFile record : records) {
            QueryWrapper queryWrapper = Wrappers.query();
            queryWrapper.eq("user_id", context.getUserId());
            queryWrapper.eq("parent_id", record.getParentId());
            queryWrapper.eq("filename", record.getFilename());
            queryWrapper.eq("del_flag", DelFlagEnum.NO.getCode());

            if (iUserFileService.count(queryWrapper) > 0) {
                throw new StorageBusinessException("文件: " + record.getFilename() +
                        " 还原失败，该文件夹下面已经存在了相同名称的文件或者文件夹，请重命名之后再执行文件还原操作");
            }
        }
    }

    private void checkRestorePermission(RestoreContext context) {
        List<Long> fileIdList = context.getFileIdList();
        List<UserFile> records = iUserFileService.listByIds(fileIdList);
        if (CollectionUtils.isEmpty(records)) {
            throw new StorageBusinessException("文件还原失败");
        }
        List<UserFile> legalRecords = records.stream()
                .filter(userFile -> Objects.equals(userFile.getUserId(), context.getUserId()))
                .collect(Collectors.toList());

        if (legalRecords.size() != records.size()) {
            throw new StorageBusinessException("您无权执行文件还原");
        }

        context.setRecords(legalRecords);
    }
}
