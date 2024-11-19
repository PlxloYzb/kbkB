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
                .betweenIfPresent(LabDO::getElementAGrade, reqVO.getElementAGrade())
                .betweenIfPresent(LabDO::getElementBGrade, reqVO.getElementBGrade())
                .betweenIfPresent(LabDO::getElementCGrade, reqVO.getElementCGrade())
                .betweenIfPresent(LabDO::getSlurryConcentration, reqVO.getSlurryConcentration())
                .betweenIfPresent(LabDO::getSlurryDensity, reqVO.getSlurryDensity())
                .betweenIfPresent(LabDO::getSamplingTime, reqVO.getSamplingTime())
                .betweenIfPresent(LabDO::getUploadTime, reqVO.getUploadTime())
                .likeIfPresent(LabDO::getOperator, reqVO.getOperator())
                .likeIfPresent(LabDO::getReviewer, reqVO.getReviewer())
                .eqIfPresent(LabDO::getReviewNote, reqVO.getReviewNote())
                .betweenIfPresent(LabDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LabDO::getId));
    }

}