package cn.iocoder.yudao.module.division.dal.dataobject.beneficiation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 选矿每日数据 DO
 *
 * @author PlxloYzb
 */
@TableName("division_beneficiation")
@KeySequence("division_beneficiation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiationDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 标记时间
     */
    private LocalDateTime tagTime;
    /**
     * 铬精矿产量
     */
    private BigDecimal crProduction;
    /**
     * 铬精矿夜班产量
     */
    private BigDecimal crProductionNight;
    /**
     * 铬精矿转运量
     */
    private BigDecimal crTransportation;
    /**
     * 铬精矿尾渣量
     */
    private BigDecimal crTailing;
    /**
     * 铬精矿发运量
     */
    private BigDecimal crMarine;
    /**
     * 矿浆输送量
     */
    private BigDecimal slurrySend;

}