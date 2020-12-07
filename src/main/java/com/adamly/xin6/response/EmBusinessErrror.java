package com.adamly.xin6.response;

//错误类型枚举
public enum EmBusinessErrror implements CommonError{
    //10000通用错误
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),

    //20000用户信息相关错误
//    USER_NUT_EXIST(20001,"用户不存在"),
    USER_NUT_EXIST(20002,"用户不存在"),
    USER_LOGIN_FAIL(20002,"手机号或密码错误"),
    USER_NOT_LOGIN(20003,"用户还未登录"),

//    30000商品信息相关错误
    GOODS_NUT_EXIST(30001,"商品不存在"),

//    40000交易信息相关错误
    STOCK_NOT_ENOUGH(40001,"商品库存不足"),

    ;

    EmBusinessErrror(int errcode, String errMsg) {
        this.errcode = errcode;
        this.errMsg = errMsg;
    }

    private int errcode;
    private String errMsg;

    @Override
    public int getErrCode() {
        return this.errcode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg=errMsg;
        return this;
    }
}
