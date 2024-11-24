package cn.iocoder.yudao.module.system.dal.dataobject.division;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@TableName("division_lab")
@Data
@EqualsAndHashCode(callSuper = true)
public class DivisionLabDO extends BaseDO {

    /** 主键ID */
    @TableId
    private Long id;

    /** 样本编号 */
    private String sampleNumber;

    /** 泥浆浓度 */
    private Double slurryConcentration;

    /** 泥浆密度 */
    private Double slurryDensity;

    /** 采样时间 */
    private LocalDateTime samplingTime;

    /** 操作员 */
    private String operator;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 镍等级 */
    private Double niGrade;

    /** 钴等级 */
    private Double coGrade;

    /** 铁等级 */
    private Double feGrade;

    /** 锰等级 */
    private Double mnGrade;

    /** 镁等级 */
    private Double mgGrade;

    /** 铝等级 */
    private Double alGrade;

    /** 钪等级 */
    private Double scGrade;

    /** 铒等级 */
    private Double crGrade;

    /** 锌等级 */
    private Double znGrade;

    /** 钙等级 */
    private Double caGrade;

    /** 二氧化硅等级 */
    private Double sio2Grade;

    /** 二价铁等级 */
    private Double fe2PlusGrade;

    /** 铬等级 */
    private Double cr6PlusGrade;

    /** 水分等级 */
    private Double moistureGrade;

    /** 总铁等级 */
    private Double tfeGrade;

    /** 三氧化二铬等级 */
    private Double cr2o3Grade;

    /** 氧化亚铁等级 */
    private Double feoGrade;

    /** 氧化镁等级 */
    private Double mgoGrade;

    /** 铬铁比 */
    private Double crFeRatio;

    /** 三氧化二铬与氧化亚铁比 */
    private Double cr2o3FeoRatio;
}