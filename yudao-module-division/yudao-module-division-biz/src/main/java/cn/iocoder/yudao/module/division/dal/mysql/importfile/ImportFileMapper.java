package cn.iocoder.yudao.module.division.dal.mysql.importfile;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.division.dal.dataobject.importfile.ImportFileDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImportFileMapper extends BaseMapperX<ImportFileDO> {
}