package cn.iocoder.yudao.module.division.service.beneficiation;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.division.controller.admin.beneficiation.vo.*;
import cn.iocoder.yudao.module.division.dal.dataobject.beneficiation.BeneficiationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 选矿每日数据 Service 接口
 *
 * @author PlxloYzb
 */
public interface BeneficiationService {

    /**
     * 创建选矿每日数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBeneficiation(@Valid BeneficiationSaveReqVO createReqVO);

    /**
     * 更新选矿每日数据
     *
     * @param updateReqVO 更新信息
     */
    void updateBeneficiation(@Valid BeneficiationSaveReqVO updateReqVO);

    /**
     * 删除选矿每日数据
     *
     * @param id 编号
     */
    void deleteBeneficiation(Long id);

    /**
     * 获得选矿每日数据
     *
     * @param id 编号
     * @return 选矿每日数据
     */
    BeneficiationDO getBeneficiation(Long id);

    /**
     * 获得选矿每日数据分页
     *
     * @param pageReqVO 分页查询
     * @return 选矿每日数据分页
     */
    PageResult<BeneficiationDO> getBeneficiationPage(BeneficiationPageReqVO pageReqVO);

}