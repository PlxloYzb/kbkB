package cn.iocoder.yudao.module.division.service.lab;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.division.controller.admin.lab.vo.*;
import cn.iocoder.yudao.module.division.dal.dataobject.lab.LabDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 化验室 Service 接口
 *
 * @author PlxloYzb
 */
public interface LabService {

    /**
     * 创建化验室
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLab(@Valid LabSaveReqVO createReqVO);

    /**
     * 更新化验室
     *
     * @param updateReqVO 更新信息
     */
    void updateLab(@Valid LabSaveReqVO updateReqVO);

    /**
     * 删除化验室
     *
     * @param id 编号
     */
    void deleteLab(Long id);

    /**
     * 获得化验室
     *
     * @param id 编号
     * @return 化验室
     */
    LabDO getLab(Long id);

    /**
     * 获得化验室分页
     *
     * @param pageReqVO 分页查询
     * @return 化验室分页
     */
    PageResult<LabDO> getLabPage(LabPageReqVO pageReqVO);

    List<LabRespVO> previewImportData(MultipartFile file);

    void importLabData(List<LabSaveReqVO> labList, Long importFileId, String confirmPassword);

}