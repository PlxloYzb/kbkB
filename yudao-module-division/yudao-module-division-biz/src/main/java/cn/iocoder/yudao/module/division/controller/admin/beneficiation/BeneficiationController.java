package cn.iocoder.yudao.module.division.controller.admin.beneficiation;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.division.controller.admin.beneficiation.vo.*;
import cn.iocoder.yudao.module.division.dal.dataobject.beneficiation.BeneficiationDO;
import cn.iocoder.yudao.module.division.service.beneficiation.BeneficiationService;

@Tag(name = "管理后台 - 选矿每日数据")
@RestController
@RequestMapping("/division/beneficiation")
@Validated
public class BeneficiationController {

    @Resource
    private BeneficiationService beneficiationService;

    @PostMapping("/create")
    @Operation(summary = "创建选矿每日数据")
    @PreAuthorize("@ss.hasPermission('division:beneficiation:create')")
    public CommonResult<Long> createBeneficiation(@Valid @RequestBody BeneficiationSaveReqVO createReqVO) {
        return success(beneficiationService.createBeneficiation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新选矿每日数据")
    @PreAuthorize("@ss.hasPermission('division:beneficiation:update')")
    public CommonResult<Boolean> updateBeneficiation(@Valid @RequestBody BeneficiationSaveReqVO updateReqVO) {
        beneficiationService.updateBeneficiation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除选矿每日数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('division:beneficiation:delete')")
    public CommonResult<Boolean> deleteBeneficiation(@RequestParam("id") Long id) {
        beneficiationService.deleteBeneficiation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得选矿每日数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('division:beneficiation:query')")
    public CommonResult<BeneficiationRespVO> getBeneficiation(@RequestParam("id") Long id) {
        BeneficiationDO beneficiation = beneficiationService.getBeneficiation(id);
        return success(BeanUtils.toBean(beneficiation, BeneficiationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得选矿每日数据分页")
    @PreAuthorize("@ss.hasPermission('division:beneficiation:query')")
    public CommonResult<PageResult<BeneficiationRespVO>> getBeneficiationPage(@Valid BeneficiationPageReqVO pageReqVO) {
        PageResult<BeneficiationDO> pageResult = beneficiationService.getBeneficiationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BeneficiationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出选矿每日数据 Excel")
    @PreAuthorize("@ss.hasPermission('division:beneficiation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBeneficiationExcel(@Valid BeneficiationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BeneficiationDO> list = beneficiationService.getBeneficiationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "选矿每日数据.xls", "数据", BeneficiationRespVO.class,
                        BeanUtils.toBean(list, BeneficiationRespVO.class));
    }

}