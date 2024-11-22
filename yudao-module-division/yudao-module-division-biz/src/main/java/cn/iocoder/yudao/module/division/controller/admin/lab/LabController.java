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
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.apache.commons.compress.utils.IOUtils;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;



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


    @GetMapping("/export-files")
    @Operation(summary = "导出文件")
    @PreAuthorize("@ss.hasPermission('division:lab:download')")
    public void exportFiles(@RequestParam List<String> fileNames, HttpServletResponse response) {
        // 在写入响应之前，先获取文件数据并处理可能的异常
        List<ImportFileDO> files;
        try {
            files = importFileService.getImportFilesByName(fileNames);
        } catch (Exception e) {
            // 如果获取文件数据失败，返回错误信息
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json;charset=UTF-8");
            try (PrintWriter writer = response.getWriter()) {
                writer.write("{\"code\":500,\"message\":\"获取文件数据失败\"}");
                writer.flush();
            } catch (IOException ioException) {
            }
            return;
        }

        // 确保 Zip 条目名称唯一
        Set<String> zipEntryNames = new HashSet<>();
        int index = 1;
        for (ImportFileDO file : files) {
            String fileName = file.getFileName();
            if (!zipEntryNames.add(fileName)) {
                // 如果文件名重复，修改文件名
                while (!zipEntryNames.add("(" + index + ")_" + fileName)) {
                    index++;
                }
                file.setFileName("(" + index + ")_" + fileName);
            }
        }

        // 在内存中创建 Zip 文件，以便在写入响应之前捕获可能的异常
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(byteArrayOutputStream)) {
            for (ImportFileDO file : files) {
                // 创建 Zip 条目
                ZipEntry entry = new ZipEntry(file.getFileName());
                zos.putNextEntry(entry);
                // 写入文件数据
                IOUtils.copy(new ByteArrayInputStream(file.getFileData()), zos);
                zos.closeEntry();
            }
            zos.finish();
        } catch (IOException e) {
            // 如果在创建 Zip 文件时发生异常，返回错误信息
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json;charset=UTF-8");
            try (PrintWriter writer = response.getWriter()) {
                writer.write("{\"code\":500,\"message\":\"导出文件失败\"}");
                writer.flush();
            } catch (IOException ioException) {
            }
            return;
        }

        // 设置响应头
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"export.zip\"");

        // 将 Zip 文件写入响应
        try (ServletOutputStream servletOutputStream = response.getOutputStream()) {
            byteArrayOutputStream.writeTo(servletOutputStream);
            servletOutputStream.flush();
        } catch (IOException e) {
            // 如果在写入响应时发生异常，记录错误日志
            // 响应已经提交，无法返回错误信息
        }
    }

    @GetMapping("/export-file/page")
    @Operation(summary = "获取导出文件分页列表")
    @PreAuthorize("@ss.hasPermission('division:lab:page')")
    public CommonResult<PageResult<ImportFileDO>> getImportFilesPage(@Valid ImportFilePageReqVO pageReqVO) {
        PageResult<ImportFileDO> pageResult = importFileService.getImportFilesPage(pageReqVO);
        return success(pageResult);
    }


}
