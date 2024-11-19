package cn.iocoder.yudao.module.division.dal.dataobject.lab;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 化验室 DO
 *
 * @author PlxloYzb
 */
@TableName("division_lab")
@KeySequence("division_lab_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 样品编号
     */
    private String sampleNumber;
    /**
     * 元素A品位（百分数，保留至三位小数）
     */
    private BigDecimal elementAGrade;
    /**
     * 元素B品位（百分数，保留至三位小数）
     */
    private BigDecimal elementBGrade;
    /**
     * 元素C品位（百分数，保留至三位小数）
     */
    private BigDecimal elementCGrade;
    /**
     * 矿浆浓度（百分数，保留至两位小数）
     */
    private BigDecimal slurryConcentration;
    /**
     * 矿浆密度（保留至三位小数）
     */
    private BigDecimal slurryDensity;
    /**
     * 送样时间
     */
    private LocalDateTime samplingTime;
    /**
     * 上传时间
     */
    private LocalDateTime uploadTime;
    /**
     * 操作人员
     */
    private String operator;
    /**
     * 复核人员
     */
    private String reviewer;
    /**
     * 复核备注
     */
    private String reviewNote;

    /**
     * 导入文件
     */
    private Long importFileId;

}