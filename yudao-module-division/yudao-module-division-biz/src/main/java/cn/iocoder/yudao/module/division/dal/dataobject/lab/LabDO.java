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
     * Ni品位（百分数，保留至三位小数）
     */
    private BigDecimal niGrade;

    /**
     * Co品位（百分数，保留至三位小数）
     */
    private BigDecimal coGrade;

    /**
     * Fe品位（百分数，保留至三位小数）
     */
    private BigDecimal feGrade;

    /**
     * Mn品位（百分数，保留至三位小数）
     */
    private BigDecimal mnGrade;

    /**
     * Mg品位（百分数，保留至三位小数）
     */
    private BigDecimal mgGrade;

    /**
     * Al品位（百分数，保留至三位小数）
     */
    private BigDecimal alGrade;

    /**
     * Sc品位（百分数，保留至三位小数）
     */
    private BigDecimal scGrade;

    /**
     * Cr品位（百分数，保留至三位小数）
     */
    private BigDecimal crGrade;

    /**
     * Zn品位（百分数，保留至三位小数）
     */
    private BigDecimal znGrade;

    /**
     * Ca品位（百分数，保留至三位小数）
     */
    private BigDecimal caGrade;

    /**
     * SiO2品位（百分数，保留至三位小数）
     */
    private BigDecimal sio2Grade;

    /**
     * Fe2+品位（百分数，保留至三位小数）
     */
    private BigDecimal fe2PlusGrade;

    /**
     * Cr6+品位（百分数，保留至三位小数）
     */
    private BigDecimal cr6PlusGrade;

    /**
     * Moisture品位（百分数，保留至三位小数）
     */
    private BigDecimal moistureGrade;

    /**
     * TFe品位（百分数，保留至三位小数）
     */
    private BigDecimal tfeGrade;

    /**
     * Cr2O3品位（百分数，保留至三位小数）
     */
    private BigDecimal cr2o3Grade;

    /**
     * FeO品位（百分数，保留至三位小数）
     */
    private BigDecimal feoGrade;

    /**
     * MgO品位（百分数，保留至三位小数）
     */
    private BigDecimal mgoGrade;

    /**
     * Cr/Fe比率（百分数，保留至三位小数）
     */
    private BigDecimal crFeRatio;

    /**
     * Cr2O3/FeO比率（百分数，保留至三位小数）
     */
    private BigDecimal cr2o3FeoRatio;

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

    private BigDecimal mu18;

    private BigDecimal mu60;

    private BigDecimal mu80;

    private BigDecimal mu100;

    private BigDecimal depth;

    private String lithology;

}