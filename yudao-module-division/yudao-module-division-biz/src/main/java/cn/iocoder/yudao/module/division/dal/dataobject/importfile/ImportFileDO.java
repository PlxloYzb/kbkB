package cn.iocoder.yudao.module.division.dal.dataobject.importfile;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

@TableName("division_lab_import_files")
@KeySequence("division_lab_import_files_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImportFileDO extends BaseDO {

    @TableId
    private Long id;

    private String fileName;

    private byte[] fileData;

}