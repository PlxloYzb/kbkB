package cn.iocoder.yudao.module.division.controller.admin.lab.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 化验室分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LabPageReqVO extends PageParam {

    @Schema(description = "样品编号", example = "lab无")
    private String sampleNumber;

//    @Schema(description = "元素A品位（百分数，保留至三位小数）", example = "lab无")
//    private BigDecimal elementAGrade;

//    @Schema(description = "元素A品位起始值（百分数，保留至三位小数）", example = "lab无")
//    private BigDecimal elementAGradeStart;
//
//    @Schema(description = "元素A品位结束值（百分数，保留至三位小数）", example = "lab无")
//    private BigDecimal elementAGradeEnd;

    @Schema(description = "元素A品位（百分数，保留至三位小数）", example = "lab无")
    private BigDecimal[] elementAGrade;

    @Schema(description = "元素B品位（百分数，保留至三位小数）", example = "lab无")
    private BigDecimal[] elementBGrade;

    @Schema(description = "元素C品位（百分数，保留至三位小数）", example = "lab无")
    private BigDecimal[] elementCGrade;

    @Schema(description = "矿浆浓度（百分数，保留至两位小数）", example = "lab无")
    private BigDecimal[] slurryConcentration;

    @Schema(description = "矿浆密度（保留至三位小数）", example = "lab无")
    private BigDecimal[] slurryDensity;

    @Schema(description = "送样时间", example = "lab无")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] samplingTime;

    @Schema(description = "上传时间", example = "lab无")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] uploadTime;

    @Schema(description = "操作人员", example = "lab无")
    private String operator;

    @Schema(description = "复核人员", example = "lab无")
    private String reviewer;

    @Schema(description = "复核备注", example = "lab无")
    private String reviewNote;

    @Schema(description = "创建时间", example = "lab无")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}