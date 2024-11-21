package cn.iocoder.yudao.module.division.controller.admin.lab.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImportFilePageReqVO extends PageParam {

    private String fileName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date[] createTime;

}
