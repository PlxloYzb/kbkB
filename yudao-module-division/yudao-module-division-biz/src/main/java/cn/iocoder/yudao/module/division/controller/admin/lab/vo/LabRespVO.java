package cn.iocoder.yudao.module.division.controller.admin.lab.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 化验室 Response VO")
@Data
@ExcelIgnoreUnannotated
public class LabRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "样品编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("样品编号")
    private String sampleNumber;

    @Schema(description = "元素A品位（百分数，保留至三位小数）", example = "lab无")
    @ExcelProperty("元素A品位（百分数，保留至三位小数）")
    private BigDecimal elementAGrade;

    @Schema(description = "元素B品位（百分数，保留至三位小数）", example = "lab无")
    @ExcelProperty("元素B品位（百分数，保留至三位小数）")
    private BigDecimal elementBGrade;

    @Schema(description = "元素C品位（百分数，保留至三位小数）", example = "lab无")
    @ExcelProperty("元素C品位（百分数，保留至三位小数）")
    private BigDecimal elementCGrade;

    @Schema(description = "矿浆浓度（百分数，保留至两位小数）", example = "lab无")
    @ExcelProperty("矿浆浓度（百分数，保留至两位小数）")
    private BigDecimal slurryConcentration;

    @Schema(description = "矿浆密度（保留至三位小数）", example = "lab无")
    @ExcelProperty("矿浆密度（保留至三位小数）")
    private BigDecimal slurryDensity;

    @Schema(description = "送样时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("送样时间")
    private LocalDateTime samplingTime;

    @Schema(description = "上传时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("上传时间")
    private LocalDateTime uploadTime;

    @Schema(description = "操作人员", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("操作人员")
    private String operator;

    @Schema(description = "复核人员", example = "lab无")
    @ExcelProperty("复核人员")
    private String reviewer;

    @Schema(description = "复核备注", example = "lab无")
    @ExcelProperty("复核备注")
    private String reviewNote;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "是否可疑数据", example = "false")
    private boolean suspicious;

    @Schema(description = "是否可疑数据", example = "false")
    private Map<String, Boolean> suspiciousDetails; // 可疑字段详细信息


}