package cn.iocoder.yudao.module.division.service.importfile;

import cn.iocoder.yudao.module.division.dal.dataobject.importfile.ImportFileDO;

public interface ImportFileService {

    Long saveImportFile(ImportFileDO importFile);

    ImportFileDO getImportFile(Long id);

}