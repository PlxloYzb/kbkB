package cn.iocoder.yudao.module.division.controller.admin.beneficiation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 选矿每日数据新增/修改 Request VO")
@Data
public class BeneficiationSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "数据类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据类型不能为空")
    private String dataType;

    @Schema(description = "标记时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "标记时间不能为空")
    private LocalDateTime tagTime;

    @Schema(description = "铬精矿产量")
    private BigDecimal crProduction;

    @Schema(description = "铬精矿夜班产量")
    private BigDecimal crProductionNight;

    @Schema(description = "铬精矿转运量")
    private BigDecimal crTransportation;

    @Schema(description = "铬精矿尾渣量")
    private BigDecimal crTailing;

    @Schema(description = "铬精矿发运量")
    private BigDecimal crMarine;

    @Schema(description = "矿浆输送量")
    private BigDecimal slurrySend;

}