package cn.iocoder.yudao.module.division.controller.admin.beneficiation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 选矿每日数据 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BeneficiationRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "数据类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据类型")
    private String dataType;

    @Schema(description = "标记时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("标记时间")
    private LocalDateTime tagTime;

    @Schema(description = "铬精矿产量")
    @ExcelProperty("铬精矿产量")
    private BigDecimal crProduction;

    @Schema(description = "铬精矿夜班产量")
    @ExcelProperty("铬精矿产量")
    private BigDecimal crProductionNight;

    @Schema(description = "铬精矿转运量")
    @ExcelProperty("铬精矿转运量")
    private BigDecimal crTransportation;

    @Schema(description = "铬精矿尾渣量")
    @ExcelProperty("铬精矿尾渣量")
    private BigDecimal crTailing;

    @Schema(description = "铬精矿发运量")
    @ExcelProperty("铬精矿发运量")
    private BigDecimal crMarine;

    @Schema(description = "矿浆输送量")
    @ExcelProperty("矿浆输送量")
    private BigDecimal slurrySend;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}