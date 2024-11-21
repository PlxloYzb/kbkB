package cn.iocoder.yudao.module.division.service.importfile;

import cn.iocoder.yudao.module.division.dal.dataobject.importfile.ImportFileDO;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.division.controller.admin.lab.vo.ImportFilePageReqVO;

import java.util.List;

public interface ImportFileService {

    Long saveImportFile(ImportFileDO importFile);

    ImportFileDO getImportFile(Long id);

    List<ImportFileDO> getImportFilesByName(List<String> fileNames);

    PageResult<ImportFileDO> getImportFilesPage(ImportFilePageReqVO pageReqVO);

}