package com.adamly.xin6.controller;

public class BaseController {
    public final static String CONTENT_TYPE_FORMD="application/x-www-form-urlencoded";
    public final static String CONTENT_TYPE_JSON="application/json";


    //   定义ExceptionHandler捕获最上层的异常
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.OK)
//    public Object handlerException(HttpServletRequest request, Exception ex){
//        Map<String, Object> responseData=new HashMap<String, Object>();
//        if(ex instanceof BusinessException){
//            BusinessException businessException=(BusinessException)ex;
//            responseData.put("errCode",businessException.getErrCode());
//            responseData.put("errMsg",businessException.getErrMsg());
//        } else {
//            responseData.put("errCode", EmBusinessErrror.UNKNOWN_ERROR.getErrCode());
//            responseData.put("errMsg",EmBusinessErrror.UNKNOWN_ERROR.getErrMsg());
//            System.out.println(ex.getMessage());
//            ex.printStackTrace();
//        }
//        return CommonReturnType.create(responseData,"fail");
//    }
}
