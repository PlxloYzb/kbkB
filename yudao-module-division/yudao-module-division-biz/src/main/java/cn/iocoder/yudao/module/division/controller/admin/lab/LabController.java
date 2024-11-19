package cn.iocoder.yudao.module.division.controller.admin.lab;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.division.controller.admin.lab.vo.*;
import cn.iocoder.yudao.module.division.dal.dataobject.importfile.ImportFileDO;
import cn.iocoder.yudao.module.division.dal.dataobject.lab.LabDO;
import cn.iocoder.yudao.module.division.service.importfile.ImportFileService;
import cn.iocoder.yudao.module.division.service.lab.LabService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 化验室")
@RestController
@RequestMapping("/division/lab")
@Validated
public class LabController {

    @Resource
    private LabService labService;

    @Resource
    private ImportFileService importFileService;

    @PostMapping("/create")
    @Operation(summary = "创建化验室")
    @PreAuthorize("@ss.hasPermission('division:lab:create')")
    public CommonResult<Long> createLab(@Valid @RequestBody LabSaveReqVO createReqVO) {
        return success(labService.createLab(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新化验室")
    @PreAuthorize("@ss.hasPermission('division:lab:update')")
    public CommonResult<Boolean> updateLab(@Valid @RequestBody LabSaveReqVO updateReqVO) {
        labService.updateLab(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除化验室")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('division:lab:delete')")
    public CommonResult<Boolean> deleteLab(@RequestParam("id") Long id) {
        labService.deleteLab(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得化验室")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('division:lab:query')")
    public CommonResult<LabRespVO> getLab(@RequestParam("id") Long id) {
        LabDO lab = labService.getLab(id);
        return success(BeanUtils.toBean(lab, LabRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得化验室分页")
    @PreAuthorize("@ss.hasPermission('division:lab:query')")
    public CommonResult<PageResult<LabRespVO>> getLabPage(@Valid LabPageReqVO pageReqVO) {
        PageResult<LabDO> pageResult = labService.getLabPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LabRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出化验室 Excel")
    @PreAuthorize("@ss.hasPermission('division:lab:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLabExcel(@Valid LabPageReqVO pageReqVO,
                               HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LabDO> list = labService.getLabPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "化验室.xls", "数据", LabRespVO.class,
                BeanUtils.toBean(list, LabRespVO.class));
    }

    @PostMapping(value = "/preview-import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "预览导入数据")
    @PreAuthorize("@ss.hasPermission('division:lab:import')")
    public CommonResult<List<LabRespVO>> previewImportData(@RequestPart("file") MultipartFile file) throws IOException {
//        if (file == null || file.isEmpty()) {
//            log.error("未接收到文件");
//            return CommonResult.error("未接收到文件");
//        }
        List<LabRespVO> previewList = labService.previewImportData(file);
        return success(previewList);
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "导入数据")
    @PreAuthorize("@ss.hasPermission('division:lab:import')")
    public CommonResult<Boolean> importLabData(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "confirmPassword", required = false) String confirmPassword) throws IOException {

        List<LabRespVO> previewList = labService.previewImportData(file);
        List<LabSaveReqVO> labList = previewList.stream()
                .map(labRespVO -> BeanUtils.toBean(labRespVO, LabSaveReqVO.class))
                .collect(Collectors.toList());
        Long importFileId = importFileService.saveImportFile(new ImportFileDO(null, file.getOriginalFilename(), file.getBytes()));

        // Pass labList and confirmPassword to the service
        labService.importLabData(labList, importFileId, confirmPassword);

        return success(true);
    }


}