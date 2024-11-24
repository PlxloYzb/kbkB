package cn.iocoder.yudao.module.system.service.division;

import cn.iocoder.yudao.module.system.dal.dataobject.division.DivisionLabDO;
import cn.iocoder.yudao.module.system.dal.mysql.division.DivisionLabMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class DivisionLabServiceImpl implements DivisionLabService {

    @Resource
    private DivisionLabMapper divisionLabMapper;

    @Override
    @Cacheable(cacheNames = "divisionLabList", key = "'list'")
    public List<DivisionLabDO> getDivisionLabList() {
        return divisionLabMapper.selectList();
    }

    @Override
    @Cacheable(cacheNames = "divisionLab", key = "#id")
    public Optional<DivisionLabDO> getDivisionLab(Long id) {
        return Optional.ofNullable(divisionLabMapper.selectById(id));
    }
}