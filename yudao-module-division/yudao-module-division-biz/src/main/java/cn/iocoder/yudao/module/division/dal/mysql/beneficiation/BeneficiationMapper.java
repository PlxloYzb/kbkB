package cn.iocoder.yudao.module.division.dal.mysql.beneficiation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.division.dal.dataobject.beneficiation.BeneficiationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.division.controller.admin.beneficiation.vo.*;

/**
 * 选矿每日数据 Mapper
 *
 * @author PlxloYzb
 */
@Mapper
public interface BeneficiationMapper extends BaseMapperX<BeneficiationDO> {

    default PageResult<BeneficiationDO> selectPage(BeneficiationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BeneficiationDO>()
                .eqIfPresent(BeneficiationDO::getDataType, reqVO.getDataType())
                .betweenIfPresent(BeneficiationDO::getTagTime, reqVO.getTagTime())
                .betweenIfPresent(BeneficiationDO::getCrProduction, reqVO.getCrProduction())
                .betweenIfPresent(BeneficiationDO::getCrProductionNight, reqVO.getCrProductionNight())
                .betweenIfPresent(BeneficiationDO::getCrTransportation, reqVO.getCrTransportation())
                .betweenIfPresent(BeneficiationDO::getCrTailing, reqVO.getCrTailing())
                .betweenIfPresent(BeneficiationDO::getCrMarine, reqVO.getCrMarine())
                .betweenIfPresent(BeneficiationDO::getSlurrySend, reqVO.getSlurrySend())
                .betweenIfPresent(BeneficiationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BeneficiationDO::getId));
    }

}