package cn.iocoder.yudao.module.system.controller.admin.division;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.controller.admin.division.vo.DivisionLabRespVO;
import cn.iocoder.yudao.module.system.service.division.DivisionLabService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - DivisionLab")
@RestController
@RequestMapping("/system/division-lab")
@Validated
public class DivisionLabController {

    @Resource
    private DivisionLabService divisionLabService;

    @GetMapping("/list")
    @Operation(summary = "获得 DivisionLab 列表")
    public CommonResult<List<DivisionLabRespVO>> getDivisionLabList() {
        List<DivisionLabRespVO> list = divisionLabService.getDivisionLabList().stream()
                .map(divisionLab -> {
                    DivisionLabRespVO respVO = new DivisionLabRespVO();
                    respVO.setId(divisionLab.getId());
                    respVO.setSampleNumber(divisionLab.getSampleNumber());
                    respVO.setSlurryConcentration(divisionLab.getSlurryConcentration());
                    respVO.setSlurryDensity(divisionLab.getSlurryDensity());
                    respVO.setSamplingTime(divisionLab.getSamplingTime());
                    respVO.setOperator(divisionLab.getOperator());
                    respVO.setCreateTime(divisionLab.getCreateTime());
                    respVO.setNiGrade(divisionLab.getNiGrade());
                    respVO.setCoGrade(divisionLab.getCoGrade());
                    respVO.setFeGrade(divisionLab.getFeGrade());
                    respVO.setMnGrade(divisionLab.getMnGrade());
                    respVO.setMgGrade(divisionLab.getMgGrade());
                    respVO.setAlGrade(divisionLab.getAlGrade());
                    respVO.setScGrade(divisionLab.getScGrade());
                    respVO.setCrGrade(divisionLab.getCrGrade());
                    respVO.setZnGrade(divisionLab.getZnGrade());
                    respVO.setCaGrade(divisionLab.getCaGrade());
                    respVO.setSio2Grade(divisionLab.getSio2Grade());
                    respVO.setFe2PlusGrade(divisionLab.getFe2PlusGrade());
                    respVO.setCr6PlusGrade(divisionLab.getCr6PlusGrade());
                    respVO.setMoistureGrade(divisionLab.getMoistureGrade());
                    respVO.setTfeGrade(divisionLab.getTfeGrade());
                    respVO.setCr2o3Grade(divisionLab.getCr2o3Grade());
                    respVO.setFeoGrade(divisionLab.getFeoGrade());
                    respVO.setMgoGrade(divisionLab.getMgoGrade());
                    respVO.setCrFeRatio(divisionLab.getCrFeRatio());
                    respVO.setCr2o3FeoRatio(divisionLab.getCr2o3FeoRatio());
                    return respVO;
                })
                .collect(Collectors.toList());
        return success(list);
    }

    @GetMapping("/get")
    @Operation(summary = "获得 DivisionLab 详情")
    public CommonResult<DivisionLabRespVO> getDivisionLab(@RequestParam("id") Long id) {
        DivisionLabRespVO respVO = new DivisionLabRespVO();
        divisionLabService.getDivisionLab(id).ifPresent(divisionLab -> {
            respVO.setId(divisionLab.getId());
            respVO.setSampleNumber(divisionLab.getSampleNumber());
            respVO.setSlurryConcentration(divisionLab.getSlurryConcentration());
            respVO.setSlurryDensity(divisionLab.getSlurryDensity());
            respVO.setSamplingTime(divisionLab.getSamplingTime());
            respVO.setOperator(divisionLab.getOperator());
            respVO.setCreateTime(divisionLab.getCreateTime());
            respVO.setNiGrade(divisionLab.getNiGrade());
            respVO.setCoGrade(divisionLab.getCoGrade());
            respVO.setFeGrade(divisionLab.getFeGrade());
            respVO.setMnGrade(divisionLab.getMnGrade());
            respVO.setMgGrade(divisionLab.getMgGrade());
            respVO.setAlGrade(divisionLab.getAlGrade());
            respVO.setScGrade(divisionLab.getScGrade());
            respVO.setCrGrade(divisionLab.getCrGrade());
            respVO.setZnGrade(divisionLab.getZnGrade());
            respVO.setCaGrade(divisionLab.getCaGrade());
            respVO.setSio2Grade(divisionLab.getSio2Grade());
            respVO.setFe2PlusGrade(divisionLab.getFe2PlusGrade());
            respVO.setCr6PlusGrade(divisionLab.getCr6PlusGrade());
            respVO.setMoistureGrade(divisionLab.getMoistureGrade());
            respVO.setTfeGrade(divisionLab.getTfeGrade());
            respVO.setCr2o3Grade(divisionLab.getCr2o3Grade());
            respVO.setFeoGrade(divisionLab.getFeoGrade());
            respVO.setMgoGrade(divisionLab.getMgoGrade());
            respVO.setCrFeRatio(divisionLab.getCrFeRatio());
            respVO.setCr2o3FeoRatio(divisionLab.getCr2o3FeoRatio());
        });
        return success(respVO);
    }
}