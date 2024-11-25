package cn.iocoder.yudao.module.division.controller.admin.lab.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 化验室新增/修改 Request VO")
@Data
public class LabSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private Long id;

    @Schema(description = "样品编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @NotEmpty(message = "样品编号不能为空")
    private String sampleNumber;

    @Schema(description = "Ni品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal niGrade;

    @Schema(description = "Co品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal coGrade;

    @Schema(description = "Fe品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal feGrade;

    @Schema(description = "Mn品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal mnGrade;

    @Schema(description = "Mg品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal mgGrade;

    @Schema(description = "Al品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal alGrade;

    @Schema(description = "Sc品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal scGrade;

    @Schema(description = "Cr品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal crGrade;

    @Schema(description = "Zn品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal znGrade;

    @Schema(description = "Ca品位（百分数，保留至三位小数）",requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal caGrade;

    @Schema(description = "SiO2品位（百分数，保留至三位小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal sio2Grade;

    @Schema(description = "Fe2+品位（百分数，保留至三位小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal fe2PlusGrade;

    @Schema(description = "Cr6+品位（百分数，保留至三位小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal cr6PlusGrade;

    @Schema(description = "Moisture品位（百分数，保留至三位小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal moistureGrade;

    @Schema(description = "TFe品位（百分数，保留至三位小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal tfeGrade;

    @Schema(description = "Cr2O3品位（百分数，保留至三位小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal cr2o3Grade;

    @Schema(description = "FeO品位（百分数，保留至三位小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal feoGrade;

    @Schema(description = "MgO品位（百分数，保留至三位小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal mgoGrade;

    @Schema(description = "Cr/Fe比率（百分数，保留至三位小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal crFeRatio;

    @Schema(description = "Cr2O3/FeO比率（百分数，保留至三位小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal cr2o3FeoRatio;

    @Schema(description = "矿浆浓度（百分数，保留至两位小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal slurryConcentration;

    @Schema(description = "矿浆密度（保留至三位小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal slurryDensity;

    @Schema(description = "送样时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @NotNull(message = "送样时间不能为空")
    private LocalDateTime samplingTime;

    @Schema(description = "18目", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal mu18;

    @Schema(description = "60目", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal mu60;

    @Schema(description = "80目", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal mu80;

    @Schema(description = "100目", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal mu100;

    @Schema(description = "深度）", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    private BigDecimal depth;

    @Schema(description = "岩性", example = "lab无")
    private String lithology;

    @Schema(description = "操作人员", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @NotEmpty(message = "操作人员不能为空")
    private String operator;

    @Schema(description = "复核人员", example = "lab无")
    private String reviewer;

    @Schema(description = "复核备注", example = "lab无")
    private String reviewNote;

    @Schema(description = "复核备注", example = "lab无")
    private Boolean suspicious; // Flag to indicate if the record is suspicious

    @Schema(description = "复核备注", example = "lab无")
    private Map<String, Boolean> suspiciousDetails; // Details of suspicious fields

}