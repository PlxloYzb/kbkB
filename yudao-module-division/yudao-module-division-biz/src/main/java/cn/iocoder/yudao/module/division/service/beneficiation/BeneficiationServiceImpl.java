package cn.iocoder.yudao.module.division.service.beneficiation;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.division.controller.admin.beneficiation.vo.*;
import cn.iocoder.yudao.module.division.dal.dataobject.beneficiation.BeneficiationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.division.dal.mysql.beneficiation.BeneficiationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.division.enums.ErrorCodeConstants.*;

/**
 * 选矿每日数据 Service 实现类
 *
 * @author PlxloYzb
 */
@Service
@Validated
public class BeneficiationServiceImpl implements BeneficiationService {

    @Resource
    private BeneficiationMapper beneficiationMapper;

    @Override
    public Long createBeneficiation(BeneficiationSaveReqVO createReqVO) {
        // 插入
        BeneficiationDO beneficiation = BeanUtils.toBean(createReqVO, BeneficiationDO.class);
        beneficiationMapper.insert(beneficiation);
        // 返回
        return beneficiation.getId();
    }

    @Override
    public void updateBeneficiation(BeneficiationSaveReqVO updateReqVO) {
        // 校验存在
        validateBeneficiationExists(updateReqVO.getId());
        // 更新
        BeneficiationDO updateObj = BeanUtils.toBean(updateReqVO, BeneficiationDO.class);
        beneficiationMapper.updateById(updateObj);
    }

    @Override
    public void deleteBeneficiation(Long id) {
        // 校验存在
        validateBeneficiationExists(id);
        // 删除
        beneficiationMapper.deleteById(id);
    }

    private void validateBeneficiationExists(Long id) {
        if (beneficiationMapper.selectById(id) == null) {
            throw exception(BENEFICIATION_NOT_EXISTS);
        }
    }

    @Override
    public BeneficiationDO getBeneficiation(Long id) {
        return beneficiationMapper.selectById(id);
    }

    @Override
    public PageResult<BeneficiationDO> getBeneficiationPage(BeneficiationPageReqVO pageReqVO) {
        return beneficiationMapper.selectPage(pageReqVO);
    }

}