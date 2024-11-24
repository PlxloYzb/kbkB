package cn.iocoder.yudao.module.system.service.division;

import cn.iocoder.yudao.module.system.dal.dataobject.division.DivisionLabDO;

import java.util.List;
import java.util.Optional;

public interface DivisionLabService {

    /**
     * 获得 DivisionLab 列表
     *
     * @return DivisionLab 列表
     */
    List<DivisionLabDO> getDivisionLabList();

    /**
     * 获得 DivisionLab 详情
     *
     * @param id 编号
     * @return DivisionLab 详情
     */
    Optional<DivisionLabDO> getDivisionLab(Long id);
}