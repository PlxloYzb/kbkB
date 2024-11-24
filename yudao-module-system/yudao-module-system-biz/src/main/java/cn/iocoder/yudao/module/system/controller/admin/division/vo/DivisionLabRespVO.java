package cn.iocoder.yudao.module.system.controller.admin.division.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - DivisionLab 响应 VO")
@Data
public class DivisionLabRespVO {

    @Schema(description = "编号", required = true, example = "1")
    private Long id;

    @Schema(description = "样本编号", required = true, example = "S123456")
    private String sampleNumber;

    @Schema(description = "泥浆浓度", example = "1.2")
    private Double slurryConcentration;

    @Schema(description = "泥浆密度", example = "1.3")
    private Double slurryDensity;

    @Schema(description = "采样时间", example = "2023-10-01T12:00:00")
    private LocalDateTime samplingTime;

    @Schema(description = "操作员", example = "张三")
    private String operator;

    @Schema(description = "创建时间", example = "2023-10-01T12:00:00")
    private LocalDateTime createTime;

    @Schema(description = "镍等级", example = "1.5")
    private Double niGrade;

    @Schema(description = "钴等级", example = "1.6")
    private Double coGrade;

    @Schema(description = "铁等级", example = "1.7")
    private Double feGrade;

    @Schema(description = "锰等级", example = "1.8")
    private Double mnGrade;

    @Schema(description = "镁等级", example = "1.9")
    private Double mgGrade;

    @Schema(description = "铝等级", example = "2.0")
    private Double alGrade;

    @Schema(description = "钪等级", example = "2.1")
    private Double scGrade;

    @Schema(description = "铒等级", example = "2.2")
    private Double crGrade;

    @Schema(description = "锌等级", example = "2.3")
    private Double znGrade;

    @Schema(description = "钙等级", example = "2.4")
    private Double caGrade;

    @Schema(description = "二氧化硅等级", example = "2.5")
    private Double sio2Grade;

    @Schema(description = "二价铁等级", example = "2.6")
    private Double fe2PlusGrade;

    @Schema(description = "铬等级", example = "2.7")
    private Double cr6PlusGrade;

    @Schema(description = "水分等级", example = "2.8")
    private Double moistureGrade;

    @Schema(description = "总铁等级", example = "2.9")
    private Double tfeGrade;

    @Schema(description = "三氧化二铬等级", example = "3.0")
    private Double cr2o3Grade;

    @Schema(description = "氧化亚铁等级", example = "3.1")
    private Double feoGrade;

    @Schema(description = "氧化镁等级", example = "3.2")
    private Double mgoGrade;

    @Schema(description = "铬铁比", example = "3.3")
    private Double crFeRatio;

    @Schema(description = "三氧化二铬与氧化亚铁比", example = "3.4")
    private Double cr2o3FeoRatio;
}