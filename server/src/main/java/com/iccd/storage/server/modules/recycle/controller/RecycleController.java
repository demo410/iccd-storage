package com.iccd.storage.server.modules.recycle.controller;

import com.google.common.base.Splitter;
import com.iccd.storage.core.constants.StorageConstants;
import com.iccd.storage.core.response.R;
import com.iccd.storage.core.utils.IdUtil;
import com.iccd.storage.server.common.utils.UserIdUtil;
import com.iccd.storage.server.modules.file.vo.UserFileVO;
import com.iccd.storage.server.modules.recycle.context.DeleteContext;
import com.iccd.storage.server.modules.recycle.context.QueryRecycleFileListContext;
import com.iccd.storage.server.modules.recycle.context.RestoreContext;
import com.iccd.storage.server.modules.recycle.po.DeletePO;
import com.iccd.storage.server.modules.recycle.po.RestorePO;
import com.iccd.storage.server.modules.recycle.service.IRecycleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 回收站模块控制器
 */
@RestController
@Api(tags = "回收站模块")
@Validated
public class RecycleController {

    @Autowired
    private IRecycleService iRecycleService;

    @ApiOperation(
            value = "获取回收站文件列表",
            notes = "该接口提供了获取回收站文件列表的功能",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @GetMapping("recycles")
    public R<List<UserFileVO>> recycles() {
        QueryRecycleFileListContext context = new QueryRecycleFileListContext();
        context.setUserId(UserIdUtil.get());
        List<UserFileVO> result = iRecycleService.recycles(context);
        return R.data(result);
    }

    @ApiOperation(
            value = "删除的文件批量还原",
            notes = "该接口提供了删除的文件批量还原的功能",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @PutMapping("recycle/restore")
    public R restore(@Validated @RequestBody RestorePO restorePO) {
        RestoreContext context = new RestoreContext();
        context.setUserId(UserIdUtil.get());

        String fileIds = restorePO.getFileIds();
        List<Long> fileIdList = Splitter.on(StorageConstants.COMMON_SEPARATOR).splitToList(fileIds).stream()
                .map(IdUtil::decrypt).collect(Collectors.toList());
        context.setFileIdList(fileIdList);

        iRecycleService.restore(context);
        return R.success();
    }

    @ApiOperation(
            value = "删除的文件批量彻底删除",
            notes = "该接口提供了删除的文件批量彻底删除的功能",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @DeleteMapping("recycle")
    public R delete(@Validated @RequestBody DeletePO deletePO) {
        DeleteContext context = new DeleteContext();
        context.setUserId(UserIdUtil.get());

        String fileIds = deletePO.getFileIds();
        List<Long> fileIdList = Splitter.on(StorageConstants.COMMON_SEPARATOR).splitToList(fileIds).stream().
                map(IdUtil::decrypt).collect(Collectors.toList());
        context.setFileIdList(fileIdList);

        iRecycleService.delete(context);
        return R.success();
    }

}
