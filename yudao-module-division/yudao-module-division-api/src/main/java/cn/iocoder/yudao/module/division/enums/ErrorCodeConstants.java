// TODO 待办：请将下面的错误码复制到 yudao-module-division-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 化验室 模块 7-001-000-000 ==========

package cn.iocoder.yudao.module.division.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {

    ErrorCode LAB_NOT_EXISTS = new ErrorCode(1_001_000_001, "化验室不存在");

    ErrorCode IMPORT_FILE_PARSE_ERROR = new ErrorCode(1_001_000_002, "解析错误");

    ErrorCode IMPORT_CONFIRM_PASSWORD_ERROR = new ErrorCode(1_001_000_003, "输入密码错误");

    ErrorCode IMPORT_SUSPICIOUS_DATA_ERROR = new ErrorCode(1_001_000_004, "可疑数据错误");

    ErrorCode SAMPLE_NUMBER_REQUIRED = new ErrorCode(1_001_000_005, "请输入样品编号");

    ErrorCode SAMPLING_TIME_REQUIRED = new ErrorCode(1_001_000_006, "请输入送样时间");

    ErrorCode OPERATOR_NAME_REQUIRED = new ErrorCode(1_001_000_007, "请输入化验人员");

    ErrorCode BENEFICIATION_NOT_EXISTS = new ErrorCode(1_001_000_008, "选矿每日数据不存在");


}