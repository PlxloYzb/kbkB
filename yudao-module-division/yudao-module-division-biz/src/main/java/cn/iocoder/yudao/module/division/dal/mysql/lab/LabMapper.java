package cn.iocoder.yudao.module.division.dal.mysql.lab;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.division.dal.dataobject.lab.LabDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.division.controller.admin.lab.vo.*;

/**
 * 化验室 Mapper
 *
 * @author PlxloYzb
 */
@Mapper
public interface LabMapper extends BaseMapperX<LabDO> {

    default PageResult<LabDO> selectPage(LabPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LabDO>()
                .likeIfPresent(LabDO::getSampleNumber, reqVO.getSampleNumber())
                .betweenIfPresent(LabDO::getNiGrade, reqVO.getNiGrade())
                .betweenIfPresent(LabDO::getCoGrade, reqVO.getCoGrade())
                .betweenIfPresent(LabDO::getFeGrade, reqVO.getFeGrade())
                .betweenIfPresent(LabDO::getMnGrade, reqVO.getMnGrade())
                .betweenIfPresent(LabDO::getMgGrade, reqVO.getMgGrade())
                .betweenIfPresent(LabDO::getAlGrade, reqVO.getAlGrade())
                .betweenIfPresent(LabDO::getScGrade, reqVO.getScGrade())
                .betweenIfPresent(LabDO::getCrGrade, reqVO.getCrGrade())
                .betweenIfPresent(LabDO::getZnGrade, reqVO.getZnGrade())
                .betweenIfPresent(LabDO::getCaGrade, reqVO.getCaGrade())
                .betweenIfPresent(LabDO::getSio2Grade, reqVO.getSio2Grade())
                .betweenIfPresent(LabDO::getFe2PlusGrade, reqVO.getFe2PlusGrade())
                .betweenIfPresent(LabDO::getCr6PlusGrade, reqVO.getCr6PlusGrade())
                .betweenIfPresent(LabDO::getMoistureGrade, reqVO.getMoistureGrade())
                .betweenIfPresent(LabDO::getTfeGrade, reqVO.getTfeGrade())
                .betweenIfPresent(LabDO::getCr2o3Grade, reqVO.getCr2o3Grade())
                .betweenIfPresent(LabDO::getFeoGrade, reqVO.getFeoGrade())
                .betweenIfPresent(LabDO::getMgoGrade, reqVO.getMgoGrade())
                .betweenIfPresent(LabDO::getCrFeRatio, reqVO.getCrFeRatio())
                .betweenIfPresent(LabDO::getCr2o3FeoRatio, reqVO.getCr2o3FeoRatio())
                .betweenIfPresent(LabDO::getSlurryConcentration, reqVO.getSlurryConcentration())
                .betweenIfPresent(LabDO::getSlurryDensity, reqVO.getSlurryDensity())
                .betweenIfPresent(LabDO::getSamplingTime, reqVO.getSamplingTime())

                .betweenIfPresent(LabDO::getMu18, reqVO.getMu18())
                .betweenIfPresent(LabDO::getMu60, reqVO.getMu60())
                .betweenIfPresent(LabDO::getMu80, reqVO.getMu80())
                .betweenIfPresent(LabDO::getMu100, reqVO.getMu100())
                .betweenIfPresent(LabDO::getDepth, reqVO.getDepth())
                .likeIfPresent(LabDO::getLithology, reqVO.getLithology())

                .likeIfPresent(LabDO::getOperator, reqVO.getOperator())
                .likeIfPresent(LabDO::getReviewer, reqVO.getReviewer())
                .eqIfPresent(LabDO::getReviewNote, reqVO.getReviewNote())
                .betweenIfPresent(LabDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LabDO::getId));
    }

}