package cn.iocoder.yudao.module.division.service.importfile;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.division.dal.dataobject.importfile.ImportFileDO;
import cn.iocoder.yudao.module.division.dal.mysql.importfile.ImportFileMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.module.division.controller.admin.lab.vo.ImportFilePageReqVO;

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

    @Override
    public List<ImportFileDO> getImportFilesByName(List<String> fileNames) {
        return importFileMapper.selectList(new LambdaQueryWrapperX<ImportFileDO>()
                .inIfPresent(ImportFileDO::getFileName, fileNames));
    }

    @Override
    public PageResult<ImportFileDO> getImportFilesPage(ImportFilePageReqVO pageReqVO) {
        return importFileMapper.selectPage(pageReqVO, new QueryWrapperX<ImportFileDO>()
                .likeIfPresent("file_name", pageReqVO.getFileName())
                .betweenIfPresent("create_time", pageReqVO.getCreateTime()));
    }

}