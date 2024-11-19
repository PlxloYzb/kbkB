package cn.iocoder.yudao.module.division.service.importfile;

import cn.iocoder.yudao.module.division.dal.dataobject.importfile.ImportFileDO;
import cn.iocoder.yudao.module.division.dal.mysql.importfile.ImportFileMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ImportFileServiceImpl implements ImportFileService {

    @Resource
    private ImportFileMapper importFileMapper;

    @Override
    public Long saveImportFile(ImportFileDO importFile) {
        importFileMapper.insert(importFile);
        return importFile.getId();
    }

    @Override
    public ImportFileDO getImportFile(Long id) {
        return importFileMapper.selectById(id);
    }

}