package com.adamly.xin6.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    private boolean hasErr=false;

    private Map<String,String> errMsgMap=new HashMap<>();

    public boolean isHasErr() {
        return hasErr;
    }

    public void setHasErr(boolean hasErr) {
        this.hasErr = hasErr;
    }

    public Map<String, String> getErrMsgMap() {
        return errMsgMap;
    }

    public void setErrMsgMap(Map<String, String> errMsgMap) {
        this.errMsgMap = errMsgMap;
    }

//    将格式化错误信息转换为直接可用的errMsg
    public String getErrMsg(){
        return StringUtils.join(errMsgMap.values().toArray(),",");
    }
}
