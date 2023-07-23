package com.iccd.storage.server.modules.file.convert;

import com.iccd.storage.engine.core.context.StoreFileChunkContext;
import com.iccd.storage.server.modules.file.context.CreateFolderContext;
import com.iccd.storage.server.modules.file.context.DeleteFileContext;
import com.iccd.storage.server.modules.file.context.FileChunkMergeAndSaveContext;
import com.iccd.storage.server.modules.file.context.FileChunkMergeContext;
import com.iccd.storage.server.modules.file.context.FileChunkSaveContext;
import com.iccd.storage.server.modules.file.context.FileChunkUploadContext;
import com.iccd.storage.server.modules.file.context.FileSaveContext;
import com.iccd.storage.server.modules.file.context.FileUploadContext;
import com.iccd.storage.server.modules.file.context.QueryUploadedChunksContext;
import com.iccd.storage.server.modules.file.context.SecUploadFileContext;
import com.iccd.storage.server.modules.file.context.UpdateFilenameContext;
import com.iccd.storage.server.modules.file.entity.UserFile;
import com.iccd.storage.server.modules.file.po.CreateFolderPO;
import com.iccd.storage.server.modules.file.po.DeleteFilePO;
import com.iccd.storage.server.modules.file.po.FileChunkMergePO;
import com.iccd.storage.server.modules.file.po.FileChunkUploadPO;
import com.iccd.storage.server.modules.file.po.FileUploadPO;
import com.iccd.storage.server.modules.file.po.QueryUploadedChunksPO;
import com.iccd.storage.server.modules.file.po.SecUploadFilePO;
import com.iccd.storage.server.modules.file.po.UpdateFilenamePO;
import com.iccd.storage.server.modules.file.vo.FolderTreeNodeVO;
import com.iccd.storage.server.modules.file.vo.UserFileVO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-21T17:58:04+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class FileConverterImpl implements FileConverter {

    @Override
    public CreateFolderContext createFolderPO2CreateFolderContext(CreateFolderPO createFolderPO) {
        if ( createFolderPO == null ) {
            return null;
        }

        CreateFolderContext createFolderContext = new CreateFolderContext();

        createFolderContext.setFolderName( createFolderPO.getFolderName() );

        createFolderContext.setParentId( com.iccd.storage.core.utils.IdUtil.decrypt(createFolderPO.getParentId()) );
        createFolderContext.setUserId( com.iccd.storage.server.common.utils.UserIdUtil.get() );

        return createFolderContext;
    }

    @Override
    public UpdateFilenameContext updateFilenamePO2UpdateFilenameContext(UpdateFilenamePO updateFilenamePO) {
        if ( updateFilenamePO == null ) {
            return null;
        }

        UpdateFilenameContext updateFilenameContext = new UpdateFilenameContext();

        updateFilenameContext.setNewFilename( updateFilenamePO.getNewFilename() );

        updateFilenameContext.setFileId( com.iccd.storage.core.utils.IdUtil.decrypt(updateFilenamePO.getFileId()) );
        updateFilenameContext.setUserId( com.iccd.storage.server.common.utils.UserIdUtil.get() );

        return updateFilenameContext;
    }

    @Override
    public DeleteFileContext deleteFilePO2DeleteFileContext(DeleteFilePO deleteFilePO) {
        if ( deleteFilePO == null ) {
            return null;
        }

        DeleteFileContext deleteFileContext = new DeleteFileContext();

        deleteFileContext.setUserId( com.iccd.storage.server.common.utils.UserIdUtil.get() );

        return deleteFileContext;
    }

    @Override
    public SecUploadFileContext secUploadFilePO2SecUploadFileContext(SecUploadFilePO secUploadFilePO) {
        if ( secUploadFilePO == null ) {
            return null;
        }

        SecUploadFileContext secUploadFileContext = new SecUploadFileContext();

        secUploadFileContext.setFilename( secUploadFilePO.getFilename() );
        secUploadFileContext.setIdentifier( secUploadFilePO.getIdentifier() );

        secUploadFileContext.setParentId( com.iccd.storage.core.utils.IdUtil.decrypt(secUploadFilePO.getParentId()) );
        secUploadFileContext.setUserId( com.iccd.storage.server.common.utils.UserIdUtil.get() );

        return secUploadFileContext;
    }

    @Override
    public FileUploadContext fileUploadPO2FileUploadContext(FileUploadPO fileUploadPO) {
        if ( fileUploadPO == null ) {
            return null;
        }

        FileUploadContext fileUploadContext = new FileUploadContext();

        fileUploadContext.setFilename( fileUploadPO.getFilename() );
        fileUploadContext.setIdentifier( fileUploadPO.getIdentifier() );
        fileUploadContext.setTotalSize( fileUploadPO.getTotalSize() );
        fileUploadContext.setFile( fileUploadPO.getFile() );

        fileUploadContext.setParentId( com.iccd.storage.core.utils.IdUtil.decrypt(fileUploadPO.getParentId()) );
        fileUploadContext.setUserId( com.iccd.storage.server.common.utils.UserIdUtil.get() );

        return fileUploadContext;
    }

    @Override
    public FileSaveContext fileUploadContext2FileSaveContext(FileUploadContext context) {
        if ( context == null ) {
            return null;
        }

        FileSaveContext fileSaveContext = new FileSaveContext();

        fileSaveContext.setFilename( context.getFilename() );
        fileSaveContext.setIdentifier( context.getIdentifier() );
        fileSaveContext.setTotalSize( context.getTotalSize() );
        fileSaveContext.setFile( context.getFile() );
        fileSaveContext.setUserId( context.getUserId() );

        return fileSaveContext;
    }

    @Override
    public FileChunkUploadContext fileChunkUploadPO2FileChunkUploadContext(FileChunkUploadPO fileChunkUploadPO) {
        if ( fileChunkUploadPO == null ) {
            return null;
        }

        FileChunkUploadContext fileChunkUploadContext = new FileChunkUploadContext();

        fileChunkUploadContext.setFilename( fileChunkUploadPO.getFilename() );
        fileChunkUploadContext.setIdentifier( fileChunkUploadPO.getIdentifier() );
        fileChunkUploadContext.setTotalChunks( fileChunkUploadPO.getTotalChunks() );
        fileChunkUploadContext.setChunkNumber( fileChunkUploadPO.getChunkNumber() );
        fileChunkUploadContext.setCurrentChunkSize( fileChunkUploadPO.getCurrentChunkSize() );
        fileChunkUploadContext.setTotalSize( fileChunkUploadPO.getTotalSize() );
        fileChunkUploadContext.setFile( fileChunkUploadPO.getFile() );

        fileChunkUploadContext.setUserId( com.iccd.storage.server.common.utils.UserIdUtil.get() );

        return fileChunkUploadContext;
    }

    @Override
    public FileChunkSaveContext fileChunkUploadContext2FileChunkSaveContext(FileChunkUploadContext context) {
        if ( context == null ) {
            return null;
        }

        FileChunkSaveContext fileChunkSaveContext = new FileChunkSaveContext();

        fileChunkSaveContext.setFilename( context.getFilename() );
        fileChunkSaveContext.setIdentifier( context.getIdentifier() );
        fileChunkSaveContext.setTotalChunks( context.getTotalChunks() );
        fileChunkSaveContext.setChunkNumber( context.getChunkNumber() );
        fileChunkSaveContext.setCurrentChunkSize( context.getCurrentChunkSize() );
        fileChunkSaveContext.setTotalSize( context.getTotalSize() );
        fileChunkSaveContext.setFile( context.getFile() );
        fileChunkSaveContext.setUserId( context.getUserId() );

        return fileChunkSaveContext;
    }

    @Override
    public StoreFileChunkContext fileChunkSaveContext2StoreFileChunkContext(FileChunkSaveContext context) {
        if ( context == null ) {
            return null;
        }

        StoreFileChunkContext storeFileChunkContext = new StoreFileChunkContext();

        storeFileChunkContext.setFilename( context.getFilename() );
        storeFileChunkContext.setIdentifier( context.getIdentifier() );
        storeFileChunkContext.setTotalSize( context.getTotalSize() );
        storeFileChunkContext.setTotalChunks( context.getTotalChunks() );
        storeFileChunkContext.setChunkNumber( context.getChunkNumber() );
        storeFileChunkContext.setCurrentChunkSize( context.getCurrentChunkSize() );
        storeFileChunkContext.setUserId( context.getUserId() );

        return storeFileChunkContext;
    }

    @Override
    public QueryUploadedChunksContext queryUploadedChunksPO2QueryUploadedChunksContext(QueryUploadedChunksPO queryUploadedChunksPO) {
        if ( queryUploadedChunksPO == null ) {
            return null;
        }

        QueryUploadedChunksContext queryUploadedChunksContext = new QueryUploadedChunksContext();

        queryUploadedChunksContext.setIdentifier( queryUploadedChunksPO.getIdentifier() );

        queryUploadedChunksContext.setUserId( com.iccd.storage.server.common.utils.UserIdUtil.get() );

        return queryUploadedChunksContext;
    }

    @Override
    public FileChunkMergeContext fileChunkMergePO2FileChunkMergeContext(FileChunkMergePO fileChunkMergePO) {
        if ( fileChunkMergePO == null ) {
            return null;
        }

        FileChunkMergeContext fileChunkMergeContext = new FileChunkMergeContext();

        fileChunkMergeContext.setFilename( fileChunkMergePO.getFilename() );
        fileChunkMergeContext.setIdentifier( fileChunkMergePO.getIdentifier() );
        fileChunkMergeContext.setTotalSize( fileChunkMergePO.getTotalSize() );

        fileChunkMergeContext.setParentId( com.iccd.storage.core.utils.IdUtil.decrypt(fileChunkMergePO.getParentId()) );
        fileChunkMergeContext.setUserId( com.iccd.storage.server.common.utils.UserIdUtil.get() );

        return fileChunkMergeContext;
    }

    @Override
    public FileChunkMergeAndSaveContext fileChunkMergeContext2FileChunkMergeAndSaveContext(FileChunkMergeContext context) {
        if ( context == null ) {
            return null;
        }

        FileChunkMergeAndSaveContext fileChunkMergeAndSaveContext = new FileChunkMergeAndSaveContext();

        fileChunkMergeAndSaveContext.setFilename( context.getFilename() );
        fileChunkMergeAndSaveContext.setIdentifier( context.getIdentifier() );
        fileChunkMergeAndSaveContext.setTotalSize( context.getTotalSize() );
        fileChunkMergeAndSaveContext.setParentId( context.getParentId() );
        fileChunkMergeAndSaveContext.setUserId( context.getUserId() );
        fileChunkMergeAndSaveContext.setRecord( context.getRecord() );

        return fileChunkMergeAndSaveContext;
    }

    @Override
    public FolderTreeNodeVO userFile2FolderTreeNodeVO(UserFile userFile) {
        if ( userFile == null ) {
            return null;
        }

        FolderTreeNodeVO folderTreeNodeVO = new FolderTreeNodeVO();

        folderTreeNodeVO.setLabel( userFile.getFilename() );
        folderTreeNodeVO.setId( userFile.getFileId() );
        folderTreeNodeVO.setParentId( userFile.getParentId() );

        folderTreeNodeVO.setChildren( com.google.common.collect.Lists.newArrayList() );

        return folderTreeNodeVO;
    }

    @Override
    public UserFileVO userFile2UserFileVO(UserFile record) {
        if ( record == null ) {
            return null;
        }

        UserFileVO userFileVO = new UserFileVO();

        userFileVO.setFileId( record.getFileId() );
        userFileVO.setParentId( record.getParentId() );
        userFileVO.setFilename( record.getFilename() );
        userFileVO.setFileSizeDesc( record.getFileSizeDesc() );
        userFileVO.setFolderFlag( record.getFolderFlag() );
        userFileVO.setFileType( record.getFileType() );
        userFileVO.setUpdateTime( record.getUpdateTime() );

        return userFileVO;
    }
}
