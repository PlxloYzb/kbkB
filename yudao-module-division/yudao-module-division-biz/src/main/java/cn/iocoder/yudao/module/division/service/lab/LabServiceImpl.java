package cn.iocoder.yudao.module.division.service.lab;

import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.module.division.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.division.service.importfile.ImportFileService;
import com.alibaba.druid.util.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.division.controller.admin.lab.vo.*;
import cn.iocoder.yudao.module.division.dal.dataobject.lab.LabDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.division.dal.mysql.lab.LabMapper;
import org.springframework.web.multipart.MultipartFile;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.division.enums.ErrorCodeConstants.*;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * 化验室 Service 实现类
 *
 * @author PlxloYzb
 */
@Service
@Validated
public class LabServiceImpl implements LabService {

    @Resource
    private LabMapper labMapper;

    @Resource
    private ImportFileService importFileService;

    @Value("${app.import.confirmPassword}")
    private String confirmPassword;

    @Value("${app.import.elementAGradeRange}")
    private String elementAGradeRange;

    @Value("${app.import.elementAGradeRange}")
    private String elementBGradeRange;

    @Value("${app.import.elementAGradeRange}")
    private String elementCGradeRange;

    @Value("${app.import.slurryConcentrationRange}")
    private String slurryConcentrationRange;

    @Value("${app.import.slurryDensityRange}")
    private String slurryDensityRange;



    @Override
    public Long createLab(LabSaveReqVO createReqVO) {
        // 插入
        LabDO lab = BeanUtils.toBean(createReqVO, LabDO.class);
        labMapper.insert(lab);
        // 返回
        return lab.getId();
    }

    @Override
    public void updateLab(LabSaveReqVO updateReqVO) {
        // 校验存在
        validateLabExists(updateReqVO.getId());
        // 更新
        LabDO updateObj = BeanUtils.toBean(updateReqVO, LabDO.class);
        labMapper.updateById(updateObj);
    }

    @Override
    public void deleteLab(Long id) {
        // 校验存在
        validateLabExists(id);
        // 删除
        labMapper.deleteById(id);
    }

