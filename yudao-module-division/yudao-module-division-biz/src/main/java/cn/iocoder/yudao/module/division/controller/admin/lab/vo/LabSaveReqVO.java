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

    @Schema(description = "元素A品位（百分数，保留至三位小数）", example = "lab无")
    private BigDecimal elementAGrade;

    @Schema(description = "元素B品位（百分数，保留至三位小数）", example = "lab无")
    private BigDecimal elementBGrade;

    @Schema(description = "元素C品位（百分数，保留至三位小数）", example = "lab无")
    private BigDecimal elementCGrade;

    @Schema(description = "矿浆浓度（百分数，保留至两位小数）", example = "lab无")
    private BigDecimal slurryConcentration;

    @Schema(description = "矿浆密度（保留至三位小数）", example = "lab无")
    private BigDecimal slurryDensity;

    @Schema(description = "送样时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @NotNull(message = "送样时间不能为空")
    private LocalDateTime samplingTime;

    @Schema(description = "上传时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "lab无")
    @NotNull(message = "上传时间不能为空")
    private LocalDateTime uploadTime;

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