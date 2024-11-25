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

    @Schema(description = "Ni品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("Ni品位（百分数，保留至三位小数）")
    private BigDecimal niGrade;

    @Schema(description = "Co品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("Co品位（百分数，保留至三位小数）")
    private BigDecimal coGrade;

    @Schema(description = "Fe品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("Fe品位（百分数，保留至三位小数）")
    private BigDecimal feGrade;

    @Schema(description = "Mn品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED,example = "lab无")
    @ExcelProperty("Mn品位（百分数，保留至三位小数）")
    private BigDecimal mnGrade;

    @Schema(description = "Mg品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED,example = "lab无")
    @ExcelProperty("Mg品位（百分数，保留至三位小数）")
    private BigDecimal mgGrade;

    @Schema(description = "Al品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED,example = "lab无")
    @ExcelProperty("Al品位（百分数，保留至三位小数）")
    private BigDecimal alGrade;

    @Schema(description = "Sc品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED,example = "lab无")
    @ExcelProperty("Sc品位（百分数，保留至三位小数）")
    private BigDecimal scGrade;

    @Schema(description = "Cr品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED,example = "lab无")
    @ExcelProperty("Cr品位（百分数，保留至三位小数）")
    private BigDecimal crGrade;

    @Schema(description = "Zn品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED,example = "lab无")
    @ExcelProperty("Zn品位（百分数，保留至三位小数）")
    private BigDecimal znGrade;

    @Schema(description = "Ca品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED,example = "lab无")
    @ExcelProperty("Ca品位（百分数，保留至三位小数）")
    private BigDecimal caGrade;

    @Schema(description = "SiO2品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("SiO2品位（百分数，保留至三位小数）")
    private BigDecimal sio2Grade;

    @Schema(description = "Fe2+品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("Fe2+品位（百分数，保留至三位小数）")
    private BigDecimal fe2PlusGrade;

    @Schema(description = "Cr6+品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("Cr6+品位（百分数，保留至三位小数）")
    private BigDecimal cr6PlusGrade;

    @Schema(description = "Moisture品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("Moisture品位（百分数，保留至三位小数）")
    private BigDecimal moistureGrade;

    @Schema(description = "TFe品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("TFe品位（百分数，保留至三位小数）")
    private BigDecimal tfeGrade;

    @Schema(description = "Cr2O3品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("Cr2O3品位（百分数，保留至三位小数）")
    private BigDecimal cr2o3Grade;

    @Schema(description = "FeO品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("FeO品位（百分数，保留至三位小数）")
    private BigDecimal feoGrade;

    @Schema(description = "MgO品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("MgO品位（百分数，保留至三位小数）")
    private BigDecimal mgoGrade;

    @Schema(description = "Cr/Fe比率（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("Cr/Fe比率（百分数，保留至三位小数）")
    private BigDecimal crFeRatio;

    @Schema(description = "Cr2O3/FeO比率（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("Cr2O3/FeO比率（百分数，保留至三位小数）")
    private BigDecimal cr2o3FeoRatio;

    @Schema(description = "矿浆浓度（百分数，保留至两位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("矿浆浓度（百分数，保留至两位小数）")
    private BigDecimal slurryConcentration;

    @Schema(description = "矿浆密度（保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("矿浆密度（保留至三位小数）")
    private BigDecimal slurryDensity;

    @Schema(description = "18目",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("18目")
    private BigDecimal mu18;

    @Schema(description = "60目",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("60目")
    private BigDecimal mu60;

    @Schema(description = "80目",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("80目")
    private BigDecimal mu80;

    @Schema(description = "100目",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("100目")
    private BigDecimal mu100;

    @Schema(description = "深度",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("深度")
    private BigDecimal depth;

    @Schema(description = "岩性", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("岩性")
    private String lithology;

    @Schema(description = "送样时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @ExcelProperty("送样时间")
    private LocalDateTime samplingTime;

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