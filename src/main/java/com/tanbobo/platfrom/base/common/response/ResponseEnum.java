package com.tanbobo.platfrom.base.common.response;

/**
 * 结果码定义
 */
public enum ResponseEnum {
    SUCCESS("00", "请求成功！"),
    FAILURE("01", "网络繁忙，请稍后重试！"),
    NUMFAILURE("02", "参数不能为空或不是正整数或不能为零"),
    ACCOUNTNOTEXIST("03", "客户不存在"),
    OTP_VERIFY_FAIL("04", "验证码验证失败"),
    OPEN_FAIL("05", "开通旺财账户失败"),
    ACCOUNT_HAS_REGISTERED("06", "账户已注册"),
    CALL_FAILURE("07", "调用报错!"),
    RESULT_NULL("08", "无返回数据!");

    private String code;

    private String message;

    // 构造方法
    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResponseEnum getResponseEnumByCode(int code) {
        String codeStr = String.valueOf(code);
        for (ResponseEnum responseEnum : ResponseEnum.values()) {
            if (responseEnum.getCode().equals(codeStr)) {
                return responseEnum;
            }
        }
        return ResponseEnum.SUCCESS;
    }
}
