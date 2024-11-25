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

import org.apache.poi.ss.usermodel.*;
import java.math.BigDecimal;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import static cn.iocoder.yudao.module.division.enums.ErrorCodeConstants.*;

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


    @Value("${app.import.niGradeRange}")
    private String niGradeRange;

    @Value("${app.import.coGradeRange}")
    private String coGradeRange;

    @Value("${app.import.feGradeRange}")
    private String feGradeRange;

    @Value("${app.import.mnGradeRange}")
    private String mnGradeRange;

    @Value("${app.import.mgGradeRange}")
    private String mgGradeRange;

    @Value("${app.import.alGradeRange}")
    private String alGradeRange;

    @Value("${app.import.scGradeRange}")
    private String scGradeRange;

    @Value("${app.import.crGradeRange}")
    private String crGradeRange;

    @Value("${app.import.znGradeRange}")
    private String znGradeRange;

    @Value("${app.import.caGradeRange}")
    private String caGradeRange;

    @Value("${app.import.sio2GradeRange}")
    private String sio2GradeRange;

    @Value("${app.import.fe2PlusGradeRange}")
    private String fe2PlusGradeRange;

    @Value("${app.import.cr6PlusGradeRange}")
    private String cr6PlusGradeRange;

    @Value("${app.import.moistureGradeRange}")
    private String moistureGradeRange;

    @Value("${app.import.tfeGradeRange}")
    private String tfeGradeRange;

    @Value("${app.import.cr2o3GradeRange}")
    private String cr2o3GradeRange;

    @Value("${app.import.feoGradeRange}")
    private String feoGradeRange;

    @Value("${app.import.mgoGradeRange}")
    private String mgoGradeRange;

    @Value("${app.import.crFeRatioRange}")
    private String crFeRatioRange;

    @Value("${app.import.cr2o3FeoRatioRange}")
    private String cr2o3FeoRatioRange;

    @Value("${app.import.slurryConcentrationRange}")
    private String slurryConcentrationRange;

    @Value("${app.import.slurryDensityRange}")
    private String slurryDensityRange;

    @Value("${app.import.mu18Range}")
    private String mu18Range;

    @Value("${app.import.mu60Range}")
    private String mu60Range;

    @Value("${app.import.mu80Range}")
    private String mu80Range;

    @Value("${app.import.mu100Range}")
    private String mu100Range;

    @Value("${app.import.depthRange}")
    private String depthRange;




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
            validateLabData(lab);

            LabDO labDO = BeanUtils.toBean(lab, LabDO.class);
            labDO.setImportFileId(importFileId);
            labMapper.insert(labDO);
        }
    }

    private void validateLabData(LabSaveReqVO lab) {
        if (lab.getSampleNumber() == null || lab.getSampleNumber().trim().isEmpty()) {
            throw new ServiceException(SAMPLE_NUMBER_REQUIRED.getCode(), SAMPLE_NUMBER_REQUIRED.getMsg());
        }
        if (lab.getSamplingTime() == null) {
            throw new ServiceException(SAMPLING_TIME_REQUIRED.getCode(), SAMPLING_TIME_REQUIRED.getMsg());
        }
        if (lab.getOperator() == null || lab.getOperator().trim().isEmpty()) {
            throw new ServiceException(OPERATOR_NAME_REQUIRED.getCode(), OPERATOR_NAME_REQUIRED.getMsg());
        }

    }

    private List<LabSaveReqVO> parseExcelFile(MultipartFile file) throws IOException {
        List<LabSaveReqVO> labList = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                LabSaveReqVO lab = new LabSaveReqVO();

                lab.setSampleNumber(getCellStringValue(row, 0));
                lab.setSamplingTime(getCellLocalDateTimeValue(row, 1));
                lab.setOperator(getCellStringValue(row, 2));
                lab.setNiGrade(getCellBigDecimalValue(row, 3));
                lab.setCoGrade(getCellBigDecimalValue(row, 4));
                lab.setFeGrade(getCellBigDecimalValue(row, 5));
                lab.setMnGrade(getCellBigDecimalValue(row, 6));
                lab.setMgGrade(getCellBigDecimalValue(row, 7));
                lab.setAlGrade(getCellBigDecimalValue(row, 8));
                lab.setSlurryConcentration(getCellBigDecimalValue(row, 9));
                lab.setSlurryDensity(getCellBigDecimalValue(row, 10));
                lab.setCrGrade(getCellBigDecimalValue(row, 11));
                lab.setTfeGrade(getCellBigDecimalValue(row, 12));
                lab.setCr2o3Grade(getCellBigDecimalValue(row, 13));
                lab.setFeoGrade(getCellBigDecimalValue(row, 14));
                lab.setCrFeRatio(getCellBigDecimalValue(row, 15));
                lab.setCr2o3FeoRatio(getCellBigDecimalValue(row, 16));
                lab.setMoistureGrade(getCellBigDecimalValue(row, 17));

                lab.setMu18(getCellBigDecimalValue(row, 18));
                lab.setMu60(getCellBigDecimalValue(row, 19));
                lab.setMu80(getCellBigDecimalValue(row, 20));
                lab.setMu100(getCellBigDecimalValue(row, 21));
                lab.setDepth(getCellBigDecimalValue(row, 22));
                lab.setLithology(getCellStringValue(row, 23));

                lab.setReviewer(getCellStringValue(row, 24));
                lab.setReviewNote(getCellStringValue(row, 25));
                lab.setMgoGrade(getCellBigDecimalValue(row, 26));
                lab.setScGrade(getCellBigDecimalValue(row, 27));
                lab.setZnGrade(getCellBigDecimalValue(row, 28));
                lab.setCaGrade(getCellBigDecimalValue(row, 29));
                lab.setSio2Grade(getCellBigDecimalValue(row, 30));
                lab.setFe2PlusGrade(getCellBigDecimalValue(row, 31));
                lab.setCr6PlusGrade(getCellBigDecimalValue(row, 32));










                labList.add(lab);
            }
        }
        return labList;
    }

    private String getCellStringValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                String value = cell.getStringCellValue();
                return value != null && !value.trim().isEmpty() ? value.trim() : null;
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                Workbook wb = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = evaluator.evaluate(cell);
                switch (cellValue.getCellType()) {
                    case STRING:
                        String formulaValue = cellValue.getStringValue();
                        return formulaValue != null && !formulaValue.trim().isEmpty() ? formulaValue.trim() : null;
                    case NUMERIC:
                        return String.valueOf(cellValue.getNumberValue());
                    default:
                        return null;
                }
            default:
                return null;
        }
    }

    private BigDecimal getCellBigDecimalValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return BigDecimal.valueOf(cell.getNumericCellValue());
            case STRING:
                String value = cell.getStringCellValue();
                if (value != null && !value.trim().isEmpty()) {
                    try {
                        return new BigDecimal(value.trim());
                    } catch (NumberFormatException e) {
                        // Handle the exception as needed (e.g., log it or throw a custom exception)
                        return null;
                    }
                }
                return null;
            case FORMULA:
                Workbook wb = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue.getCellType() == CellType.NUMERIC) {
                    return BigDecimal.valueOf(cellValue.getNumberValue());
                } else if (cellValue.getCellType() == CellType.STRING) {
                    String formulaValue = cellValue.getStringValue();
                    if (formulaValue != null && !formulaValue.trim().isEmpty()) {
                        try {
                            return new BigDecimal(formulaValue.trim());
                        } catch (NumberFormatException e) {
                            // Handle the exception as needed
                            return null;
                        }
                    }
                }
                return null;
            default:
                return null;
        }
    }

    private LocalDateTime getCellLocalDateTimeValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue();
                } else {
                    // Cell contains a numeric value that's not a date
                    return null;
                }
            case STRING:
                String value = cell.getStringCellValue();
                if (value != null && !value.trim().isEmpty()) {
                    try {
                        // Adjust the date format pattern according to your date format
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        return LocalDateTime.parse(value.trim(), formatter);
                    } catch (Exception e) {
                        // Handle parsing exception
                        return null;
                    }
                }
                return null;
            case FORMULA:
                Workbook wb = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue.getCellType() == CellType.NUMERIC) {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getLocalDateTimeCellValue();
                    }
                } else if (cellValue.getCellType() == CellType.STRING) {
                    String formulaValue = cellValue.getStringValue();
                    if (formulaValue != null && !formulaValue.trim().isEmpty()) {
                        try {
                            // Adjust the date format pattern according to your date format
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            return LocalDateTime.parse(formulaValue.trim(), formatter);
                        } catch (Exception e) {
                            // Handle parsing exception
                            return null;
                        }
                    }
                }
                return null;
            default:
                return null;
        }
    }



    private Map<String, Boolean> checkSuspiciousElements(LabSaveReqVO lab) {
        Map<String, Boolean> suspiciousMap = new HashMap<>();
// niGrade
        BigDecimal minNiGrade = new BigDecimal(niGradeRange.split("-")[0]);
        BigDecimal maxNiGrade = new BigDecimal(niGradeRange.split("-")[1]);
        Boolean isNiGradeSuspicious = false;
        if (lab.getNiGrade() != null) {
            isNiGradeSuspicious = lab.getNiGrade().compareTo(minNiGrade) < 0 || lab.getNiGrade().compareTo(maxNiGrade) > 0;
        }
        suspiciousMap.put("niGrade", isNiGradeSuspicious);


// coGrade
        BigDecimal minCoGrade = new BigDecimal(coGradeRange.split("-")[0]);
        BigDecimal maxCoGrade = new BigDecimal(coGradeRange.split("-")[1]);
        Boolean isCoGradeSuspicious = false;
        if (lab.getCoGrade() != null) {
            isCoGradeSuspicious = lab.getCoGrade().compareTo(minCoGrade) < 0 || lab.getCoGrade().compareTo(maxCoGrade) > 0;
        }
        suspiciousMap.put("coGrade", isCoGradeSuspicious);

// feGrade
        BigDecimal minFeGrade = new BigDecimal(feGradeRange.split("-")[0]);
        BigDecimal maxFeGrade = new BigDecimal(feGradeRange.split("-")[1]);
        Boolean isFeGradeSuspicious = false;
        if (lab.getFeGrade() != null) {
            isFeGradeSuspicious = lab.getFeGrade().compareTo(minFeGrade) < 0 || lab.getFeGrade().compareTo(maxFeGrade) > 0;
        }
        suspiciousMap.put("feGrade", isFeGradeSuspicious);

// mnGrade
        BigDecimal minMnGrade = new BigDecimal(mnGradeRange.split("-")[0]);
        BigDecimal maxMnGrade = new BigDecimal(mnGradeRange.split("-")[1]);
        Boolean isMnGradeSuspicious = false;
        if (lab.getMnGrade() != null) {
            isMnGradeSuspicious = lab.getMnGrade().compareTo(minMnGrade) < 0 || lab.getMnGrade().compareTo(maxMnGrade) > 0;
        }
        suspiciousMap.put("mnGrade", isMnGradeSuspicious);

// mgGrade
        BigDecimal minMgGrade = new BigDecimal(mgGradeRange.split("-")[0]);
        BigDecimal maxMgGrade = new BigDecimal(mgGradeRange.split("-")[1]);
        Boolean isMgGradeSuspicious = false;
        if (lab.getMgGrade() != null) {
            isMgGradeSuspicious = lab.getMgGrade().compareTo(minMgGrade) < 0 || lab.getMgGrade().compareTo(maxMgGrade) > 0;
        }
        suspiciousMap.put("mgGrade", isMgGradeSuspicious);

// alGrade
        BigDecimal minAlGrade = new BigDecimal(alGradeRange.split("-")[0]);
        BigDecimal maxAlGrade = new BigDecimal(alGradeRange.split("-")[1]);
        Boolean isAlGradeSuspicious = false;
        if (lab.getAlGrade() != null) {
            isAlGradeSuspicious = lab.getAlGrade().compareTo(minAlGrade) < 0 || lab.getAlGrade().compareTo(maxAlGrade) > 0;
        }
        suspiciousMap.put("alGrade", isAlGradeSuspicious);

// scGrade
        BigDecimal minScGrade = new BigDecimal(scGradeRange.split("-")[0]);
        BigDecimal maxScGrade = new BigDecimal(scGradeRange.split("-")[1]);
        Boolean isScGradeSuspicious = false;
        if (lab.getScGrade() != null) {
            isScGradeSuspicious = lab.getScGrade().compareTo(minScGrade) < 0 || lab.getScGrade().compareTo(maxScGrade) > 0;
        }
        suspiciousMap.put("scGrade", isScGradeSuspicious);

// crGrade
        BigDecimal minCrGrade = new BigDecimal(crGradeRange.split("-")[0]);
        BigDecimal maxCrGrade = new BigDecimal(crGradeRange.split("-")[1]);
        Boolean isCrGradeSuspicious = false;
        if (lab.getCrGrade() != null) {
            isCrGradeSuspicious = lab.getCrGrade().compareTo(minCrGrade) < 0 || lab.getCrGrade().compareTo(maxCrGrade) > 0;
        }
        suspiciousMap.put("crGrade", isCrGradeSuspicious);

// znGrade
        BigDecimal minZnGrade = new BigDecimal(znGradeRange.split("-")[0]);
        BigDecimal maxZnGrade = new BigDecimal(znGradeRange.split("-")[1]);
        Boolean isZnGradeSuspicious = false;
        if (lab.getZnGrade() != null) {
            isZnGradeSuspicious = lab.getZnGrade().compareTo(minZnGrade) < 0 || lab.getZnGrade().compareTo(maxZnGrade) > 0;
        }
        suspiciousMap.put("znGrade", isZnGradeSuspicious);

// caGrade
        BigDecimal minCaGrade = new BigDecimal(caGradeRange.split("-")[0]);
        BigDecimal maxCaGrade = new BigDecimal(caGradeRange.split("-")[1]);
        Boolean isCaGradeSuspicious = false;
        if (lab.getCaGrade() != null) {
            isCaGradeSuspicious = lab.getCaGrade().compareTo(minCaGrade) < 0 || lab.getCaGrade().compareTo(maxCaGrade) > 0;
        }
        suspiciousMap.put("caGrade", isCaGradeSuspicious);

// sio2Grade
        BigDecimal minSio2Grade = new BigDecimal(sio2GradeRange.split("-")[0]);
        BigDecimal maxSio2Grade = new BigDecimal(sio2GradeRange.split("-")[1]);
        Boolean isSio2GradeSuspicious = false;
        if (lab.getSio2Grade() != null) {
            isSio2GradeSuspicious = lab.getSio2Grade().compareTo(minSio2Grade) < 0 || lab.getSio2Grade().compareTo(maxSio2Grade) > 0;
        }
        suspiciousMap.put("sio2Grade", isSio2GradeSuspicious);

// fe2PlusGrade
        BigDecimal minFe2PlusGrade = new BigDecimal(fe2PlusGradeRange.split("-")[0]);
        BigDecimal maxFe2PlusGrade = new BigDecimal(fe2PlusGradeRange.split("-")[1]);
        Boolean isFe2PlusGradeSuspicious = false;
        if (lab.getFe2PlusGrade() != null) {
            isFe2PlusGradeSuspicious = lab.getFe2PlusGrade().compareTo(minFe2PlusGrade) < 0 || lab.getFe2PlusGrade().compareTo(maxFe2PlusGrade) > 0;
        }
        suspiciousMap.put("fe2PlusGrade", isFe2PlusGradeSuspicious);

// cr6PlusGrade
        BigDecimal minCr6PlusGrade = new BigDecimal(cr6PlusGradeRange.split("-")[0]);
        BigDecimal maxCr6PlusGrade = new BigDecimal(cr6PlusGradeRange.split("-")[1]);
        Boolean isCr6PlusGradeSuspicious = false;
        if (lab.getCr6PlusGrade() != null) {
            isCr6PlusGradeSuspicious = lab.getCr6PlusGrade().compareTo(minCr6PlusGrade) < 0 || lab.getCr6PlusGrade().compareTo(maxCr6PlusGrade) > 0;
        }
        suspiciousMap.put("cr6PlusGrade", isCr6PlusGradeSuspicious);

// moistureGrade
        BigDecimal minMoistureGrade = new BigDecimal(moistureGradeRange.split("-")[0]);
        BigDecimal maxMoistureGrade = new BigDecimal(moistureGradeRange.split("-")[1]);
        Boolean isMoistureGradeSuspicious = false;
        if (lab.getMoistureGrade() != null) {
            isMoistureGradeSuspicious = lab.getMoistureGrade().compareTo(minMoistureGrade) < 0 || lab.getMoistureGrade().compareTo(maxMoistureGrade) > 0;
        }
        suspiciousMap.put("moistureGrade", isMoistureGradeSuspicious);

// tfeGrade
        BigDecimal minTfeGrade = new BigDecimal(tfeGradeRange.split("-")[0]);
        BigDecimal maxTfeGrade = new BigDecimal(tfeGradeRange.split("-")[1]);
        Boolean isTfeGradeSuspicious = false;
        if (lab.getTfeGrade() != null) {
            isTfeGradeSuspicious = lab.getTfeGrade().compareTo(minTfeGrade) < 0 || lab.getTfeGrade().compareTo(maxTfeGrade) > 0;
        }
        suspiciousMap.put("tfeGrade", isTfeGradeSuspicious);

// cr2o3Grade
        BigDecimal minCr2o3Grade = new BigDecimal(cr2o3GradeRange.split("-")[0]);
        BigDecimal maxCr2o3Grade = new BigDecimal(cr2o3GradeRange.split("-")[1]);
        Boolean isCr2o3GradeSuspicious = false;
        if (lab.getCr2o3Grade() != null) {
            isCr2o3GradeSuspicious = lab.getCr2o3Grade().compareTo(minCr2o3Grade) < 0 || lab.getCr2o3Grade().compareTo(maxCr2o3Grade) > 0;
        }
        suspiciousMap.put("cr2o3Grade", isCr2o3GradeSuspicious);

// feoGrade
        BigDecimal minFeoGrade = new BigDecimal(feoGradeRange.split("-")[0]);
        BigDecimal maxFeoGrade = new BigDecimal(feoGradeRange.split("-")[1]);
        Boolean isFeoGradeSuspicious = false;
        if (lab.getFeoGrade() != null) {
            isFeoGradeSuspicious = lab.getFeoGrade().compareTo(minFeoGrade) < 0 || lab.getFeoGrade().compareTo(maxFeoGrade) > 0;
        }
        suspiciousMap.put("feoGrade", isFeoGradeSuspicious);

// mgoGrade
        BigDecimal minMgoGrade = new BigDecimal(mgoGradeRange.split("-")[0]);
        BigDecimal maxMgoGrade = new BigDecimal(mgoGradeRange.split("-")[1]);
        Boolean isMgoGradeSuspicious = false;
        if (lab.getMgoGrade() != null) {
            isMgoGradeSuspicious = lab.getMgoGrade().compareTo(minMgoGrade) < 0 || lab.getMgoGrade().compareTo(maxMgoGrade) > 0;
        }
        suspiciousMap.put("mgoGrade", isMgoGradeSuspicious);

// crFeRatio
        BigDecimal minCrFeRatio = new BigDecimal(crFeRatioRange.split("-")[0]);
        BigDecimal maxCrFeRatio = new BigDecimal(crFeRatioRange.split("-")[1]);
        Boolean isCrFeRatioSuspicious = false;
        if (lab.getCrFeRatio() != null) {
            isCrFeRatioSuspicious = lab.getCrFeRatio().compareTo(minCrFeRatio) < 0 || lab.getCrFeRatio().compareTo(maxCrFeRatio) > 0;
        }
        suspiciousMap.put("crFeRatio", isCrFeRatioSuspicious);

// cr2o3FeoRatio
        BigDecimal minCr2o3FeoRatio = new BigDecimal(cr2o3FeoRatioRange.split("-")[0]);
        BigDecimal maxCr2o3FeoRatio = new BigDecimal(cr2o3FeoRatioRange.split("-")[1]);
        Boolean isCr2o3FeoRatioSuspicious = false;
        if (lab.getCr2o3FeoRatio() != null) {
            isCr2o3FeoRatioSuspicious = lab.getCr2o3FeoRatio().compareTo(minCr2o3FeoRatio) < 0 || lab.getCr2o3FeoRatio().compareTo(maxCr2o3FeoRatio) > 0;
        }
        suspiciousMap.put("cr2o3FeoRatio", isCr2o3FeoRatioSuspicious);


// slurryConcentration
        BigDecimal minSlurryConcentration = new BigDecimal(slurryConcentrationRange.split("-")[0]);
        BigDecimal maxSlurryConcentration = new BigDecimal(slurryConcentrationRange.split("-")[1]);
        Boolean isSlurryConcentrationSuspicious = false;

        if (lab.getSlurryConcentration() != null) {
                isSlurryConcentrationSuspicious = lab.getSlurryConcentration().compareTo(minSlurryConcentration) < 0 || lab.getSlurryConcentration().compareTo(maxSlurryConcentration) > 0;
            }

        suspiciousMap.put("slurryConcentration", isSlurryConcentrationSuspicious);

// slurryDensity
        BigDecimal minSlurryDensity = new BigDecimal(slurryDensityRange.split("-")[0]);
        BigDecimal maxSlurryDensity = new BigDecimal(slurryDensityRange.split("-")[1]);
        Boolean isSlurryDensitySuspicious = false;
        if (lab.getSlurryDensity() != null) {
            isSlurryDensitySuspicious = lab.getSlurryDensity().compareTo(minSlurryDensity) < 0 || lab.getSlurryDensity().compareTo(maxSlurryDensity) > 0;
        }
        suspiciousMap.put("slurryDensity", isSlurryDensitySuspicious);

// 18目
        BigDecimal minMu18 = new BigDecimal(mu18Range.split("-")[0]);
        BigDecimal maxMu18 = new BigDecimal(mu18Range.split("-")[1]);
        Boolean isMu18Suspicious = false;
        if (lab.getMu18() != null) {
            isMu18Suspicious = lab.getMu18().compareTo(minMu18) < 0 || lab.getMu18().compareTo(maxMu18) > 0;
        }
        suspiciousMap.put("slurryDensity", isMu18Suspicious);

// 60目
        BigDecimal minMu60 = new BigDecimal(mu60Range.split("-")[0]);
        BigDecimal maxMu60 = new BigDecimal(mu60Range.split("-")[1]);
        Boolean isMu60Suspicious = false;
        if (lab.getMu60() != null) {
            isMu60Suspicious = lab.getMu60().compareTo(minMu60) < 0 || lab.getMu60().compareTo(maxMu60) > 0;
        }
        suspiciousMap.put("slurryDensity", isMu60Suspicious);

// 80目
        BigDecimal minMu80 = new BigDecimal(mu80Range.split("-")[0]);
        BigDecimal maxMu80 = new BigDecimal(mu80Range.split("-")[1]);
        Boolean isMu80Suspicious = false;
        if (lab.getMu80() != null) {
            isMu80Suspicious = lab.getMu80().compareTo(minMu80) < 0 || lab.getMu80().compareTo(maxMu80) > 0;
        }
        suspiciousMap.put("slurryDensity", isMu80Suspicious);

// 100目
        BigDecimal minMu100 = new BigDecimal(mu100Range.split("-")[0]);
        BigDecimal maxMu100 = new BigDecimal(mu100Range.split("-")[1]);
        Boolean isMu100Suspicious = false;
        if (lab.getMu100() != null) {
            isMu100Suspicious = lab.getMu100().compareTo(minMu100) < 0 || lab.getMu100().compareTo(maxMu100) > 0;
        }
        suspiciousMap.put("slurryDensity", isMu100Suspicious);

// depth
        BigDecimal minDepth = new BigDecimal(depthRange.split("-")[0]);
        BigDecimal maxDepth = new BigDecimal(depthRange.split("-")[1]);
        Boolean isDepthSuspicious = false;
        if (lab.getDepth() != null) {
            isDepthSuspicious = lab.getDepth().compareTo(minDepth) < 0 || lab.getDepth().compareTo(maxDepth) > 0;
        }
        suspiciousMap.put("slurryDensity", isDepthSuspicious);

        return suspiciousMap;
    }

}