    private void validateLabExists(Long id) {
        if (labMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.LAB_NOT_EXISTS);
        }
    }

    @Override
    public LabDO getLab(Long id) {
        return labMapper.selectById(id);
    }

    @Override
    public PageResult<LabDO> getLabPage(LabPageReqVO pageReqVO) {
        return labMapper.selectPage(pageReqVO);
    }

    @Override
    public List<LabRespVO> previewImportData(MultipartFile file) {
        try {
            List<LabSaveReqVO> labList = parseExcelFile(file);
            List<LabRespVO> previewList = new ArrayList<>();
            for (LabSaveReqVO lab : labList) {
                LabRespVO respVO = BeanUtils.toBean(lab, LabRespVO.class);

                // 获取详细的可疑信息
                Map<String, Boolean> suspiciousMap = checkSuspiciousElements(lab);
                respVO.setSuspiciousDetails(suspiciousMap);

                // 是否存在任意可疑字段
                respVO.setSuspicious(suspiciousMap.containsValue(true));
                previewList.add(respVO);
            }
            return previewList;
        } catch (IOException e) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.IMPORT_FILE_PARSE_ERROR);
        }
    }

    @Override
    @Transactional
    public void importLabData(List<LabSaveReqVO> labList, Long importFileId, String confirmPassword) {
        boolean hasSuspiciousData = false;

        // First pass: Check for suspicious data and set flags
        for (LabSaveReqVO lab : labList) {
            Map<String, Boolean> suspiciousMap = checkSuspiciousElements(lab);
            boolean isSuspicious = suspiciousMap.containsValue(true);
            lab.setSuspicious(isSuspicious);
            lab.setSuspiciousDetails(suspiciousMap);

            if (isSuspicious) {
                hasSuspiciousData = true;
            }
        }

        // If there is suspicious data, validate confirmPassword
        if (hasSuspiciousData) {
            if (StringUtils.isEmpty(confirmPassword)) {
                throw new IllegalArgumentException("存在可疑数据时，审核密码是必需的。");
            }
            // Validate the confirmPassword (e.g., compare with stored password)
            if (!this.confirmPassword.equals(confirmPassword)) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.IMPORT_CONFIRM_PASSWORD_ERROR);
            }
        }

        // Second pass: Process and insert data
        for (LabSaveReqVO lab : labList) {
            if (lab.getSuspicious()) {
                // Handle suspicious data (e.g., add review notes)
                String suspiciousFields = lab.getSuspiciousDetails().entrySet().stream()
                        .filter(Map.Entry::getValue)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.joining(", "));
                lab.setReviewNote("可疑字段：" + suspiciousFields);
                // Additional handling can be added here
            }

            LabDO labDO = BeanUtils.toBean(lab, LabDO.class);
            labDO.setImportFileId(importFileId);
            labMapper.insert(labDO);
        }
    }



    private List<LabSaveReqVO> parseExcelFile(MultipartFile file) throws IOException {
        List<LabSaveReqVO> labList = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // 跳过表头行
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                LabSaveReqVO lab = new LabSaveReqVO();
                lab.setSampleNumber(row.getCell(0).getStringCellValue());
                lab.setElementAGrade(new BigDecimal(row.getCell(1).getNumericCellValue()));
                lab.setElementBGrade(new BigDecimal(row.getCell(2).getNumericCellValue()));
                lab.setElementCGrade(new BigDecimal(row.getCell(3).getNumericCellValue()));
                lab.setSlurryConcentration(new BigDecimal(row.getCell(4).getNumericCellValue()));
                lab.setSlurryDensity(new BigDecimal(row.getCell(5).getNumericCellValue()));
                lab.setSamplingTime(row.getCell(6).getLocalDateTimeCellValue());
                lab.setUploadTime(row.getCell(7).getLocalDateTimeCellValue());
                lab.setOperator(row.getCell(8).getStringCellValue());
                lab.setReviewer(row.getCell(9).getStringCellValue());
                lab.setReviewNote(row.getCell(10).getStringCellValue());
                labList.add(lab);
            }
        }
        return labList;
    }

    private Map<String, Boolean> checkSuspiciousElements(LabSaveReqVO lab) {
        Map<String, Boolean> suspiciousMap = new HashMap<>();

        BigDecimal minAGrade = new BigDecimal(elementAGradeRange.split("-")[0]);
        BigDecimal maxAGrade = new BigDecimal(elementAGradeRange.split("-")[1]);
        suspiciousMap.put("elementAGrade", lab.getElementAGrade().compareTo(minAGrade) < 0 || lab.getElementAGrade().compareTo(maxAGrade) > 0);

        BigDecimal minBGrade = new BigDecimal(elementBGradeRange.split("-")[0]);
        BigDecimal maxBGrade = new BigDecimal(elementBGradeRange.split("-")[1]);
        suspiciousMap.put("elementBGrade", lab.getElementBGrade().compareTo(minBGrade) < 0 || lab.getElementBGrade().compareTo(maxBGrade) > 0);

        BigDecimal minCGrade = new BigDecimal(elementCGradeRange.split("-")[0]);
        BigDecimal maxCGrade = new BigDecimal(elementCGradeRange.split("-")[1]);
        suspiciousMap.put("elementCGrade", lab.getElementCGrade().compareTo(minCGrade) < 0 || lab.getElementCGrade().compareTo(maxCGrade) > 0);

        BigDecimal minSlurryConcentration = new BigDecimal(slurryConcentrationRange.split("-")[0]);
        BigDecimal maxSlurryConcentration = new BigDecimal(slurryConcentrationRange.split("-")[1]);
        suspiciousMap.put("slurryConcentration", lab.getSlurryConcentration().compareTo(minSlurryConcentration) < 0 || lab.getSlurryConcentration().compareTo(maxSlurryConcentration) > 0
        );

        BigDecimal minSlurryDensityRange = new BigDecimal(slurryDensityRange.split("-")[0]);
        BigDecimal maxSlurryDensityRange = new BigDecimal(slurryDensityRange.split("-")[1]);
        suspiciousMap.put("slurryDensity", lab.getSlurryDensity().compareTo(minSlurryDensityRange) < 0 || lab.getSlurryDensity().compareTo(maxSlurryDensityRange) > 0
        );




        return suspiciousMap;
    }

}